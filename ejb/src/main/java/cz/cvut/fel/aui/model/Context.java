package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiType;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import dk.pervasive.jcaf.ContextEvent;
import dk.pervasive.jcaf.entity.GenericEntity;
import dk.pervasive.jcaf.entity.Place;
import dk.pervasive.jcaf.item.Location;
import dk.pervasive.jcaf.relationship.Located;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Context extends GenericEntity implements Cloneable
{
    private Age age;

    private String language;

    private String country;

    private ScreenSize screenSize;

    private Device device;

    @UiOrder(1)
    @NotNull
    public Age getAge()
    {
        return age;
    }

    public void setAge(Age age)
    {
        this.age = age;
    }

    @UiOrder(5)
    @NotNull
    @Size(min = 2)
    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    @UiOrder(4)
    @NotNull
    @UiType(value = "state")
    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @UiOrder(3)
    @NotNull
    public ScreenSize getScreenSize()
    {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize)
    {
        this.screenSize = screenSize;
    }

    @UiOrder(2)
    @NotNull
    public Device getDevice()
    {
        return device;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }

    public Context clone()
    {
        try {
            return (Context) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public void contextChanged(ContextEvent event) {
        super.contextChanged(event);

        String new_location = null;

        if(event.getRelationship() instanceof Located){
            if (event.getItem() instanceof Location) {
                new_location = ((Location) event.getItem()).getLocation();
            }
            if(event.getItem() instanceof Place){
                new_location = event.getItem().getId();
            }
        }
        if (new_location != null) {
            if (event.getEventType() == ContextEvent.RELATIONSHIP_ADDED)
                this.setCountry(new_location);
            else
                this.setCountry("Unknown");
        }
    }

    @Override
    public String toString() {
        return "Context{" +
                "age=" + age +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", screenSize=" + screenSize +
                ", device=" + device +
                '}';
    }
}
