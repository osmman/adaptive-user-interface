package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.dao.UserDao;
import cz.cvut.fel.aui.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 15.11.13
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
@Named
@ViewScoped
public class UserController extends BaseController {

    private User user;

    @Inject
    private UserDao userDao;

    @PostConstruct
    private void init() {
        user = new User();
    }

    public String createUser() {
        userDao.create(user);
        init();
        return null;
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
