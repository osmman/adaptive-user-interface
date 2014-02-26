package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiProfiles;
import com.codingcrayons.aspectfaces.annotations.UiType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Tomáš on 7.12.13.
 */
@Embeddable
public class Address implements Cloneable, Serializable
{

    private String street;

    private String city;

    private String country;

    private String postCode;

    private String state = "";

    @NotNull
    @NotEmpty
    @UiOrder(1)
    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    @NotNull
    @NotEmpty
    @UiOrder(2)
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @NotNull
    @NotEmpty
    @UiOrder(3)
    @UiType(value = "state")
    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @NotNull
    @NotEmpty
    @UiOrder(5)
    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @UiProfiles({"COUNTRY_US"})
    @UiOrder(4)
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
