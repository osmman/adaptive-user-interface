package cz.cvut.fel.aui.view.jsf;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.rules.AuiRuleEngine;

import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
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

    private Map<String, Object> setting = new HashMap<String, Object>();

    @Inject
    private AuiRuleEngine ruleEngine;

    @Inject
    private Logger logger;

    @Inject
    public void ViewHelper(@Current final Context context) throws RemoteException, RuleSessionTypeUnsupportedException, InvalidRuleSessionException, RuleExecutionSetNotFoundException, RuleSessionCreateException, ConfigurationException {
        setting.put("layout",DEFAULT_LAYOUT);
        setting.put("locale",Locale.US);
        setting.put("RTL",false);
        onContextChanged(context);
    }

    public String getLayout() {
        return (String) setting.get("layout");
    }

    public Locale getLocale() {
        return (Locale) setting.get("locale");
    }

    public Boolean isRightToLeft() {
        return (Boolean) setting.get("RTL");
    }

    public void setRightToLeft(Boolean rightToLeft) {
        setting.put("RTL",rightToLeft);
    }

    public void onContextChanged(@Observes final Context context) {
        try {
            ruleEngine.process(setting,context);
        } catch (Exception e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
    }
}
