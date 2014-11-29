package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiIgnore;
import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiType;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Context implements Cloneable, Serializable {
    private Age age;

    private String language;

    private String country;

    private ScreenSize screenSize;

    private Device device;

    private Integer invalid = 0;

    @UiOrder(1)
    @NotNull
    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    @UiOrder(5)
    @NotNull
    @Size(min = 2)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @UiOrder(4)
    @NotNull
    @UiType(value = "state")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @UiOrder(3)
    @NotNull
    public ScreenSize getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }

    @UiOrder(2)
    @NotNull
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @UiIgnore
    @Min(0)
    public Integer getInvalid() {
        return invalid;
    }

    public void setInvalid(Integer invalid) {
        this.invalid = invalid;
    }

    public Context clone() {
        try {
            return (Context) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
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
                ", invalid=" + invalid +
                '}';
    }
}
