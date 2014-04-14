
var lang = client.getModelValue("public.user",null,"locale.language");
var context = client.getModelValues("aui.context",null,null);

var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Context";
params["bundle.0"] = "/bundle/uip_locale_"+lang+".properties";
params["form.name"] = "context.form";
params["model.postfix"] = "context.form";
params["handler.submit"] = "submit.form";
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "true";
params["form.readonly"] = "false";
params["form.layout"] = "layouts/form.xml";
params["client.notify.uiUpdate"] = "false";

event.callHandler("integration.aui.generate", params);

client.updateModel("public.interfaces", null, "context.form", null, null);
client.updateModel("public.interfaces", null, "root", "public.interfaces:context.form", null);