package cz.cvut.fel.aui.dao.impl;

import cz.cvut.fel.aui.dao.AbstractDao;
import cz.cvut.fel.aui.dao.UserDao;
import cz.cvut.fel.aui.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 15.11.13
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Inject EntityManager em;

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
