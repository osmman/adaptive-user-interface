package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiProfiles;
import com.codingcrayons.aspectfaces.annotations.UiType;
import com.codingcrayons.aspectfaces.annotations.UiUserRoles;
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

    @UiOrder(1)
    @UiUserRoles({"student","adult","elder"})
    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    @UiOrder(2)
    @UiUserRoles({"student","adult","elder"})
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @UiOrder(3)
    @UiType(value = "state")
    @UiUserRoles({"student","adult","elder"})
    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @UiOrder(5)
    @UiUserRoles({"student","adult","elder"})
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
    @UiUserRoles({"student","adult","elder"})
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
