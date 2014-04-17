
var properties = event.getProperties();

client.updateModel("cz.cvut.fel.aui.model.Context",null,"language",properties["language"] || "en");

event.callHandler("integration.java.locale",{});