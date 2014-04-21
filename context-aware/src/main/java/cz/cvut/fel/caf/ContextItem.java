package cz.cvut.fel.caf;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Tomáš on 17.4.14.
 */
@XmlRootElement
public interface ContextItem extends Serializable {
    String getName();
    void setName(String name);
    Object getValue();
    void setValue(Object value);
    Class<?> getValueClass();
}
