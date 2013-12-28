package cz.cvut.fel.aui.model.context;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public enum ScreenSize
{
    none("data.screen.none"),
    small("data.screen.size.small"),
    normal("data.screen.size.normal"),
    wide("data.screen.size.wide");

    private String label;

    private ScreenSize(String label){
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
