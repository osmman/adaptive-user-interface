
var values = client.getModelValues("cz.cvut.fel.aui.model.Context.context.form",null,null);

server.logMessage(values["language"]);

client.updateModel("cz.cvut.fel.aui.model.Context", null, "age", values["age"]);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "language", values["language"]);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "country", values["country"]);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "screenSize", values["screenSize"]);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "device", values["device"]);

event.callHandler("integration.java.locale",{});

server.logMessage('..................... submit called ......................');

client.updateModel("public.interfaces", null, "root", "public.interfaces:root", null);
