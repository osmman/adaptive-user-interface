package uip.service;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.service.ContextService;
import cz.cvut.fel.aui.service.remote.ContextServiceRemote;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Tomáš on 19. 5. 2014.
 */
@Remote(ContextServiceRemote.class)
@Stateful
@SessionScoped
public class RemoteContextService implements ContextServiceRemote, Serializable {

    @Inject
    private ContextService service;

    @Override
    public Context getContext() {
        return service.getContext();
    }

    @Override
    public void save(Context context) {

    }
}
