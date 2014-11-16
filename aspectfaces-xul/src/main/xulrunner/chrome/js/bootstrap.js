const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

const DEBUG = true;
(function(enabled) {
    if(!enabled) return;
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
Cu.import('resource://modules/Locale.jsm');
Cu.import('resource://modules/Binding.jsm');

function startup(){
    var cssUri = Services.io.newURI("chrome://aui/skin/binding.css",null,null);
    Binding.regist(cssUri)
}