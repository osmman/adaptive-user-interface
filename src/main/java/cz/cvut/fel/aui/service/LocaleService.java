package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.Context;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 30.11.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */


@SessionScoped
public class LocaleService implements Serializable
{
    @Inject
    private Logger logger;

    @Named
    @Produces
    private Locale locale;

    /**
     * @todo doresit injection contextu
     */
    private Context context;

    @Inject
    private transient FacesContext facesContext;

    public void onContextChanged(@Observes(notifyObserver = Reception.ALWAYS) Context context)
    {
        locale = new Locale.Builder()
                .setLanguage(context.getLanguage())
                .setRegion(context.getCountry())
                .build();
        facesContext.getViewRoot().setLocale(locale);
    }


    @PostConstruct
    private void initLocale()
    {
        locale = facesContext.getViewRoot().getLocale();
    }
}
