function changeLocale() {

    try {
        var newLocale = "cs-CZ"

        // Write preferred locale to local user config
        var prefs = Components.classes["@mozilla.org/preferences-service;1"].
            getService(Components.interfaces.nsIPrefBranch);
        console.log(prefs)
        prefs.setCharPref("general.useragent.locale", newLocale);


        // Restart application
        var appStartup = Components.classes["@mozilla.org/toolkit/app-startup;1"]
            .getService(Components.interfaces.nsIAppStartup);

        appStartup.quit(Components.interfaces.nsIAppStartup.eRestart |
            Components.interfaces.nsIAppStartup.eAttemptQuit);

    } catch(err) {

        alert("Couldn't change locale: " + err);
    }
}