package cz.cvut.fel.aui.view;

import cz.cvut.fel.aui.model.Context;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 1.12.13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */

@RequestScoped
public class MessageService
{

    @Inject
    private FacesContext facesContext;

    protected FacesMessage facesMessage(String text){
        return facesMessage("", text, FacesMessage.SEVERITY_INFO);
    }

    protected FacesMessage facesMessage(String title, String text, FacesMessage.Severity severity) {
        return facesMessage("growl", title, text, severity);
    }

    protected FacesMessage facesMessage(String id, String title, String text, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, title, text);
        facesContext.addMessage(id, message);
        return message;
    }

    public void onContextChanged(@Observes(notifyObserver = Reception.ALWAYS) Context context)
    {
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Context changed","");
        facesContext.addMessage(null, m);
    }

}
