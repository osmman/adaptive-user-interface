package cz.cvut.fel.caf.impl;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import cz.cvut.fel.caf.ContextItem;
import cz.cvut.fel.caf.ContextObserver;
import cz.cvut.fel.caf.ContextService;
import cz.cvut.fel.caf.ContextState;
import cz.cvut.fel.caf.annotations.Added;
import cz.cvut.fel.caf.annotations.Removed;
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
@Interceptors({LoggedInterceptor.class})
public class ContextServiceImpl implements ContextService, Serializable {

    private Set<ContextItem> context = new HashSet<ContextItem>();

    private Set<ContextObserver> observers = new HashSet<ContextObserver>();

    @Inject
    @Added
    public Event<ContextItem> contextItemEventAdded;

    @Inject
    @Removed
    public Event<ContextItem> contextItemEventRemoved;

    @Override
    public void addContextItem(ContextItem contextItem) {
        context.add(contextItem);
        contextItemEventAdded.fire(contextItem);
        fireObservers(contextItem, ContextState.ADDED);
    }

    @Override
    public void removeContextItem(ContextItem contextItem) {
        context.remove(contextItem);
        contextItemEventRemoved.fire(contextItem);
        fireObservers(contextItem, ContextState.REMOVED);
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
    public Set<ContextItem> getContext() {
        return ImmutableSet.<ContextItem>builder().addAll(context.iterator()).build();
    }

    private void fireObservers(ContextItem contextItem, ContextState state) {
        for (ContextObserver observer : observers) {
            observer.onChange(contextItem, state, this);
        }
    }
}
