this.EXPORTED_SYMBOLS = ["Locale"];

const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

Cu.import('resource://modules/MyServices.jsm');

this.Locale = {
    change: function (newLocale) {
        MyServices.References.setCharPref("general.useragent.locale", newLocale);
        MyServices.AppStartup.quit(Ci.nsIAppStartup.eRestart | Ci.nsIAppStartup.eAttemptQuit);
    }
};

