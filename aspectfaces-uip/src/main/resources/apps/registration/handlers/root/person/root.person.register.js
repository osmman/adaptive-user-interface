
var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Person";
//params["class.1"] = "cz.cvut.fel.aui.model.PersonInfo";
//params["class.2"] = "cz.cvut.fel.aui.model.Address";
params["bundle.0"] = "/bundle/uip_locale_en.properties";
params["form.name"] = "person.register";
params["model.postfix"] = "person.register";
params["handler.submit"] = "submit.person";
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "true";
params["form.readonly"] = "false";
params["form.layout"] = "layouts/form.xml";
params["fields.ignore"] = "personInfo,id";
params["client.notify.uiUpdate"] = "false";

event.callHandler("integration.aui.generate", params);

//params["class.0"] = "cz.cvut.fel.aui.model.PersonInfo";
//params["form.name"] = "interface.personInfo.form";
//event.callHandler("integration.aui.generate", params);
//
//params["class.0"] = "cz.cvut.fel.aui.model.Person";
//params["form.name"] = "interface.person.form";
//event.callHandler("integration.aui.generate", params);
//
//params["class.0"] = "cz.cvut.fel.aui.model.Address";
//params["form.name"] = "interface.address.form";
//event.callHandler("integration.aui.generate", params);

client.updateModel("public.interfaces", null, "person.register", null, null);
client.updateModel("public.interfaces", null, "root", "public.interfaces:person.register", null);