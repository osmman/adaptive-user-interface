package cz.cvut.fel.caf.impl;

import cz.cvut.fel.caf.*;
import cz.cvut.fel.caf.annotations.Logged;
import cz.cvut.fel.caf.interceptors.LoggedInterceptor;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomáš on 17.4.14.
 */
@Stateful
@SessionScoped
@Logged
public class ContextServiceImpl implements ContextService, Serializable {

    private Context context = new Context();

    private Set<ContextObserver> observers = new HashSet<ContextObserver>();

    @Inject
    private Event<ContextEvent> contextItemEvent;


    @Override
    public void addContextItem(Relationship relation, ContextItem contextItem) {
        context.setContextItem(relation, contextItem);
        ContextEvent event = new ContextEvent(
                this,
                ContextEvent.Type.RELATIONSHIP_ADDED,
                relation,
                contextItem);
        contextItemEvent.fire(event);
        fireObservers(event);
    }

    @Override
    public void removeContextItem(Relationship relation) {
        context.removeContextItem(relation);
        ContextEvent event = new ContextEvent(
                this,
                ContextEvent.Type.RELATIONSHIP_REMOVED,
                relation,
                null);
        contextItemEvent.fire(event);
        fireObservers(event);
    }

    @Override
    public void addObserver(ContextObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ContextObserver observer) {
        observers.remove(observer);
    }

    @Override
    public Context getContext() {
        return context;
    }

    private void fireObservers(ContextEvent event) {
        for (ContextObserver observer : observers) {
            observer.contextChanged(event);
        }
    }
}
