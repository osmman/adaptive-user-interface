package cz.cvut.fel.caf.impl.relations;

import cz.cvut.fel.caf.Relationship;

/**
 * Created by Tomáš on 5. 5. 2014.
 */
public class RelationshipImpl implements Relationship {

    private String name;

    public RelationshipImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationshipImpl that = (RelationshipImpl) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
