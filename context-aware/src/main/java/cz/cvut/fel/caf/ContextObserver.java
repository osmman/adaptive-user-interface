package cz.cvut.fel.caf;

/**
 * Created by Tomáš on 17.4.14.
 */
public interface ContextObserver {

    void onChange(ContextItem contextItem, ContextState state, ContextService service);
}
