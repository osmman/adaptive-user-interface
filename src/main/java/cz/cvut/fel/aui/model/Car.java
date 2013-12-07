package cz.cvut.fel.aui.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 19.11.13
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Car extends EntityObject
{
    @NotNull
    @Size(min = 3, max = 60)
    private String name;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(unique=true)
    private String spz;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person owner;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSpz()
    {
        return spz;
    }

    public void setSpz(String spz)
    {
        this.spz = spz;
    }

    public Person getOwner()
    {
        return owner;
    }

    public void setOwner(Person owner)
    {
        this.owner = owner;
    }
}
