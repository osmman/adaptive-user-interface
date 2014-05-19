package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.utils.ContextResources;
import cz.cvut.fel.aui.utils.context.DeviceInfo;
import cz.cvut.fel.aui.utils.context.Locale;
import cz.cvut.fel.aui.utils.context.User;
import cz.cvut.fel.aui.utils.validator.Validator;
import cz.cvut.fel.caf.ContextEvent;
import cz.cvut.fel.caf.ContextService;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */

@Model
public class ContextController extends BaseController {
    @Inject
    private Logger logger;

    @Inject
    private ContextService contextService;

    @Inject
    private Validator validator;

    private Context context;

    @Named
    @Produces
    public Context getContext() {
        return context;
    }

    public Date getToday() {
        return new Date();
    }

    @PostConstruct
    public void initContext() {
        context = createModel(contextService.getContext());
    }

    public String save() {
        validator.validate(context);

        contextService.addContextItem(
                ContextResources.LOCALE,
                new Locale(context.getCountry(), context.getLanguage())
        );
        contextService.addContextItem(
                ContextResources.DEVICE,
                new DeviceInfo(context.getDevice(), context.getScreenSize())
        );
        contextService.addContextItem(
                ContextResources.USER,
                new User(context.getAge())
        );
        return redirect("context");
    }

    public void onContextChanged(@Observes(notifyObserver = Reception.IF_EXISTS) ContextEvent event) {
        //initContext();
        facesMessage("context changed");
        logger.info("Context changed " + event.toString());
    }

    private Context createModel(cz.cvut.fel.caf.Context ctx) {
        Context model = new Context();
        DeviceInfo deviceInfo = (DeviceInfo) ctx.getContextItem(ContextResources.DEVICE);
        User user = (User) ctx.getContextItem(ContextResources.USER);
        Locale locale = (Locale) ctx.getContextItem(ContextResources.LOCALE);

        model.setCountry(locale.getCountry());
        model.setLanguage(locale.getLanguage());
        model.setAge(user.getAge());
        model.setDevice(deviceInfo.getDevice());
        model.setScreenSize(deviceInfo.getScreenSize());

        return model;
    }
}
