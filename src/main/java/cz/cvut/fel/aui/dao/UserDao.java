package cz.cvut.fel.aui.dao;

import cz.cvut.fel.aui.model.User;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 15.11.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface UserDao extends DataAccessObject<User> {
}
