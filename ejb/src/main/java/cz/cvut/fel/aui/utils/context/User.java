package cz.cvut.fel.aui.utils.context;

import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.caf.ContextItem;

/**
 * Created by Tomáš on 8. 5. 2014.
 */
public class User implements ContextItem {

    private Age age;

    private Integer invalid;

    public User(Age age) {
        this.age = age;
        this.invalid = 0;
    }

    public Age getAge() {
        return age;
    }

    public void invalidForm(){
        invalid++;
    }

    public void rightForm(){
        if(invalid >0) invalid--;
    }

    public Boolean isConfused() {
        return invalid > 2;
    }
}
