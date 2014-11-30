package cz.cvut.fel.aui.rules;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.rules.*;
import javax.rules.admin.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Tomáš on 2. 6. 2014.
 */
@Singleton
public class AuiRuleEngine implements Serializable{

    private RuleServiceProvider ruleServiceProvider;

    private RuleAdministrator ruleAdministrator;

    @PostConstruct
    private void init() throws ClassNotFoundException, ConfigurationException, IOException, RuleExecutionSetCreateException, URISyntaxException, RuleExecutionSetRegisterException {
        Class.forName("org.drools.jsr94.rules.RuleServiceProviderImpl");
        ruleServiceProvider = RuleServiceProviderManager.getRuleServiceProvider("http://drools.org/");
        ruleAdministrator = ruleServiceProvider.getRuleAdministrator();

        registerRuleExecutionSet("rules.drl");
    }

    private void registerRuleExecutionSet(String name) throws IOException, RuleExecutionSetCreateException, RuleExecutionSetRegisterException {

        InputStream is = this.getClass().getResourceAsStream(name);

        RuleExecutionSet ruleExecutionSet = ruleAdministrator.getLocalRuleExecutionSetProvider(null)
                .createRuleExecutionSet(is, null);

        is.close();

        ruleAdministrator.registerRuleExecutionSet(ruleExecutionSet.getName(), ruleExecutionSet, null);
    }

    public Map<String, Object> process(Map<String,Object> environment, Object... inputs) throws ConfigurationException, RemoteException, RuleSessionCreateException, RuleExecutionSetNotFoundException, RuleSessionTypeUnsupportedException, InvalidRuleSessionException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("env",environment);
        RuleRuntime ruleRuntime = ruleServiceProvider.getRuleRuntime();

        StatelessRuleSession srs = (StatelessRuleSession) ruleRuntime.createRuleSession("cz.cvut.fel.aui.rules", map, RuleRuntime.STATELESS_SESSION_TYPE );
        srs.executeRules(Arrays.asList(inputs));
        return (Map<String,Object>) map.get("env");
    }
}
