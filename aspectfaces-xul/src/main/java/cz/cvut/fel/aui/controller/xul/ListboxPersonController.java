package cz.cvut.fel.aui.controller.xul;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.service.PersonService;
import org.xulfaces.component.listbox.DataListBoxComponent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Tomáš on 10.3.14.
 */
@Named
@SessionScoped
public class ListboxPersonController implements Serializable {

    private DataListBoxComponent listBoxComponent;

    @Inject
    private PersonService service;

    private List<Person> model;

    @PostConstruct
    private void init() {
        model = service.findAll();
    }

    public void observeChanges(@Observes(notifyObserver = Reception.IF_EXISTS) Person person){
        init();
    }

    public List<Person> getModel() {
        return model;
    }

    public DataListBoxComponent getListBoxComponent() {
        return listBoxComponent;
    }

    public void removeSelected() {
        List<Integer> selectedRows = getListBoxComponent().getSelectedRows();
        if (selectedRows != null) {
            for (Integer rowNumber : selectedRows) {
                service.remove(model.get(rowNumber));
            }
        }
    }

    public void setListBoxComponent(DataListBoxComponent listBoxComponent) {
        this.listBoxComponent = listBoxComponent;
    }
}
