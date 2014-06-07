package cz.cvut.fel.aui.utils;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Tomáš on 26.2.14.
 */
public class WebResources {

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @RequestScoped
    public ELContext produceELContext(FacesContext facesContext){
        return facesContext.getELContext();
    }
}
