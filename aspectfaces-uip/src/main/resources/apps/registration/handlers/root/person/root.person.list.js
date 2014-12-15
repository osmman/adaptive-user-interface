
var modelNumber = event.getProperties()['model.number'];

event.callHandler("integration.java.ejbMethodExecute", {
    "return.model.name": "person.list",
    "ejb.name": "java:global/AdaptiveUI/ejb-1.0-SNAPSHOT/PersonService",
    "ejb.method.name": "findAll"
});

var interfaceBuilder = buildersFactory.createBuilder("uips.builders.AbstractInterfaceBuilder");
interfaceBuilder.initializeInterface("person.list");
interfaceBuilder.introduceContainer(null, null);

interfaceBuilder.introduceContainer(null, null);

interfaceBuilder.introduceLabel();
interfaceBuilder.createProperty("Registered users", "title", null, null);
interfaceBuilder.finalizeLabel();

var model = client.getModelValues("person.list", null, null);
var modelsCount = client.getModelValue("person.list", null, "models.count");
modelsCount = parseInt(modelsCount);

for(i = 0; i < modelsCount; i++) {
    interfaceBuilder.introduceContainer(null, null);

    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("User " + i, "title", "cz.cvut.fel.aui.model.Person.person.list." + i + ":id", null);
    interfaceBuilder.finalizeLabel();

    interfaceBuilder.introduceElement(null, "public.display", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Email", "title", "locale.bundle:person.email", null);
    interfaceBuilder.finalizeLabel();
    interfaceBuilder.finalizeElement();

    interfaceBuilder.introduceElement(null, "public.display", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Email", "title", "cz.cvut.fel.aui.model.Person.person.list." + i + ":email", null);
    interfaceBuilder.finalizeLabel();
    interfaceBuilder.finalizeElement();

    interfaceBuilder.introduceElement(null, "public.trigger", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Detail", "title", null, null);
    interfaceBuilder.finalizeLabel();
    interfaceBuilder.introduceBehaviour("action", "root.person.detail", true);
    interfaceBuilder.createProperty("" + i, "model.number", null, null);
    interfaceBuilder.finalizeBehaviour();
    interfaceBuilder.finalizeElement();

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