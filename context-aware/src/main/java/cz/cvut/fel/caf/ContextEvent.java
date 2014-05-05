package cz.cvut.fel.caf;

import java.util.EventObject;

public class ContextEvent extends EventObject {

    private Type eventType;

    private ContextItem contextItem;

    private Relationship relationship;

    public ContextEvent(Object source, Type eventType, Relationship relationship, ContextItem contextItem) {
        super(source);
        this.eventType = eventType;
        this.relationship = relationship;
        this.contextItem = contextItem;
    }

    public enum Type {
        RELATIONSHIP_ADDED,
        RELATIONSHIP_REMOVED
    }

    Type getEventType() {
        return eventType;
    }

    ContextItem getContextItem() {
        return contextItem;
    }

    Relationship getRelationship() {
        return relationship;
    }
}