
function openWizardWindow(url,width,height){
    window.open(url,'properties','menubar=no,location=yes,resizable=yes,scrollbars=yes,status=yes,width=' + width +  ',height=' + height + ',modal=yes');
}

function openRegistrationWizard(){
    openWizardWindow('registration.xul',400,350);
}

function openContextWizard(){
    openWizardWindow('context.xul',400,400);
}