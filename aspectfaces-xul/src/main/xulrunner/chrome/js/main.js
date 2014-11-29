

function openRegistrationWizard() {
    window.openDialog('chrome://aui/content/registration.xul',"registration-wizard","chrome, dialog, modal");
}

function openContextWizard() {
    openDialogFn('context.xul', 400, 400);
}

function saveContext() {
    try {
        XUL_FACES_BRIDGE.serverUrl = _server + "/overlay/context.xul"
        triggerAction('cmd-context-save');
        Aui.onContextChange();
        Navigation.overlay(document,_server +  "/overlay/context.xul",null)
    } catch (e) {
        alert(e);
    } finally {
        XUL_FACES_BRIDGE.serverUrl = _server;
    }
}

function deletePerson() {
    try {
        if (dialogDelete()) {
            XUL_FACES_BRIDGE.serverUrl =  _server + "/overlay/people.xul"
            triggerAction('cmd-delete-person');
            document.location.reload()
        }
    } catch (e) {
        alert(e);
    } finally {
        XUL_FACES_BRIDGE.serverUrl = _server;
    }
}

function detailPerson() {
    var selected = document.querySelector("listbox listitem[selected]");
    if (selected) {
        var id = selected.getAttribute("value");
        if (id) {
            window.location.replace(_server+'/person.xul?person=' + id);
            //window.openDialog(server+'/person.xul?person=' + id,"detail","chrome, dialog");
        }
    }
}

function dialogDelete() {
    return confirm("Delete?");
}