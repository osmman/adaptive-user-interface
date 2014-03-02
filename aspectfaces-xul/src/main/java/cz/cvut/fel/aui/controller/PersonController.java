package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.model.PersonInfo;
import cz.cvut.fel.aui.service.ContextService;
import cz.cvut.fel.aui.service.PersonService;
import cz.cvut.fel.aui.utils.validator.Validator;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
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
@Model
public class PersonController extends BaseController
{

    @Produces
    @Named
    private Person newPerson;

    private Long id;

    @Produces
    @Named
    private Person person;

    @Inject
    private PersonService personService;

    @Inject
    private FacesContext facesContext;

    @Inject
    private ContextService contextService;

    @Inject
    private Validator validator;

    @PostConstruct
    public void initUser()
    {
        Context context = contextService.getContext();

        newPerson = new Person();
        newPerson.setPersonInfo(new PersonInfo());
        newPerson.getPersonInfo().getAddress().setCountry(context.getCountry());
    }

    public void loadPerson(){
        if (id != null) {
            person = personService.find(id);
            if (person.getPersonInfo() == null) {
                person.setPersonInfo(new PersonInfo());
            }
        }
    }

    public String create()
    {
        personService.create(newPerson);
        initUser();
        return redirect("people");
    }

    public void validate(){
        validator.validate(newPerson);
    }

    public String edit(Person person)
    {
        personService.edit(person);
        return redirect("person",true);
    }

    public String delete(Person person)
    {
        personService.remove(person);
        return redirect("people");
    }

    public String delete(){
        loadPerson();
        personService.remove(person);
        return redirect("people");
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<Person> getAll()
    {
        List<Person> list = personService.findAll();

        for(Person p : list){
            PersonInfo info = new PersonInfo();
            info.setFirstName("Tomas");
            info.setLastName("Turek");
            p.setPersonInfo(info);
        }

        return list;
    }

}
