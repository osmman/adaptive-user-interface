package cz.cvut.fel.aui.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Tomáš on 7.12.13.
 */
@Entity
public class PersonInfo extends EntityObject
{

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[^\\s]].*", message = "Cannot start with space")
    private String firstName;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[^\\s]].*", message = "Cannot start with space")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Address address = new Address();

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public enum Gender {
        FEMALE("gender.female"),

        MALE("gender.male");

        private final String label;

        private Gender(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
