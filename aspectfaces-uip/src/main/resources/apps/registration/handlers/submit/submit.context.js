
var values = client.getModelValues("cz.cvut.fel.aui.model.Context.context.form",null,null);
var age = client.getModelValue("cz.cvut.fel.aui.model.Context.age.enum.conversion", null, values["age"]);
var screenSize = client.getModelValue("cz.cvut.fel.aui.model.Context.screenSize.enum.conversion", null, values["screenSize"]);
var device = client.getModelValue("cz.cvut.fel.aui.model.Context.device.enum.conversion", null, values["device"]);

client.updateModel("cz.cvut.fel.aui.model.Context", null, "age", age);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "language", values["language"]);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "country", values["country"]);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "screenSize", screenSize);
client.updateModel("cz.cvut.fel.aui.model.Context", null, "device", device);

event.callHandler("integration.java.locale",{});

server.logMessage('..................... submit called ......................');

client.updateModel("public.interfaces", null, "root", "public.interfaces:root", null);
