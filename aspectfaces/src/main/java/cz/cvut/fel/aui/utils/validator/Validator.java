package cz.cvut.fel.aui.utils.validator;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class Validator
{
    @Inject
    private javax.validation.Validator validator;

    /**
     * @param object
     * @throws javax.validation.ConstraintViolationException
     */
    public <T> void validate(T object)
    {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
