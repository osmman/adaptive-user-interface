package cz.cvut.fel.caf.impl.contexts;

import cz.cvut.fel.caf.ContextItem;

/**
 * Created by Tomáš on 17.4.14.
 */
public class ContextItemImpl implements ContextItem {

    private Object value;

    private String name;

    public ContextItemImpl(){
        name = "empty";
        value = new Object();
    }

    public ContextItemImpl(String name, Object value) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class<?> getValueClass() {
        return getValue().getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContextItemImpl that = (ContextItemImpl) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
