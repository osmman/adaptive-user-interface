package cz.cvut.fel.aui.view.jsf;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.rules.AuiRuleEngine;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.rules.*;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private AuiRuleEngine ruleEngine;

    @Inject
    private Logger logger;

    @Inject
    public void ViewHelper(@Current final Context context) throws RemoteException, RuleSessionTypeUnsupportedException, InvalidRuleSessionException, RuleExecutionSetNotFoundException, RuleSessionCreateException, ConfigurationException {
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

        Map<String, Object> env = new HashMap<String, Object>();
        try {
            ruleEngine.process(env,context);
            locale = (Locale) env.get("locale");
            rightToLeft = (Boolean) env.get("RTL");
            layout = (String) (env.get("layout") != null ? env.get("layout") : DEFAULT_LAYOUT);
        } catch (Exception e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
    }
}
