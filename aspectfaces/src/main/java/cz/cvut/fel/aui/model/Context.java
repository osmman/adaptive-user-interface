package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiAfter;
import com.codingcrayons.aspectfaces.annotations.UiBefore;
import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiType;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Context implements Cloneable
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
    @UiAfter(type = "image", value="1")
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
}
