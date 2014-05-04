
var modelNumber = event.getProperties()['model.number'];

event.callHandler("integration.java.ejbMethodExecute", {
    "return.model.name": "personInfo",
    "ejb.name": "java:global/AdaptiveUI/aspectfaces-uip-1.0-SNAPSHOT/EntityService",
    "ejb.method.name": "find",
    "param.name.1": "id",
    "param.value.1": "1",
    "param.tape.1": "Long",
    "param.name.0": "clazz",
    "param.value.0": "cz.cvut.fel.aui.model.PersonInfo",
    "param.type.0": "String"
});

var params = {};
params["class.0"] = "cz.cvut.fel.aui.model.Person";
params["class.1"] = "cz.cvut.fel.aui.model.PersonInfo";
params["class.2"] = "cz.cvut.fel.aui.model.Address";
params["class.3"] = "cz.cvut.fel.aui.model.Degree";
params["bundle.0"] = "/bundle/uip_locale_en.properties";
params["form.name"] = "person.registration";
params["model.postfix"] = "person.list." + modelNumber;
params["handler.cancel"] = "cancel";
params["model.rewrite"] = "false";
params["form.readonly"] = "true";
params["fields.ignore"] = "password,personInfo,id,fullName,address,degree";
params["field.email.name"] = "true";
params["form.layout"] = "layouts/detail.xml";
params["client.notify.uiUpdate"] = "false";
event.callHandler("integration.aui.generate", params);

client.updateModel("public.interfaces", null, "root", "public.interfaces:person.registration", null);
