
var modelNumber = event.getProperties()['model.number'];

var params = {};
params["return.model.name"] = "person.list";
params["ejb.name"] = "java:global/AdaptiveUI/ejb-1.0-SNAPSHOT/PersonService";
params["ejb.method.name"] = "findAll";
event.callHandler("integration.java.ejbMethodExecute", params);


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
    interfaceBuilder.introduceContainer(null, null);

    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("User " + i, "title", null, null);
    interfaceBuilder.finalizeLabel();

    interfaceBuilder.introduceElement(null, "public.display", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Name", "title", null, null);
    interfaceBuilder.finalizeLabel();
    interfaceBuilder.finalizeElement();

    interfaceBuilder.introduceElement(null, "public.display", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Name", "title", "cz.cvut.fel.aui.model.Person.person.list." + i + ":id", null);
    interfaceBuilder.finalizeLabel();
    interfaceBuilder.finalizeElement();

    interfaceBuilder.introduceElement(null, "public.display", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Surename", "title", null, null);
    interfaceBuilder.finalizeLabel();
    interfaceBuilder.finalizeElement();

    interfaceBuilder.introduceElement(null, "public.display", null);
    interfaceBuilder.introduceLabel();
    interfaceBuilder.createProperty("Surename", "title", "cz.cvut.fel.aui.model.Person.person.list." + i + ":email", null);
    interfaceBuilder.finalizeLabel();
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