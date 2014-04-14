/**
 * Created by Tomáš on 11.4.14.
 */
var properties = event.getProperties();

server.logMessage(properties["interface"]);

//client.updateModel("public.interfaces", null, "person.registration", null, null);
client.updateModel("public.interfaces", null, "registration", "public.interfaces:"+properties["interface"], null);