package cz.cvut.fel.caf;

import java.util.Set;

/**
 * Created by Tomáš on 17.4.14.
 */
public interface ContextService {

    void addContextItem(ContextItem contextItem);
    void removeContextItem(ContextItem contextItem);

    void addObserver(ContextObserver observer);
    void removeObserver(ContextObserver observer);

    Set<ContextItem> getContext();

}
