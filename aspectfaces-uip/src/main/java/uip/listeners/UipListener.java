package uip.listeners;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.jboss.ejb3.annotation.ResourceAdapter;
import uips.integrated.jee.UipServerMessageDrivenBean;

@MessageDriven(
        name = "TcpXmlUipConnector1",
        messageListenerInterface = uip.uipaf.jeeuipssocketconnector.api.message.SocketMessageEndpoint.class,
        activationConfig = {
                @ActivationConfigProperty( propertyName = "port", propertyValue ="5678"),
                @ActivationConfigProperty( propertyName = "connectorName", propertyValue ="TcpXmlUipConnector1"),
                @ActivationConfigProperty( propertyName = "ipAddress", propertyValue ="0.0.0.0")
        }
)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@ResourceAdapter(value = "AdaptiveUI.ear#UipsJavaEeSocketConnector-rar-1.0.0.rar")
public class UipListener extends UipServerMessageDrivenBean {

    private static final long serialVersionUID = -5130878618896158250L;

}
