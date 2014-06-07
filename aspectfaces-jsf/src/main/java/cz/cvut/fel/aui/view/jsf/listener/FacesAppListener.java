package cz.cvut.fel.aui.view.jsf.listener;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 * Created by Tomáš on 2. 6. 2014.
 */
public class FacesAppListener implements SystemEventListener {
    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        if(event instanceof PreRenderViewEvent){
            System.out.println("PreRenderViewEvent");

        }

    }

    @Override
    public boolean isListenerForSource(Object source) {
        return source instanceof UIViewRoot;
    }
}
