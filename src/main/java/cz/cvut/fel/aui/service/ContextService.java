package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import cz.cvut.fel.aui.utils.validator.Validator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.beans.Transient;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 30.11.13
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */

@SessionScoped
public class ContextService implements Serializable
{
    @Inject
    private transient Logger logger;

    @Inject
    private transient Validator validator;

    private Context context;

    @Inject
    private Event<Context> contextChanged;

    public Context getContext()
    {
        return context.clone();
    }

    @PostConstruct
    public void newContext()
    {
        logger.info("created context");
        context = new Context();
        context.setAge(Age.STUDENT);
        context.setCountry("CZ");
        context.setLanguage("cs");
        context.setDevice(Device.DESKTOP);
        context.setScreenSize(ScreenSize.wide);
        save(context);
    }

    public void save(Context context)
    {
        validator.validate(context);
        this.context = context.clone();
        contextChanged.fire(getContext());
    }
}
