
function openDialog(url,width,height){
    window.open(url,'properties','menubar=no,centerscreen,location=yes,resizable=yes,scrollbars=yes,status=no,width=' + width +  ',height=' + height + ',modal=yes');
}

function openRegistrationWizard(){
    openDialog('registration.xul',400,350);
}

function openContextWizard(){
    openDialog('context.xul',400,400);
}

function saveContext(){
    try{
        XUL_FACES_BRIDGE.serverUrl = document.location.origin+"/aspectfaces-xul/layouts/context.xul"
        triggerAction('cmd-context-save');
        //document.location.reload()
    }catch(e){
        alert(e);
    } finally {
        XUL_FACES_BRIDGE.serverUrl = document.location.href;
    }
}

function deletePerson(){
    try{
        if(dialogDelete()){
            XUL_FACES_BRIDGE.serverUrl = document.location.origin+"/aspectfaces-xul/layouts/people.xul"
            triggerAction('cmd-delete-person');
            document.location.reload()
        }
    }catch(e){
        alert(e);
    } finally {
        XUL_FACES_BRIDGE.serverUrl = document.location.href;
    }
}

function detailPerson(){
    var selected = document.querySelector("listbox listitem[selected]");
    if(selected){
        var id = selected.getAttribute("value");
        if(id){
            openDialog('person.xul?person='+id,400,400);
        }
    }
}

function dialogDelete(){
    return confirm("Delete?");
}