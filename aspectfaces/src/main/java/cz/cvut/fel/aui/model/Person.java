package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiPassword;
import com.codingcrayons.aspectfaces.annotations.UiType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person extends EntityObject
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

    @OneToOne
    private PersonInfo personInfo;

    @OneToMany(mappedBy = "owner")
    private Set<Car> cars;

    @NotNull
    @NotEmpty
    @UiPassword
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

    public Set<Car> getCars()
    {
        return cars;
    }

    public void setCars(Set<Car> cars)
    {


        this.cars = cars;
    }
}
