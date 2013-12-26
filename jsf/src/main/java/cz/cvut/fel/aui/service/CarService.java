package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.Car;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 26.11.13
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class CarService extends AbstractDao<Car>
{
    @Inject
    EntityManager em;

    public CarService()
    {
        super(Car.class);
    }

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
}

