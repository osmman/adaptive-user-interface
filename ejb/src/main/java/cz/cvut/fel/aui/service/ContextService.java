package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import cz.cvut.fel.aui.utils.validator.Validator;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */

@Stateful
@SessionScoped
public class ContextService implements Serializable {
    @Inject
    private Logger logger;

    @Inject
    private Validator validator;

    private Context context;

    @Inject
    private Event<Context> contextChanged;

    @Produces
    @Current
    public Context getContext() {
        return context.clone();
    }

    @PostConstruct
    public void newContext() {
        logger.info("created context");
        context = new Context();
        context.setAge(Age.STUDENT);
        context.setCountry("US");
        context.setLanguage("en");
        context.setDevice(Device.DESKTOP);
        context.setScreenSize(ScreenSize.wide);
    }

    public void save(Context context) {
        validator.validate(context);
        this.context = context.clone();
        logger.info("CONTEXT SAVED");
        contextChanged.fire(getContext());
    }
}
