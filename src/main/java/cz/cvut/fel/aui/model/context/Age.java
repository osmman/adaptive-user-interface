package cz.cvut.fel.aui.model.context;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 30.11.13
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public enum Age
{
    CHILD("data.age.child"),
    STUDENT("data.age.student"),
    ADULT("data.age.adult"),
    ELDER("data.age.elder");

    private String label;

    private Age(String label){
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
