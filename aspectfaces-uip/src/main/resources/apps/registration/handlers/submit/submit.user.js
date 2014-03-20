
client.updateModel("cz.cvut.fel.aui.model.Person.person.register", null, "personInfo", "cz.cvut.fel.aui.model.PersonInfo.person.register");
client.updateModel("cz.cvut.fel.aui.model.Person..person.register", null, "address", "cz.cvut.fel.aui.model.Address.person.register");

var params = {};
params["model.0"] = "cz.cvut.fel.aui.model.Person.person.register";
params["model.1"] = "cz.cvut.fel.aui.model.PersonInfo.person.register";
params["model.2"] = "cz.cvut.fel.aui.model.Address.person.register";
event.callHandler("integration.java.entityUpdateCreate", params);

server.logMessage('..................... submit called ......................');

client.updateModel("public.interfaces", null, "root", "public.interfaces:root", null);
