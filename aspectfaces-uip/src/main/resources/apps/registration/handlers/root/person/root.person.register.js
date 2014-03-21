
var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Person";
params["class.1"] = "cz.cvut.fel.aui.model.PersonInfo";
params["class.2"] = "cz.cvut.fel.aui.model.Address";
params["bundle.0"] = "/bundle/locale_en.properties";
params["form.name"] = "person.register";
params["model.postfix"] = "person.register";
params["handler.submit"] = "submit.user";
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "true";
params["form.readonly"] = "false";
params["client.notify.uiUpdate"] = "false";

event.callHandler("integration.aui.generate", params);

client.updateModel("public.interfaces", null, "person.register", null, null);
client.updateModel("public.interfaces", null, "root", "public.interfaces:person.register", null);