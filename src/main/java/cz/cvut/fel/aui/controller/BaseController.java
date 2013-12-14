package cz.cvut.fel.aui.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController
{

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
        return facesMessage("", text, FacesMessage.SEVERITY_INFO);
    }

    protected FacesMessage facesMessage(String title, String text, FacesMessage.Severity severity)
    {
        return facesMessage("growl", title, text, severity);
    }

    protected FacesMessage facesMessage(String id, String title, String text, FacesMessage.Severity severity)
    {
        /**
         * @todo překlady
         */
        FacesMessage message = new FacesMessage(severity, title, text);
        FacesContext.getCurrentInstance().addMessage(id, message);
        return message;
    }
}
