
var lang = client.getModelValue("public.user",null,"locale.language");
var context = client.getModelValues("cz.cvut.fel.aui.model.Context",null,null);

var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Person";
params["class.1"] = "cz.cvut.fel.aui.model.PersonInfo";
params["class.2"] = "cz.cvut.fel.aui.model.Address";
params["class.3"] = "cz.cvut.fel.aui.model.Degree";
params["bundle.0"] = "/bundle/uip_locale_"+lang+".properties";
params["form.name"] = "person.registration";
params["model.postfix"] = "person.registration";
params["handler.submit"] = "submit.person";
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "true";
params["form.readonly"] = "false";
params["form.layout"] = "layouts/form.xml";
params["fields.ignore"] = "personInfo,id,fullName,address,degree";
params["security.roles"] = context["age"].toLowerCase();
// missing AF profile
params["client.notify.uiUpdate"] = "false";

event.callHandler("integration.aui.generate", params);

client.updateModel("public.interfaces", null, "person.registration", null, null);
client.updateModel("public.interfaces", null, "root", "public.interfaces:person.registration", null);