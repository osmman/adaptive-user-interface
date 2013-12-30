package cz.cvut.fel.aui.utils;

import cz.cvut.fel.aui.model.Navigation;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomáš on 28.12.13.
 */

@Singleton
@Startup
@Named("navigation")
public class NavigationList
{

    private List<Navigation> navigation;

    @PostConstruct
    private void init()
    {
        navigation = new LinkedList<Navigation>();
        navigation.add(new Navigation("context", "navigation.context"));
        navigation.add(new Navigation("people", "navigation.people"));
    }

    public List<Navigation> getList()
    {
        return navigation;
    }

}
