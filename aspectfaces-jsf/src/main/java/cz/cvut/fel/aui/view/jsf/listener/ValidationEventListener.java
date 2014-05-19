package cz.cvut.fel.aui.view.jsf.listener;

import cz.cvut.fel.aui.utils.ContextResources;
import cz.cvut.fel.aui.utils.FacUtil;
import cz.cvut.fel.aui.utils.context.User;
import cz.cvut.fel.caf.ContextService;

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
        logger.info(failed.toString());
        ContextService service = (ContextService) FacUtil.getBeanByClass(ContextService.class);
        User user = (User) service.getContext().getContextItem(ContextResources.USER);
        if (failed) {
            user.invalidForm();
            service.addContextItem(ContextResources.USER, user);
        } else {
            user.rightForm();
            service.addContextItem(ContextResources.USER, user);
        }

    }

    @Override
    public boolean isListenerForSource(Object source) {
        return true;
    }
}
