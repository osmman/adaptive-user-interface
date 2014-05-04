var params = {
    "return.model.name": "person.list",
    "ejb.name": "java:global/AdaptiveUI/ejb-1.0-SNAPSHOT/PersonService",
    "ejb.method.name": "findAll"
};
event.callHandler("integration.java.ejbMethodExecute", params);

var paramsGenerator = {
    "class.0": "cz.cvut.fel.aui.model.Person",
    "class.1": "cz.cvut.fel.aui.model.PersonInfo",
    "bundle.0": "/bundle/uip_locale_en.properties",
    "model.rewrite": "false",
    "form.readonly": "true"
//    "fields.ignore": "personInfo,id,firstname,lastname,address,degree"
};

var interfaceBuilder = buildersFactory.createBuilder("uips.builders.AbstractInterfaceBuilder");
interfaceBuilder.initializeInterface("person.list");
interfaceBuilder.introduceContainer(null, null);

interfaceBuilder.introduceContainer(null, null);

interfaceBuilder.introduceLabel();
interfaceBuilder.createProperty("Registered users", "title", null, null);
interfaceBuilder.finalizeLabel();

var modelsCount = client.getModelValue("person.list", null, "models.count");
modelsCount = parseInt(modelsCount);



for(i = 0; i < modelsCount; i++) {

    paramsGenerator["form.name"] = "person.list."+i;
    paramsGenerator["model.postfix"] = "person.list."+i;
    event.callHandler("integration.aui.generate", paramsGenerator);

    interfaceBuilder.introduceContainer(null, null);
    interfaceBuilder.introduceElement(null,"person.list."+i,null);
    interfaceBuilder.finalizeElement();

//    interfaceBuilder.introduceLabel();
//    interfaceBuilder.createProperty("User " + i, "title", null, null);
//    interfaceBuilder.finalizeLabel();
//
//    interfaceBuilder.introduceElement(null, "public.display", null);
//    interfaceBuilder.introduceLabel();
//    interfaceBuilder.createProperty("Name", "title", null, null);
//    interfaceBuilder.finalizeLabel();
//    interfaceBuilder.finalizeElement();
//
//    interfaceBuilder.introduceElement(null, "public.display", null);
//    interfaceBuilder.introduceLabel();
//    interfaceBuilder.createProperty("Name", "title", "cz.cvut.fel.aui.model.Person.person.list." + i + ":id", null);
//    interfaceBuilder.finalizeLabel();
//    interfaceBuilder.finalizeElement();
//
//    interfaceBuilder.introduceElement(null, "public.display", null);
//    interfaceBuilder.introduceLabel();
//    interfaceBuilder.createProperty("Surename", "title", null, null);
//    interfaceBuilder.finalizeLabel();
//    interfaceBuilder.finalizeElement();
//
//    interfaceBuilder.introduceElement(null, "public.display", null);
//    interfaceBuilder.introduceLabel();
//    interfaceBuilder.createProperty("Surename", "title", "cz.cvut.fel.aui.model.Person.person.list." + i + ":email", null);
//    interfaceBuilder.finalizeLabel();
//    interfaceBuilder.finalizeElement();


    interfaceBuilder.finalizeContainer();
}

interfaceBuilder.finalizeContainer();

interfaceBuilder.introduceElement(null, "public.trigger", null);
interfaceBuilder.introduceLabel();
interfaceBuilder.createProperty("Close", "title", null, null);
interfaceBuilder.finalizeLabel();
interfaceBuilder.introduceBehaviour("action", "cancel", true);
interfaceBuilder.finalizeBehaviour();
interfaceBuilder.finalizeElement();


interfaceBuilder.finalizeContainer();

client.updateCreatedObjects(interfaceBuilder);

client.updateModel("public.interfaces", null, "root", "public.interfaces:person.list", null);