package uip.listeners;

import javax.ejb.ActivationConfigProperty;
        import javax.ejb.MessageDriven;
        import javax.ejb.TransactionAttribute;
        import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;

import org.jboss.ejb3.annotation.ResourceAdapter;
        import uips.integrated.jee.UipServerMessageDrivenBean;


@MessageDriven(
        name = "TcpXmlServerConnector1",
        messageListenerInterface = uip.uipaf.jeeuipssocketconnector.api.message.SocketMessageEndpoint.class,
        activationConfig = {
                @ActivationConfigProperty( propertyName = "port", propertyValue ="2636"),
                @ActivationConfigProperty( propertyName = "connectorName", propertyValue ="TcpXmlServerConnector1"),
                @ActivationConfigProperty( propertyName = "ipAddress", propertyValue ="0.0.0.0")
        }
)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@ResourceAdapter(value = "AdaptiveUI.ear#UipsJavaEeSocketConnector-rar-1.0.0.rar")
public class ServerCommListener extends UipServerMessageDrivenBean {

    private static final long serialVersionUID = -2817746392063865718L;


}
