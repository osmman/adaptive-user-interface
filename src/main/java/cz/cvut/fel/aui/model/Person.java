package cz.cvut.fel.aui.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    private PersonInfo personInfo;

    @OneToMany(mappedBy = "owner")
    private Set<Car> cars;

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
