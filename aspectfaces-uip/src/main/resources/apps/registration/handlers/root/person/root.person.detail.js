var lang = client.getModelValue("public.user",null,"locale.language");
var context = client.getModelValues("cz.cvut.fel.aui.model.Context",null,null);

var modelNumber = event.getProperties()['model.number'];

var p = client.getModelValues("cz.cvut.fel.aui.model.Person.person.list."+modelNumber, null, null);
server.logMessage("------------------------------------------------------------------------------------------------------")
var pi = p["personInfo"];

event.callHandler("integration.aui.generate", {
    "class.0": "cz.cvut.fel.aui.model.Person",
    "class.1": "cz.cvut.fel.aui.model.PersonInfo",
    "class.2": "cz.cvut.fel.aui.model.Address",
    "class.3": "cz.cvut.fel.aui.model.Degree",
    "bundle.0": "/bundle/uip_locale_"+lang+".properties",
    "form.name": "person.detail",
    "model.postfix": "person.list." + modelNumber,
    "handler.cancel": "cancel",
    "model.rewrite": "true",
    "form.readonly": "true",
    "fields.ignore": "password,personInfo,id,fullName,address,degree",
    "form.layout": "layouts/detail.xml",
    "client.notify.uiUpdate": "false"
});

event.callHandler("integration.java.ejbMethodExecute", {
    "return.model.name": "cz.cvut.fel.aui.model.PersonInfo.person.list",
    "ejb.name": "java:global/AdaptiveUI/aspectfaces-uip-1.0-SNAPSHOT/EntityService",
    "ejb.method.name": "find",
    "param.name.1": "id",
    "param.value.1": "1",
    "param.type.1": "java.lang.Long",
    "param.name.0": "clazz",
    "param.value.0": "cz.cvut.fel.aui.model.PersonInfo",
    "param.type.0": "java.lang.String"
});

client.updateModel("public.interfaces", null, "root", "public.interfaces:person.detail", null);
