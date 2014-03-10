package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.Person;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class PersonService extends AbstractDao<Person>
{

    @Inject
    private EntityManager em;

    public PersonService()
    {
        super(Person.class);
    }

    @Inject
    private Event<Person> personEvent;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    @Override
    public void create(Person entity)
    {
        super.create(entity);
        personEvent.fire(entity);
    }

    @Override
    public void edit(Person entity) {
        super.edit(entity);
        personEvent.fire(entity);
    }

    @Override
    public void remove(Person entity) {
        super.remove(entity);
        personEvent.fire(entity);
    }
}
