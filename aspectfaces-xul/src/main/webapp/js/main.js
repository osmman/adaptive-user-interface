
function openWizardWindow(url,width,height){
    window.open(url,'properties','menubar=no,location=yes,resizable=yes,scrollbars=yes,status=yes,width=' + width +  ',height=' + height + ',modal=yes');
}

function openRegistrationWizard(){
    openWizardWindow('registration.xul',400,350);
}

function openContextWizard(){
    openWizardWindow('context.xul',400,400);
}

function saveContext(){
    try{
        XUL_FACES_BRIDGE.serverUrl = document.location.origin+"/aspectfaces-xul/layouts/context.xul"
        triggerAction('cmd-context-save');
        XUL_FACES_BRIDGE.updateDOM(XUL_FACES_BRIDGE.responseXml);
    }catch(e){
        alert(e);
    } finally {
        XUL_FACES_BRIDGE.serverUrl = document.location.href;
    }
}