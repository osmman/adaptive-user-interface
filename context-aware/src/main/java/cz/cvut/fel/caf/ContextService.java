package cz.cvut.fel.caf;

/**
 * Created by Tomáš on 17.4.14.
 */
public interface ContextService {

    void addContextItem(Relationship relation, ContextItem contextItem);

    void removeContextItem(Relationship relation);

    void addObserver(ContextObserver observer);

    void removeObserver(ContextObserver observer);

    Context getContext();

}
