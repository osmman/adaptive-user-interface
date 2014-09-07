this.EXPORTED_SYMBOLS = ["Binding"];

const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

Cu.import('resource://modules/MyServices.jsm');

this.Binding = {
    regist: function(cssUri){
        MyServices.StyleSheet.loadAndRegisterSheet(cssUri, MyServices.StyleSheet.USER_SHEET);
    },

    unregist: function(cssUri){
        MyServices.StyleSheet.unregisterSheet(cssUri, MyServices.StyleSheet.USER_SHEET);
    }
}