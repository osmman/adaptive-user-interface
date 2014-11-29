this.EXPORTED_SYMBOLS = ["Binding"];

const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

Cu.import('resource://modules/MyServices.jsm');

this.Binding = {
    regist: function (uri) {
        if (!MyServices.StyleSheet.sheetRegistered(uri, MyServices.StyleSheet.USER_SHEET)) {
            MyServices.StyleSheet.loadAndRegisterSheet(uri, MyServices.StyleSheet.USER_SHEET);
        }
    },

    unregist: function (uri) {
        if (MyServices.StyleSheet.sheetRegistered(uri, MyServices.StyleSheet.USER_SHEET)) {
            MyServices.StyleSheet.unregisterSheet(uri, MyServices.StyleSheet.USER_SHEET);
        }
    }
}