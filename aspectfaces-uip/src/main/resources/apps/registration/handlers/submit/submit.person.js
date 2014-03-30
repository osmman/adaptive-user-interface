
//client.updateModel("cz.cvut.fel.aui.model.Person.person.register", null, "personInfo", "cz.cvut.fel.aui.model.PersonInfo.person.register");
//client.updateModel("cz.cvut.fel.aui.model.Person.person.register", null, "address", "cz.cvut.fel.aui.model.Address.person.register");

//var params = {};
//params["model.0"] = "cz.cvut.fel.aui.model.Person.person.register";
//params["model.1"] = "cz.cvut.fel.aui.model.PersonInfo.person.register";
//params["model.2"] = "cz.cvut.fel.aui.model.Address.person.register";


var params = {};
params["return.model.name"] = "person.list";
params["param.name.0"] = "entity";
params["param.value.0"] = client.getModelValues("cz.cvut.fel.aui.model.Person.person.register",null,null);
params["param.type.0"] = "cz.cvut.fel.aui.model.Person";
params["ejb.name"] = "java:global/AdaptiveUI/ejb-1.0-SNAPSHOT/PersonService";
params["ejb.method.name"] = "create";
event.callHandler("integration.java.ejbMethodExecute", params);

//event.callHandler("integration.java.entityUpdateCreate", params);

server.logMessage('..................... submit called ......................');

client.updateModel("public.interfaces", null, "root", "public.interfaces:root", null);
