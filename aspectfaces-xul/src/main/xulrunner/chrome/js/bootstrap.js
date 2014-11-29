const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

const DEBUG = true;
(function (enabled) {
    if (!enabled) return;
    var windowtype = null;
    Cu.import('resource://gre/modules/devtools/dbg-server.jsm');
    DebuggerServer.chromeWindowType = windowtype;
    if (!DebuggerServer.initialized) {
        DebuggerServer.init();
        DebuggerServer.addBrowserActors(windowtype);
    }
    DebuggerServer.openListener(6000);
})(DEBUG);

Cu.import('resource://gre/modules/Services.jsm');
Cu.import('resource://modules/MyServices.jsm');
Cu.import('resource://modules/Binding.jsm');
Cu.import('resource://modules/Locale.jsm');
Cu.import('resource://modules/Navigation.jsm');
Cu.import('resource://modules/Aui.jsm');

_server = "http://localhost:8080/aspectfaces-xul";

imageBinding = Services.io.newURI("chrome://aui/skin/images.css", null, null);
helpBinding = Services.io.newURI("chrome://aui/skin/help.css", null, null);

function startup() {
    Aui.onContextChange();
}

Aui.handleConfig = function (config) {


    if (config["applyHelp"]) {
        Binding.regist(helpBinding);
    } else {
        Binding.unregist(helpBinding);
    }

    if (config["applyImage"]) {
        Binding.regist(imageBinding);
    } else {
        Binding.unregist(imageBinding);
    }

    //Locale.change(config["locale"])
}