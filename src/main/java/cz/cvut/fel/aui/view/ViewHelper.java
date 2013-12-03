package cz.cvut.fel.aui.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 2.12.13
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class ViewHelper
{

    private String menuClass = "";

    private String inputClass = "";

    private String labelClass = "";

    private boolean help = false;

    private boolean images = false;

    public String getMenuClass()
    {
        return menuClass;
    }

    public void setMenuClass(String menuClass)
    {
        this.menuClass = menuClass;
    }

    public String getInputClass()
    {
        return inputClass;
    }

    public void setInputClass(String inputClass)
    {
        this.inputClass = inputClass;
    }

    public String getLabelClass()
    {
        return labelClass;
    }

    public void setLabelClass(String labelClass)
    {
        this.labelClass = labelClass;
    }

    public boolean getHelp()
    {
        return help;
    }

    public void setHelp(boolean help)
    {
        this.help = help;
    }

    public boolean getImages(){
        return images;
    }

    public void setImages(boolean images){
        this.images = images;
    }
}
