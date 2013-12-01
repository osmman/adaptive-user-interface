package cz.cvut.fel.aui.controller;

import cz.cvut.fel.aui.service.CarService;
import cz.cvut.fel.aui.model.Car;

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
 * User: Tomáš
 * Date: 26.11.13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class CarController extends BaseController
{

    @Produces
    @Named
    private Car newCar;

    @Inject
    private CarService carDao;

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    public void initUser() {
        newCar = new Car();
    }

    public void createUser() {
        carDao.create(newCar);
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Created", "Registration successful");
        facesContext.addMessage(null, m);
        initUser();
    }

    public List<Car> getCars(){
        return carDao.findAll();
    }

}
