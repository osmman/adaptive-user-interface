package cz.cvut.fel.caf;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class Context implements Serializable {
    private Hashtable<Relationship,ContextItem> context = new Hashtable<Relationship, ContextItem>();

    public void setContextItem(Relationship relationship, ContextItem item){
        context.put(relationship,item);
    }

    public void removeContextItem(Relationship relation){
        context.remove(relation);
    }

    public ContextItem getContextItem(Relationship relation){
        return context.get(relation);
    }

    public boolean contains(ContextItem item){
        return context.contains(item);
    }

    public boolean containsRelationship(Relationship relation) {
        return context.containsKey(relation);
    }

    public Enumeration getRelationships() {
        return context.keys();
    }

    public Enumeration getItems() {
        return context.elements();
    }
}
