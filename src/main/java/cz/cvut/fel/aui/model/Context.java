package cz.cvut.fel.aui.model;

import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 15.11.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Context implements Cloneable
{

    @NotNull
    private Age age;

    @NotNull
    @NotEmpty
    private String language;

    @NotNull
    private String country;

    @NotNull
    private ScreenSize screenSize;

    @NotNull
    private Device device;

    public Age getAge()
    {
        return age;
    }

    public void setAge(Age age)
    {
        this.age = age;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public ScreenSize getScreenSize()
    {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize)
    {
        this.screenSize = screenSize;
    }

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
}
