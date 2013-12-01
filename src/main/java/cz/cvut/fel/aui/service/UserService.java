package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 15.11.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class UserService extends AbstractDao<User>
{

    @Inject
    EntityManager em;

    public UserService()
    {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
}
