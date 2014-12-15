package uips.handlers;

import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.model.PersonInfo;
import cz.cvut.fel.aui.service.ContextService;
import cz.cvut.fel.aui.service.PersonService;
import cz.cvut.fel.aui.service.remote.ContextServiceRemote;
import cz.cvut.fel.aui.utils.FacUtil;
import uips.builders.ModelBuilderImp;
import uips.events.HandlerMethods;
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
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Tomáš on 19. 5. 2014.
 */
public class RegistrationMessageHandler implements IUipAutonomousEventHandler {

    private ILog log;

    @Override
    public String getHandlerClass() {
        return "person.registration.create";
    }

    @Override
    public boolean handleEvent(IEvent eventInn, IAppClient appClient, IInstance instanceReference, ResultHolder resultHolder) {
        PersonService cs = (PersonService) FacUtil.getBeanByClass(PersonService.class);

        Map person = HandlerMethods.getModelValues("cz.cvut.fel.aui.model.Person.person.registration", null, null,
                instanceReference, appClient, true, this.log);

        Map personInfo = HandlerMethods.getModelValues((String) person.get("personInfo"), null, null,
                instanceReference, appClient, true, this.log);

        Map address = HandlerMethods.getModelValues((String) personInfo.get("address"), null, null,
                instanceReference, appClient, true, this.log);

        Map degree = HandlerMethods.getModelValues((String) personInfo.get("degree"), null, null,
                instanceReference, appClient, true, this.log);

        Person entit = new Person((String) person.get("email"),(String) person.get("password"));
        PersonInfo entitPersonInfo = new PersonInfo();
        entitPersonInfo.setFirstName((String) personInfo.get("firstName"));
        entitPersonInfo.setLastName((String) personInfo.get("lastName"));

        cs.create(entit);
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
