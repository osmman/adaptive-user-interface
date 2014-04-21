package cz.cvut.fel.caf.test;

import cz.cvut.fel.caf.ContextItem;
import cz.cvut.fel.caf.impl.contexts.ContextItemImpl;
import cz.cvut.fel.caf.impl.providers.WebServiceProvider;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tomáš on 18.4.14.
 */
public class Client {
    private WebServiceProvider ws;

    public Client(final URL wsdlUrl){
        QName serviceName = new QName("http://caf.fel.cvut.cz/context", "context");

        Service service = Service.create(wsdlUrl, serviceName);
        ws = service.getPort(WebServiceProvider.class);
        BindingProvider bp = (BindingProvider) ws;
        bp.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
        assert (ws != null);
    }

    public void sendContext(ContextItemImpl item){
        ws.changeContext(item);
    }

    public static void main(String[] args) throws MalformedURLException {
        Client client = new Client(new URL("localhost:8080/contextWS?WSDL"));
        client.sendContext(new ContextItemImpl());
    }
}
