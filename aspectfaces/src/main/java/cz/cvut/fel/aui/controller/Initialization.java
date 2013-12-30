package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.service.PersonService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by Tomáš on 27.12.13.
 */
@Startup
@Singleton
public class Initialization
{

    @Inject
    private PersonService personService;

    @PostConstruct
    public void deploy(){
        personService.create(new Person("osmman@gmail.com","heslo"));
        personService.create(new Person("asdas@gmail.com","heslo"));
    }
}
