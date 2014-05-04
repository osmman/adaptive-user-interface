package cz.cvut.fel.aui.model;

import uip.uipaf.auiGenerator.model.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
abstract class EntityObject implements Serializable, Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    public EntityObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)[%s]", getClass().getSimpleName(), getId(), super.toString());
    }
}
