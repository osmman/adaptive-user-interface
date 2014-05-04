
var params = {};
params["return.model.name"] = "test.context";
params["ejb.name"] = "java:global/AdaptiveUI/ejb-1.0-SNAPSHOT/ContextService";
params["ejb.method.name"] = "getContext";
event.callHandler("integration.java.ejbMethodExecute", params);

server.logMessage(client.getModelValues("test.context",null,null));

var lang = client.getModelValue("public.user",null,"locale.language");
var context = client.getModelValues("cz.cvut.fel.aui.model.Context",null,null);

var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Context";
params["bundle.0"] = "/bundle/uip_locale_"+lang+".properties";
params["form.name"] = "context.form";
params["model.postfix"] = "context.form";
params["handler.submit"] = "submit.context";
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "true";
params["form.readonly"] = "false";
params["form.layout"] = "layouts/form.xml";
params["client.notify.uiUpdate"] = "false";

event.callHandler("integration.aui.generate", params);

client.updateModel("cz.cvut.fel.aui.model.Context.context.form", null, "age", context["age"]);
client.updateModel("cz.cvut.fel.aui.model.Context.context.form", null, "language", context["language"]);
client.updateModel("cz.cvut.fel.aui.model.Context.context.form", null, "country", context["country"]);
client.updateModel("cz.cvut.fel.aui.model.Context.context.form", null, "screenSize", context["screenSize"]);
client.updateModel("cz.cvut.fel.aui.model.Context.context.form", null, "device", context["device"]);

client.updateModel("public.interfaces", null, "context.form", null, null);
client.updateModel("public.interfaces", null, "root", "public.interfaces:context.form", null);