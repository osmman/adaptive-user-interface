const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

Cu.import('resource://gre/modules/XPCOMUtils.jsm');

EXPORTED_SYMBOLS = ["MyServices"];

var MyServices = {};

XPCOMUtils.defineLazyGetter(MyServices, 'StyleSheet', function(){
    return Cc['@mozilla.org/content/style-sheet-service;1'].getService(Ci.nsIStyleSheetService)
});

XPCOMUtils.defineLazyGetter(MyServices, 'References', function(){
    return Cc['@mozilla.org/preferences-service;1'].getService(Ci.nsIPrefBranch)
});

XPCOMUtils.defineLazyGetter(MyServices, 'AppStartup', function(){
    return Cc['@mozilla.org/toolkit/app-startup;1'].getService(Ci.nsIAppStartup)
});

XPCOMUtils.defineLazyGetter(MyServices, 'WindowWatcher', function(){
    return Cc["@mozilla.org/embedcomp/window-watcher;1"].getService(Ci.nsIWindowWatcher)
});
