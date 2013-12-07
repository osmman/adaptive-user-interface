package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.service.PersonService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class PersonController extends BaseController
{

    @Produces
    @Named
    private Person newPerson;

    @Inject
    private PersonService personDao;

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    public void initUser()
    {
        newPerson = new Person();
    }

    public void createUser()
    {
        personDao.create(newPerson);
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Created", "Registration successful");
        facesContext.addMessage(null, m);
        initUser();
    }

    public void edit()
    {
        personDao.edit(newPerson);
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edited", "Registration successful");
        facesContext.addMessage(null, m);
        initUser();
    }

    public void delete(){
        personDao.remove(newPerson);
        initUser();
    }

    public List<Person> getUsers()
    {
        return personDao.findAll();
    }


}
