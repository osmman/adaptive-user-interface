package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Tomáš on 7.12.13.
 */
@Entity
public class PersonInfo extends EntityObject
{

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "personInfo")
    private Person person;

    private Address address = new Address();

    @NotNull
    @NotEmpty
    @UiOrder(1)
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    @UiOrder(2)
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @UiOrder(3)
    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    @UiOrder(4)
    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public enum Gender
    {
        FEMALE("gender.female"),

        MALE("gender.male");

        private final String label;

        private Gender(String label)
        {
            this.label = label;
        }

        public String getLabel()
        {
            return label;
        }
    }

}
