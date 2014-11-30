

function openRegistrationWizard() {
    window.openDialog('chrome://aui/content/registration.xul',"registration-wizard","chrome, dialog, modal");
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
            window.location.reload()
        }
    } catch (e) {
        alert(e);
    } finally {
        XUL_FACES_BRIDGE.serverUrl = _server;
    }
}

function detailPerson() {
    XUL_FACES_BRIDGE.serverUrl =  _server + "/overlay/people.xul"
    var selected = document.querySelector("listbox listitem[selected]");
    if (selected) {
        var id = selected.getAttribute("value");
        if (id) {
            window.openDialog('chrome://aui/content/detail.xul',"detail","dialog,chrome",id);
        }
    }
}

function dialogDelete() {
    return confirm("Delete?");
}