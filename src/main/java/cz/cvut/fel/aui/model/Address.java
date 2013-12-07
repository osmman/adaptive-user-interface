package cz.cvut.fel.aui.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Tomáš on 7.12.13.
 */
@Embeddable
public class Address implements Cloneable,Serializable
{

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private String postCoe;

    private String state ="";

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPostCoe()
    {
        return postCoe;
    }

    public void setPostCoe(String postCoe)
    {
        this.postCoe = postCoe;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
