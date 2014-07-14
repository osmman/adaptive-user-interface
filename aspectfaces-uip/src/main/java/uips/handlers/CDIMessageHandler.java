package uips.handlers;

import cz.cvut.fel.aui.service.ContextService;
import cz.cvut.fel.aui.service.PersonService;
import cz.cvut.fel.aui.service.remote.ContextServiceRemote;
import cz.cvut.fel.aui.utils.FacUtil;
import uips.events.interfaces.IUipAutonomousEventHandler;
import uips.instances.IAppClient;
import uips.instances.IInstance;
import uips.support.logging.ILog;
import uips.support.settings.tree.IHandlerSettings;
import uips.support.tools.ResultHolder;
import uips.tree.interfaces.IEvent;
import uips.uipserver.IUipServer;

import javax.enterprise.context.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.logging.Level;

/**
 * Created by Tomáš on 19. 5. 2014.
 */
public class CDIMessageHandler implements IUipAutonomousEventHandler {

    private ILog log;

    @Override
    public String getHandlerClass() {
        return "integration.java.cdi";
    }

    @Override
    public boolean handleEvent(IEvent eventInn, IAppClient appClient, IInstance instanceReference, ResultHolder resultHolder) {
        PersonService cs = (PersonService) FacUtil.getBeanByClass(PersonService.class);
        log.write(Level.INFO, Boolean.toString(FacUtil.isContextActivate(SessionScoped.class)));

        cs.findAll();

        try {
            final Context context = new InitialContext();
            ContextServiceRemote cts = (ContextServiceRemote) context.lookup("ejb:AdaptiveUI/aspectfaces-uip-1.0-SNAPSHOT/RemoteContextService!"+ContextServiceRemote.class.getName()+"?stateful");
            cz.cvut.fel.aui.model.Context cc = cts.getContext();

        } catch (NamingException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void initHandler(IUipServer uipServer, IHandlerSettings settings) {
        this.log = uipServer.getLog();
    }

    @Override
    public boolean startBeforeUipEventHandlers() {
        return true;
    }

    @Override
    public boolean startAfterUipEventHandlers() {
        return false;
    }

    @Override
    public Class<? extends IUipAutonomousEventHandler> getHandlerTypeInterfaceClass() {
        return IUipAutonomousEventHandler.class;
    }
}
