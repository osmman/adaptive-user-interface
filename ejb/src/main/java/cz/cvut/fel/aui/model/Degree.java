package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiOrder;
import com.codingcrayons.aspectfaces.annotations.UiUserRoles;

import javax.persistence.Embeddable;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomáš on 1.4.14.
 */
@Embeddable
public class Degree implements Cloneable, Serializable {

    private Date expectedGraduation;

    private Date beganDegree;

    @Future
    @UiOrder(1)
    @UiUserRoles({"student"})
    public Date getExpectedGraduation() {
        return expectedGraduation;
    }

    public void setExpectedGraduation(Date expectedGraduation) {
        this.expectedGraduation = expectedGraduation;
    }

    @Past
    @UiOrder(2)
    @UiUserRoles({"student"})
    public Date getBeganDegree() {
        return beganDegree;
    }

    public void setBeganDegree(Date beganDegree) {
        this.beganDegree = beganDegree;
    }
}
