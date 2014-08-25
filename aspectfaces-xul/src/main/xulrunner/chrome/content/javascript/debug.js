(function() {

    var windowtype = null;
    Components.utils.import('resource://gre/modules/devtools/dbg-server.jsm');
    DebuggerServer.chromeWindowType = windowtype;
    if (!DebuggerServer.initialized) {
        DebuggerServer.init();
        DebuggerServer.addBrowserActors(windowtype);
    }
    DebuggerServer.openListener(6000);

})();