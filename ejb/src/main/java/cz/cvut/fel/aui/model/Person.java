package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiPassword;
import com.codingcrayons.aspectfaces.annotations.UiType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import uip.uipaf.auiGenerator.model.Storeable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person extends EntityObject implements Storeable
{
    public Person()
    {
    }

    public Person(String email, String password)
    {
        this.password = password;
        this.email = email;
    }

    private String password;

    private String email;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private PersonInfo personInfo;

    @NotNull
    @NotEmpty
    @UiPassword
    @UiOrder(2)
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @NotNull
    @NotEmpty
    @Email
    @UiType(value = "main")
    @UiOrder(1)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public PersonInfo getPersonInfo()
    {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo)
    {
        this.personInfo = personInfo;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", personInfo=" + personInfo +
                '}';
    }
}
