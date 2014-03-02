/*
*   xulfaces : bring XUL power to Java
*   
*  Copyright (C) 2005  Olivier SCHMITT 
*	This library is free software; you can redistribute it and/or
*	modify it under the terms of the GNU Lesser General Public
*  License as published by the Free Software Foundation; either
*	version 2.1 of the License, or (at your option) any later version.
*
*	This library is distributed in the hope that it will be useful,
*	but WITHOUT ANY WARRANTY; without even the implied warranty of
*	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
*	Lesser General Public License for more details.
*
*	You should have received a copy of the GNU Lesser General Public
*	License along with this library; if not, write to the Free Software
*	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

/**
* <p>Defines a JSF Ajax Request.</p>
* @author kito31
* @version $Id: xulfaces-bridge.js,v 1.27 2007/07/17 20:22:32 kito31 Exp $ 
*/
function FacesRequest(serverUrl) {
  
	/** Contains the request parameters */
	this.parms = new Array();
	
	/** Current param index */
	this.parmsIndex = 0;

	/** Set the server URL that receives request */
	this.server = serverUrl;
	
	/**
	* <p>Change the server URL.</p>
	* @param url The URL (String)
	*/
	this.setURL = function(url) {
		this.server = url;
	}
  
	/**
	* <p>Send the request to the server.</p>
	* <p>The params array is sent to server if sendParams is true.</p>
	* @param sendParams Send or not the parameters ? (Boolean) 
	* @return The responseXML object
	*/  
	this.send = function(sendParams) {
	
	  var response = null;
	  var targetURL = this.server;
	  var httpRequest;
	  //Try to create our XMLHttpRequest Object
	  try {
	  	if (window.XMLHttpRequest) {
	        httpRequest = new XMLHttpRequest();
	    } else if (window.ActiveXObject) {
	        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	    }  
	  }catch (e){
	    this.reset();
	    throw 'Error creating the connection!' + e;
	  }
	  
	  //Make the connection and send our data
	  try {
	    var data = "";
	    this.add("XFBRIDGE_REQUEST","true");
	    
	    if(sendParams){
		    for(var i in this.parms) {
		      data = data + this.parms[i].name + "=" + encodeURIComponent(this.parms[i].value) + "&";
	    	}	    	
	    }    
	    httpRequest.open("POST", targetURL, false);
	    httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	    httpRequest.send(data);   	
	    
	  }catch (e){
	    this.reset();
	    throw "An error has occured calling the external site: " + e;
	  } 
	 
	  this.reset();
	  //Make sure we received a valid response
	  switch(httpRequest.readyState) {
	    case 1,2,3:
	        throw 'Bad Ready State: '+httpRequest.status;
	    break;
	    case 4:
	      if(httpRequest.status !=200) {
	      }
	    break;
	  }
	  response = httpRequest.responseXML;	  
	  return response;  
  	}
			
	/**
	* <p>Send the request to the server in asynchronous mode 
	* (processHttpRequest method is called when ready, onRequestProgress in progress).</p>
	* <p>The params array is sent to server if sendParams is true.</p>
	* @param sendParams Send or not the parameters ? (Boolean) 
	* @return The responseXML object
	*/  
	this.sendAsynchronously = function(sendParams) {
	
		var response = null;
		var targetURL = this.server;
		var httpRequest = null;
	  	//Try to create our XMLHttpRequest Object
		try {
	  		if (window.XMLHttpRequest) {
	        	httpRequest = new XMLHttpRequest();
		    } else if (window.ActiveXObject) {
		        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	    	}  
		}catch (e){
			this.reset();
		    throw 'Error creating the connection!' + e;
		}
	  
	  	//Make the connection and send our data
	  	try {
	    	var data = "";
		    this.add("XFBRIDGE_REQUEST","true");	    

		    for(var i in this.parms) {
				data = data + this.parms[i].name + "=" + encodeURIComponent(this.parms[i].value) + "&";
    		}	    	

	    	httpRequest.onreadystatechange = function() { 
	    		XUL_FACES_BRIDGE.processHttpRequest(httpRequest);
		    };
	    
			httpRequest.onprogress = function() { 
				XUL_FACES_BRIDGE.onRequestProgress(); 
			};

		    httpRequest.open("POST", targetURL, true);
    	    httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	    	httpRequest.send(data);   	
	    
		}catch (e){
		    this.reset();
	    	throw "An error has occured calling the external site: " + e;
		}
		return httpRequest; 	 		
	}				
	/**
	* <p>Add a Param object to parms array with name and value.</p>
	* @param name The parameter name (String)
	* @param value The parameter value (Object)
	*/  
	this.add = function(name,value) {
		for(var i=0; i < this.parms.length;i++){
			if(this.parms[i].name == name){
	  			this.parms[i].value = value;
	  		return;
			}
		}
		//Add a new pair object to the params
		this.parms[this.parmsIndex] = new Param(name,value);
		this.parmsIndex++;  
	}

	/**
	* <p>Reset the parms array and parmsIndex.</p>
	*/  
	this.reset = function() {
  		this.parms = new Array();
		this.parmsIndex = 0;  
	}
}

/**
* @class <p>Defines a parameter (name,value).</p>
* @constructor 
* <p>Build a Param object with name and value.</p>
* @param name The name (String)
* @param value The value
*/
function Param(name,value) {
  this.name = name;
  this.value = value;
}

/**
* @class <p>Defines a faces component.</p>
* @constructor 
* <p>Build a FacesComponent with  clientId and state.</p>
* <p>clientId maps to JSF UIComponent clientId.</p>
* <p>state is the current state of the component on client side.</p>
* @param clientId The client side id of the server component (String)
* @param state State of the component on client side (String)
* @param removeAfterSubmitted The FacesComponent is destroyed after AJAX request submission.
*/
function FacesComponent(clientId,state,removeAfterSubmitted) {
  this.clientId = clientId;
  this.state = state;
  this.removeAfterSubmitted = removeAfterSubmitted;
}

/**
* @class <p>Defines a bridge between a XUL client and a JSF application.</p>
* @constructor 
* <p>Build XULFacesBridge object dedicated to interact with server at serverUrl.</p>
* @param serverUrl The JSF page (String)
*/
function XULFacesBridge(serverUrl) {
    

    /** The logger */
    this.logger =  new Logger();
    
    /** The current responseXml object (from last send invocation) */
    this.responseXml = null;
    
    /** The server URL */
    this.serverUrl = serverUrl;
    
    /** Components array */
	this.components = new Array();
	
	/** Current component index */
	this.componentsIndex = 0;
	
	/**
	* <p>Add a FacesComponent object to components.</p>
	* <p>This FacesComponent is intended to exist on server side.</p>
	*
	* @param id The client id (String)
	* @param state The state of this component on client side (String)
	*/
	this.addComponent = function (clientId,state,removeAfterSubmitted){
		
		for(var i=0; i < this.components.length;i++){
			if(this.components[i].clientId == clientId){
				this.components[i].state = state;
				this.components[i].removeAfterSubmitted = removeAfterSubmitted;
				return;
  			}
  		}
		//Add a new FacesComponent object to the components
  		this.components[this.componentsIndex] = new FacesComponent(clientId,state,removeAfterSubmitted);
  		this.componentsIndex++;
	}

	
	this.cleanComponents = function (){
		var tempArray = new Array();
		var tempIndex = 0;
		for(var i=0; i < this.components.length;i++){
			var facesComponent = this.components[i];
			if(!facesComponent.removeAfterSubmitted){
				tempArray[tempIndex] = facesComponent;
				tempIndex++;
			}
		}
		this.components = tempArray;
		this.componentsIndex = tempIndex;
	}
		
	/**
	* <p>Send the request.</p>
	* <p>FacesComponents are sent to serverUrl.</p>
	*
	* @return The responseXML object
	*/			
	this.sendRequest = function (){
		var tempFacesRequest = new FacesRequest(this.serverUrl);
		for(var i=0; i < this.components.length;i++){
			var facesComponent = this.components[i];
			tempFacesRequest.add(facesComponent.clientId,facesComponent.state);
		}
		var renderKitId = document.getElementById('javax.faces.RenderKitId');		
		if(renderKitId){			
			var attribute = renderKitId.attributes.getNamedItem('value');
			tempFacesRequest.add('javax.faces.RenderKitId',attribute.value);
		}
		var viewState = document.getElementById('javax.faces.ViewState');		
		if(viewState){			
			var attribute = viewState.attributes.getNamedItem('value');
			tempFacesRequest.add('javax.faces.ViewState',attribute.value);
		}
		this.cleanComponents();
		return tempFacesRequest.send(true);
	}

	
	/**
	* <p>Send the request asynchronously.</p>
	* <p>FacesComponents are sent to serverUrl.</p>
	*
	* @return The responseXML object
	*/			
	this.sendAsynchronousRequest = function (){
		var tempFacesRequest = new FacesRequest(this.serverUrl);
		for(var i=0; i < this.components.length;i++){
			var facesComponent = this.components[i];
			tempFacesRequest.add(facesComponent.clientId,facesComponent.state);
		}
		var renderKitId = document.getElementById('javax.faces.RenderKitId');		
		if(renderKitId){			
			var attribute = renderKitId.attributes.getNamedItem('value');
			tempFacesRequest.add('javax.faces.RenderKitId',attribute.value);
		}
		var viewState = document.getElementById('javax.faces.ViewState');		
		if(viewState){			
			var attribute = viewState.attributes.getNamedItem('value');
			tempFacesRequest.add('javax.faces.ViewState',attribute.value);
		}
		this.cleanComponents();
		return tempFacesRequest.sendAsynchronously(true);		
	}
	  	
	/**
	* <p>Check for exception tag in xml.</p>
	*
	* @param xml An xml doc
	* @return true or false
	*/				
	this.hasErrors = function(xml){
		if(xml){
			var exceptionElements = xml.getElementsByTagName('exception');		
			if(exceptionElements.length > 0){
				return true;
		  	}
 		}
		return false;
	}

	/**
	* <p>Check for notValid tag in xml.</p>
	*
	* @param xml An xml doc
	* @return true or false
	*/				
	this.hasNotValid = function(xml){
		if(xml){
			var notValidElements = xml.getElementsByTagName('notValid');		
			if(notValidElements.length > 0){
				return true;
		  	}
 		}
		return false;
	}
	
	
	/**
	* <p>Replace oldNode with a new node.</p>
	* 
	* @param oldNode The old node
	* @param newNode The new node	
	*/
  	this.replaceNode = function (oldNode,newNode){	

		if((oldNode) && (newNode)) {		
			try {
				var parentNode = oldNode.parentNode;
				var clonedNode = newNode.cloneNode(true);
				parentNode.replaceChild(clonedNode,oldNode);
			}
			catch(e){				
			}			
		}		
	}

	/**
	* <p>Update the document DOM with xml.</p>
	* 
	* @param xml The xml document
	*/
  	this.updateDOM = function (xml){
	
		if(xml){
			var viewStateElement =  xml.getElementsByTagName('xfc:viewState');
			var zoneElements = xml.getElementsByTagName('xfc:updateZone');
			for(var i=0; i < zoneElements.length;i++){
	  			var zoneElement = zoneElements[i];
	  			var zoneAttribute = zoneElement.attributes.getNamedItem('nodeID');
	  			if(zoneAttribute){
		  			var oldElement = document.getElementById(zoneAttribute.value);
		  			var newElement = xml.getElementById(zoneAttribute.value);
		  			if(newElement){
		  				// Update zone
		  				this.mergeDOM(oldElement,newElement);
		  			}
		  			else {
			  			// Remove zone
		  				zoneElement.parentNode.removeChild(zoneElement);  				
		  			}
	  			}
		  	}
		  	
			// If viewStateElement is not null then update the viewState in current doc	
		  	if(viewStateElement){
			  	var oldViewStateElement = document.getElementById('javax.faces.ViewState');
  				this.mergeDOM(oldViewStateElement,viewStateElement[0]);
		  	}
		  	
	 	}
	}


	/**
	* <p>Watch zone into the document DOM.</p>
	* 
	*/
  	this.watchDOM = function (){	

		var zoneElements = document.getElementsByTagName('watchZoneProperty');
		for(var i=0; i < zoneElements.length;i++){
  			var zoneElement = zoneElements[i];
  			var zoneAttribute = zoneElement.attributes.getNamedItem('nodeID');
  			if(zoneAttribute){
	  			var watchedElement = document.getElementById(zoneAttribute.value);
	  			if(watchedElement != null){
	  				var propertyName = zoneElement.attributes.getNamedItem('propertyName');
	  				var code = "watchedElement." + propertyName.value;
	  				try {	
		  				var propertyValue = eval(code);
		  				this.addComponent(zoneAttribute.value,propertyValue,true);
					}
					catch(e){
					}
	  			}
  			}
	  	}
	}



  	/**
	* <p>Merge DOM node with otherNode.</p>
	* <p>Merge deeply.</p>
	*
	* @param node The current node to update
	* @param otherNode The otherNode to inspect and merge into node	
	*/
  	this.mergeDOM = function (node,otherNode){
		if(node && otherNode){
			var nodeAttributes = node.attributes;
			var otherNodeAttributes = otherNode.attributes;
			if(nodeAttributes && otherNodeAttributes){
				for (var j = 0; j < nodeAttributes.length; j++) {
			  		var nodeAttribute = nodeAttributes[j];
					var otherNodeAttribute = otherNodeAttributes.getNamedItem(nodeAttribute.name);
					// If current attribute exists in otherNode then check attribute's value
					if(otherNodeAttribute){
						// Is current attribute has changed ?
						if(otherNodeAttribute.value != nodeAttribute.value){
							// the two nodes are different : replace old with new						
							this.replaceNode(node,otherNode);
							return;						
						}
					}
				}						
			}
			var hasNodes = node.hasChildNodes();
			var otherHasNodes = otherNode.hasChildNodes();
			if( hasNodes && otherHasNodes){			
				var nodeChildren = node.childNodes;
				var otherNodeChildren = otherNode.childNodes;
				if(nodeChildren.length == otherNodeChildren.length){
					for (var j = 0; j < nodeChildren.length; j++) {
				  		var childNode = nodeChildren.item(j);
				  		var otherChildNode = otherNodeChildren.item(j);
				  		this.mergeDOM(childNode,otherChildNode);
					}
				}
				else {
					this.replaceNode(node,otherNode);
				}
			}
			else {
				this.replaceNode(node,otherNode);
			}
		}
		return;
	}

  	/**
	* <p>Checkbox change event handler.</p>
	*
	* @param event An event object
	*/ 	  	  	
  	this.onTextboxChange = function (event){
  	
		if( (event.target.id) && (event.target.tagName == "textbox")){	
			XUL_FACES_BRIDGE.addComponent(event.target.id,event.target.value,true);	
		}
	}
  	
  	/**
	* <p>Checkbox change event handler.</p>
	*
	* @param event An event object
	*/ 	  	
  	this.onCheckboxChange = function (event){

		if( (event.target.id) && (event.target.tagName == "checkbox")){	
			var checkBox = document.getElementById(event.target.id);
			XUL_FACES_BRIDGE.addComponent(checkBox.id,checkBox.checked,true);
		}
	}
  	
  	/**
	* <p>List selection event handler.</p>
	*
	* @param event An event object
	*/ 	  	
  	this.onListboxSelect = function (event){

		if( (event.target.id) && (event.target.tagName == "listbox")){				
			var listBox = event.target;
			if(listBox.selType == 'single'){				
				var listItem = listBox.currentItem;
				if((listItem.id != null) && (listItem.id != "")){
					XUL_FACES_BRIDGE.addComponent(listBox.id,listItem.id,true);
				}
				else {
					XUL_FACES_BRIDGE.addComponent(listBox.id,listItem.value,true);
				}				
			}
			else {
				var listItems = listBox.selectedItems;
				var value = "";
				for(i=0; i < listItems.length; i++){
					var currentListItem = listItems[i];
					if(i+1 < listItems.length){
						if((currentListItem.id != null) && (currentListItem.id != "")){
							value = value + currentListItem.id + " ";
						}
						else {
							value = value + currentListItem.value + " ";
						}						
					}
					else {
						if((currentListItem.id != null) && (currentListItem.id != "")){
							value = value + currentListItem.id;
						}
						else {
							value = value + currentListItem.value + " ";
						}
					}
				}
				XUL_FACES_BRIDGE.addComponent(listBox.id,value,true);
			}	
		}
	}

	/**
	* <p>Radio change event handler.</p>
	*
	* @param event An event object
	*/ 	  	
  	this.onRadioChange = function (event){
		if(event.target.tagName == "radio"){	
			var parentNode = event.target.parentNode;
			XUL_FACES_BRIDGE.addComponent(parentNode.id,event.target.value,true);
		}
	}

	/**
	* <p>Drop down change selection event handler.</p>
	*
	* @param event An event object
	*/ 	
 	this.onDropDownChange = function (event){
	
		if(event.target.tagName == "menuitem"){
			var parentNode = event.target.parentNode;
			if(parentNode.tagName == "menupopup"){
				parentNode = parentNode.parentNode;
				if(parentNode.tagName == "menulist"){
					if(parentNode.id){
						XUL_FACES_BRIDGE.addComponent(parentNode.id,event.target.value,true);	
					}
				}
			}		
		}
	}
	
	/**
	* <p>Tree selection event handler.</p>
	*
	* @param event An event object 
	*/ 	
 	this.onTreeSelect = function(event){

		if( (event.target.id) && (event.target.tagName == "tree")){				
			var tree = event.target;
			XUL_FACES_BRIDGE.saveTreeSelection(tree);
		}
	}
	
	this.saveTreeSelection = function(tree){
			
		var selection = "";
		var numRanges = tree.view.selection.getRangeCount();
	
		for ( var i=0; i < numRanges;i++ ){		
			var start = new Object();
			var end = new Object();	
			tree.view.selection.getRangeAt(i,start,end);
			for (var v=start.value; v<=end.value; v++){
				var item = tree.contentView.getItemAtIndex(v);				
				if(v < end.value){
					selection = selection + item.id + " ";
				}
				else {
					selection = selection + item.id;
				}
			}
		}
		XUL_FACES_BRIDGE.addComponent(tree.id,selection);	
		for(var i=0; i < tree.view.rowCount; i++){
			if(tree.view.isContainer(i)){
				var item = tree.contentView.getItemAtIndex(i);			
				var value = item.getAttribute("open");
				XUL_FACES_BRIDGE.addComponent(item.id,value);	
			}
		}	
	}
	

	/**
	* <p>Execute JSF action with clientId (map to UIComponent client id).</p>
	* @param clientId The action clientId
	*/
	this.executeAction = function(clientId) {
		this.responseXml = null;
		this.addComponent('action',clientId,true);
		var targetURL = this.serverUrl; // HL(May/6/07): added to support URL reload below
		this.watchDOM();
		var xml = this.sendRequest();
		
		var isBadAction = this.hasErrors(xml);
		var isNotValid = this.hasNotValid(xml);

		// Navigation support
		// Require JSF navigation rule with page redirect directive.		
		if ( !isNotValid && !isBadAction ) {			
			if(xml && xml.documentURI && 
				targetURL != xml.documentURI){
				document.location.href = xml.documentURI;
				return;
			}			
		}       
		
		if( (isNotValid) || (!isBadAction)){
			this.updateDOM(xml);
		}
		else {
			if(isBadAction){
				var errors = this.extractErrors(xml);
				throw errors;
			}
		}	
		this.responseXml = xml;	
		if(isNotValid){
			throw "Values are not valid !";
		}	
	}
	
	/**
	* <p>Execute JSF action with clientId (map to UIComponent client id).</p>
	* @param clientId The action clientId
	*/
	this.executeAsynchronousAction = function(clientId) {
		this.responseXml = null;
		this.addComponent('action',clientId,true);
		this.watchDOM();
		this.sendAsynchronousRequest();
	}
	
	/**
	* <p>Execute a binded method on server side.</p>
	* @param componentId The component id
	* @param eventName Event's name
	*/
	this.fireEventOnServerSide = function(componentId,eventName){
		this.responseXml = null;				
		this.addComponent("XF_MB_"+componentId,eventName,true);	
		this.watchDOM();
		this.sendAsynchronousRequest();	
	}	
	
	/**
	* <p>Callback for processing asynchronous XmlHttpRequest.</p>
	* @param httpRequest An XMLHttpRequest object
	*/
	this.processHttpRequest = function(httpRequest){

		//Make sure we received a valid response
	  	switch(httpRequest.readyState) {
	    	case 1,2,3:
	        	//throw 'Bad Ready State: ' + httpRequest.status;
			    break;
	    	case 4:
		      	
				var xml = httpRequest.responseXML;	
				var status = httpRequest.status;
				var isBadAction = this.hasErrors(xml);
				var isNotValid = this.hasNotValid(xml);

				// Navigation support
				// Require JSF navigation rule with page redirect directive.
				var targetURL = this.serverUrl;
				if ( !isNotValid && !isBadAction ) {
					if(status == 404){
						alert("404 error : not found ! (you should check your rules)");
						//alert(httpRequest.responseText);
					}
					else {
						if(xml && xml.documentURI && 
							targetURL != xml.documentURI){
							document.location.href = xml.documentURI;
							return;
						}
					}
				}
				// 
				if( (isNotValid) || (!isBadAction)){
					this.updateDOM(xml);
				}
				else {
					if(isBadAction){
						var errors = this.extractErrors(xml);
						var progressMeter = document.getElementById("AJAX-progress-meter");
						if(progressMeter){
							progressMeter.setAttribute("mode", "determined");
							progressMeter.setAttribute("hidden", "false");
						}
						if(isNotValid){
							alert("Values are not valid !");
						}						
						alert(errors);
					}
				}	
				this.responseXml = xml;	

				if(XUL_FACES_BRIDGE.logger.isDebugMode()){
					XUL_FACES_BRIDGE.logger.debug(httpRequest.responseText);
				}				

				var progressMeter = document.getElementById("AJAX-progress-meter");
				if(progressMeter){
					progressMeter.setAttribute("mode", "determined");
					progressMeter.setAttribute("hidden", "false");
				}
				if(isNotValid){
					throw "Values are not valid !";
				}
	    	break;
		}		
	}
	
	this.onRequestProgress = function() {
		var progressMeter = document.getElementById("AJAX-progress-meter");
		if(progressMeter){
			progressMeter.setAttribute("mode", "undetermined");
			progressMeter.setAttribute("hidden", "false");		
		}
	}
	
	/**
	* <p>Extract errors from XML doc.</p>
	* <p>Seek exception tag, if found, extract children nodes from tag and build a message.</p>
	* @param errorXML An XML document containing errors
	*/
	this.extractErrors = function(errorXML){
		var message = "";
		if(errorXML){
			var exceptionElements = errorXML.getElementsByTagName('exception');		
			if(exceptionElements.length > 0){
				var exceptionElement = exceptionElements[0];
				var childNodes = exceptionElement.childNodes;

				if(childNodes){
					for (var i = 0; i < childNodes.length; i++) {
						var textNode = childNodes[i];
			  			message = message + textNode.data;
					}
				}				
		  	}
 		}
		return message;
	}
}



var XUL_FACES_BRIDGE = new XULFacesBridge(document.location.href);

window.onload = function(){
    document.addEventListener('RadioStateChange', XUL_FACES_BRIDGE.onRadioChange,false);
    document.addEventListener('select', XUL_FACES_BRIDGE.onListboxSelect,false);
    document.addEventListener('select', XUL_FACES_BRIDGE.onTreeSelect,false);
    document.addEventListener('change', XUL_FACES_BRIDGE.onTextboxChange,false);
    document.addEventListener('command', XUL_FACES_BRIDGE.onCheckboxChange,false);
    document.addEventListener('command', XUL_FACES_BRIDGE.onDropDownChange,false);
}

/**
* <p>Trigger an action associated with a command.</p>
*/
function triggerAction(actionId){
	XUL_FACES_BRIDGE.executeAction(actionId);		
}

function triggerBindedMethod(componentId,eventName){
	XUL_FACES_BRIDGE.fireEventOnServerSide(componentId,eventName);	
}

/**
* <p>Trigger an asynchronous action associated with a command.</p>
*/
function triggerAsynchronousAction(actionId){
	XUL_FACES_BRIDGE.executeAsynchronousAction(actionId);		
}