
client.updateModel("cz.cvut.fel.aui.model.Person.person.registration", null, "personInfo", "cz.cvut.fel.aui.model.PersonInfo.person.registration");
client.updateModel("cz.cvut.fel.aui.model.PersonInfo.person.registration", null, "address", "cz.cvut.fel.aui.model.Address.person.registration");
client.updateModel("cz.cvut.fel.aui.model.PersonInfo.person.registration", null, "degree", "cz.cvut.fel.aui.model.Degree.person.registration");

server.logMessage('---------------------------------------------------------')

server.logMessage(client.getModelValues("cz.cvut.fel.aui.model.Address.person.registration",null,null));

//var params = {};
//params["return.model.name"] = "person.registration.result";
//params["ejb.name"] = "java:global/AdaptiveUI/ejb-1.0-SNAPSHOT/PersonService";
//params["ejb.method.name"] = "create";
//event.callHandler("integration.java.ejbMethodExecute", params);

var params = {};
event.callHandler("person.registration.create", params);



//event.callHandler("integration.java.entityUpdateCreate", params);

server.logMessage('..................... submit called ......................');

client.updateModel("public.interfaces", null, "root", "public.interfaces:root", null);
