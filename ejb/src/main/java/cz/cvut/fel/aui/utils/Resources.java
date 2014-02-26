package cz.cvut.fel.aui.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class Resources
{
    @PersistenceContext
    private EntityManager em;

    @Produces
    public EntityManager getEm()
    {
        return em;
    }

    @Produces
    public Logger getLogger(InjectionPoint ip)
    {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }
}
