package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.model.PersonInfo;
import cz.cvut.fel.aui.service.PersonService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

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

    private Person person;

    @Inject
    private PersonService personService;

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    public void initUser()
    {
        newPerson = new Person();
        Map map = facesContext.getExternalContext().getRequestParameterMap();
        //id = Long.getLong(facesContext.getExternalContext().getRequestParameterMap().get("id"));
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

    public String edit(Person person)
    {
        personService.edit(person);
        return redirect("person",true);
    }

    public void delete(Person person)
    {
        personService.remove(person);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Person getPerson()
    {
         return person;
    }

    public List<Person> getAll()
    {
        return personService.findAll();
    }

}
