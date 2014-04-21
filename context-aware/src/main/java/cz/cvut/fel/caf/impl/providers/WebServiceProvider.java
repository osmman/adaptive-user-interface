package cz.cvut.fel.caf.impl.providers;

import cz.cvut.fel.caf.ContextItem;
import cz.cvut.fel.caf.ContextProvider;
import cz.cvut.fel.caf.ContextService;
import cz.cvut.fel.caf.impl.contexts.ContextItemImpl;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;
import java.io.Serializable;
import java.util.Set;

@WebService(serviceName = "contextWS", portName = "context", name = "context",targetNamespace = "http://caf.fel.cvut.cz/context")
public class WebServiceProvider implements ContextProvider, Serializable {

    @Inject
    private ContextService context;

    @WebMethod
    public void changeContext(@WebParam(name = "contextItem") ContextItemImpl contextItem){
        context.addContextItem(contextItem);
    }
}
