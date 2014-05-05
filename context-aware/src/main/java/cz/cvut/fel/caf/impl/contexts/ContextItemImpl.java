package cz.cvut.fel.caf.impl.contexts;

import cz.cvut.fel.caf.ContextItem;

/**
 * Created by Tomáš on 17.4.14.
 */
public class ContextItemImpl implements ContextItem {

    private Object value;

    public Object getValue() {
        return value;
    }

    public ContextItemImpl(Object value) {
        this.value = value;
    }
}
