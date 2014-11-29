this.EXPORTED_SYMBOLS = ["Aui"];

const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

Cu.import('resource://modules/MyServices.jsm');

this.Aui = {

    config: {},

    onContextChange: function () {
        var url = "http://localhost:8080/aspectfaces-xul/rdf/adaptation";
        var request = Cc["@mozilla.org/xmlextras/xmlhttprequest;1"].createInstance(Components.interfaces.nsIXMLHttpRequest);
        request.onload = function(aEvent){
            var text = aEvent.target.responseText;
            var jsObject = JSON.parse(text);
            Aui.config = jsObject;
            Aui.handleConfig(jsObject);
        }
        request.open("GET", url, true);
        request.send(null);
    },

    handleConfig: function(config){

    }

}