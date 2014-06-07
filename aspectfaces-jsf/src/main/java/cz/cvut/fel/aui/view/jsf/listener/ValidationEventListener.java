package cz.cvut.fel.aui.view.jsf.listener;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.service.ContextService;
import cz.cvut.fel.aui.utils.FacUtil;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import java.util.logging.Logger;

public class ValidationEventListener implements SystemEventListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        Boolean failed = FacesContext.getCurrentInstance().isValidationFailed();
        ContextService service = (ContextService) FacUtil.getBeanByClass(ContextService.class);
        Context context = service.getContext();
        if(failed){
            context.setInvalid(context.getInvalid()+1);
            service.save(context);
        }else if(context.getInvalid() > 0) {
            context.setInvalid(context.getInvalid()-1);
            service.save(context);
        }

    }

    @Override
    public boolean isListenerForSource(Object source) {
        return true;
    }
}
