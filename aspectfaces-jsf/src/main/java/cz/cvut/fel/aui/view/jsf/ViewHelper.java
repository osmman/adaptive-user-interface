package cz.cvut.fel.aui.view.jsf;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;

import javax.enterprise.event.Observes;
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

    @Inject
    public void ViewHelper(@Current final Context context) {
        onContextChanged(context);
    }

    public String getLayout() {
        return layout;
    }

    public Locale getLocale() {
        return locale;
    }

    public Boolean isRightToLeft() {
        return rightToLeft;
    }

    public void setRightToLeft(Boolean rightToLeft) {
        this.rightToLeft = rightToLeft;
    }

    public void onContextChanged(@Observes final Context context) {
        switch (context.getDevice()) {
            case TABLET:
            case PHONE:
                layout = "mobile";
                break;
            default:
                layout = DEFAULT_LAYOUT;
        }

        locale = new Locale(context.getLanguage(), context.getCountry());
        rightToLeft = "ar".equals(context.getLanguage()) ? true : false;
    }
}
