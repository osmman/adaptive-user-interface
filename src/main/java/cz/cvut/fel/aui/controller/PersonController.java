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
    private PersonService personService;

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    public void initUser()
    {
        newPerson = new Person();
    }

    public String create()
    {
        personService.create(newPerson);
        initUser();
        return redirect("people");
    }

    public void edit()
    {
        personService.edit(newPerson);
    }

    public void delete(Person person){
        personService.remove(person);
    }

    public List<Person> getAll()
    {
        return personService.findAll();
    }


}
