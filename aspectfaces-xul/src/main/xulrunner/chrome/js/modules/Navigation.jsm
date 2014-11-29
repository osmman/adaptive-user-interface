this.EXPORTED_SYMBOLS = ["Navigation"];

const Ci = Components.interfaces;
const Cc = Components.classes;
const Cu = Components.utils;

Cu.import('resource://modules/MyServices.jsm');

this.Navigation = {
    overlay: function(document, uri, callback){
        document.getElementById("content").innerHTML = "";
        document.loadOverlay(uri, callback);
    }
}