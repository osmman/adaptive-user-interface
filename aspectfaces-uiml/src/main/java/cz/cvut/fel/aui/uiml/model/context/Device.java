package cz.cvut.fel.aui.uiml.model.context;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public enum Device
{
    PHONE("data.device.phone"),
    TABLET("data.device.tablet"),
    DESKTOP("data.device.desktop");

    private String label;

    private Device(String label){
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
