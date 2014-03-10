package cz.cvut.fel.aui.controller.xul;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController
{

    @Inject
    private FacesContext facesContext;

    protected String redirect(String view, Boolean viewPart)
    {
        return view + "?faces-redirect=true" + (viewPart ? "&includeViewParams=true" : "");
    }

    protected String redirect(String view)
    {
         return redirect(view,false);
    }

    protected FacesMessage facesMessage(String text)
    {
        return facesMessage(text, text, FacesMessage.SEVERITY_INFO);
    }

    protected FacesMessage facesMessage(String title, String text, FacesMessage.Severity severity)
    {
        return facesMessage(null, title, text, severity);
    }

    protected FacesMessage facesMessage(String id, String title, String text, FacesMessage.Severity severity)
    {
        FacesMessage message = new FacesMessage(severity, title, text);
        facesContext.addMessage(id, message);
        return message;
    }
}
