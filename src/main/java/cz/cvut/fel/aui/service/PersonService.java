package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.Person;

import javax.ejb.Stateless;
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
    EntityManager em;

    public PersonService()
    {
        super(Person.class);
    }

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
}
