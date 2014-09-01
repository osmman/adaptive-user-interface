package cz.cvut.fel.aui.controller.xul;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.service.ContextService;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.logging.Logger;

@Model
public class ContextController extends BaseController
{
    @Inject
    private Logger logger;

    @Inject
    private ContextService contextService;

    private Context context;

    @Named
    @Produces
    public Context getContext(){
        return context;
    }

    public Date getToday(){
        return new Date();
    }

    @PostConstruct
    public void initContext(){
        context = contextService.getContext();
    }

    public void save(){
        contextService.save(context);
        //return redirect("context");
    }

    public void onContextChanged(@Observes(notifyObserver = Reception.ALWAYS) Context context){
        this.context = context;
        facesMessage("context changed");
        logger.info("Context changed "+context.toString());
    }
}
