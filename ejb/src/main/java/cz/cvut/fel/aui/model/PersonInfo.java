package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiProfiles;
import com.codingcrayons.aspectfaces.annotations.UiUserRoles;
import cz.cvut.fel.aui.model.context.Age;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tomáš on 7.12.13.
 */
@Entity
public class PersonInfo extends EntityObject {

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Title title;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Degree degree = new Degree();

    private Address address = new Address();

    @OneToOne(mappedBy = "personInfo")
    private Person person;

    @NotNull
    @NotEmpty
    @UiOrder(2)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    @UiOrder(3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @UiOrder(4)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @UiOrder(6)
    @UiUserRoles({"student","adult","elder"})
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @UiOrder(5)
    @UiUserRoles({"student"})
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @UiOrder(1)
    @UiUserRoles({"student","adult","elder"})
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFullName() {
        return firstName + " " + lastName;
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

    public enum Title {
        None,
        Master,
        Doctor,
        Engineer,
        Bachelor,
        Professor,
        Ninja;

        public String getLabel() {
            return this.name();
        }
    }

}
