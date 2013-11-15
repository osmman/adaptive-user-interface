package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.dao.UserDao;
import cz.cvut.fel.aui.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
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

    @Produces
    @Named
    private User newUser;

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void initUser() {
        newUser = new User();
    }

    public String createUser() {
        userDao.create(newUser);
        return "";
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }
}
