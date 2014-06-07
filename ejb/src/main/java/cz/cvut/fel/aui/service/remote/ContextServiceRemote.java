package cz.cvut.fel.aui.service.remote;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;

import javax.enterprise.inject.Produces;

/**
 * Created by Tomáš on 19. 5. 2014.
 */
public interface ContextServiceRemote {

    public Context getContext();

    public void save(Context context);
}
