
var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Context";
params["bundle.0"] = "/bundle/uip_locale_en.properties";
params["form.name"] = "context.form";
params["model.postfix"] = "form";
params["handler.submit"] = "submit.form";
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "true";
params["form.readonly"] = "false";
params["form.layout"] = "layouts/form.xml";
params["client.notify.uiUpdate"] = "true";

event.callHandler("integration.aui.generate", params);



//var text = client.getModelValue("locale.bundle",{"language":"cs"},"state");
//var lang = client.getModelValue("public.user",null,"locale.language");

//var sst = server.getModelValue("context",null,"age");

//server.logMessage(text);
//
//server.logMessage(lang);

//server.logMessage(sst);

//client.updateModel("cz.cvut.fel.aui.model.Context.context.form",null,"age.value","1");
client.updateModel("public.interfaces", null, "context.form", null, null);
client.updateModel("public.interfaces", null, "root", "public.interfaces:context.form", null);