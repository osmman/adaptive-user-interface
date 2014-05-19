package cz.cvut.fel.aui.view.jsf;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.utils.ContextResources;
import cz.cvut.fel.aui.utils.context.DeviceInfo;
import cz.cvut.fel.caf.ContextEvent;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Locale;

/**
 * Created by Tomáš on 5.4.14.
 */
@Named
public class ViewHelper {

    private final String DEFAULT_LAYOUT = "default";

    private String layout = DEFAULT_LAYOUT;

    private Locale locale = Locale.US;

    private Boolean rightToLeft = false;

//    @Inject
//    public void ViewHelper(@Current final Context context) {
//        onContextChanged(context);
//    }

    public String getLayout() {
        return layout;
    }

    public Locale getLocale() {
        return locale;
    }

    public Boolean isRightToLeft() {
        return rightToLeft;
    }

//    public void setRightToLeft(Boolean rightToLeft) {
//        this.rightToLeft = rightToLeft;
//    }

    public void onContextChanged(@Observes(notifyObserver = Reception.ALWAYS) ContextEvent event) {

        if(event.getRelationship() == ContextResources.DEVICE){
            if(event.getContextItem() instanceof  DeviceInfo){
                DeviceInfo info = (DeviceInfo) event.getContextItem();
                switch (info.getDevice()) {
                    case TABLET:
                    case PHONE:
                        layout = "mobile";
                        break;
                    default:
                        layout = DEFAULT_LAYOUT;
                }
            }
        }

        if(event.getRelationship() == ContextResources.LOCALE){
            if(event.getContextItem() instanceof  DeviceInfo){
                cz.cvut.fel.aui.utils.context.Locale loc = (cz.cvut.fel.aui.utils.context.Locale) event.getContextItem();
                locale = new Locale(loc.getLanguage(), loc.getCountry());
                rightToLeft = "ar".equals(loc.getLanguage()) ? true : false;
            }
        }
    }
}
