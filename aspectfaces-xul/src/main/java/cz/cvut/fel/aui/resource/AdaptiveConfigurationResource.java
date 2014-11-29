package cz.cvut.fel.aui.resource;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.rules.AuiRuleEngine;

@Path("/adaptation")
@SessionScoped
public class AdaptiveConfigurationResource implements Serializable {

	@Inject
	private AuiRuleEngine engine;

	@Inject
	@Current
	private Context context;

	@Inject
	private Logger logger;

	private Map<String, Object> configuration = new HashMap<String, Object>();

	@PostConstruct
	public void init() {
		logger.info("created");
		onContextChanged(context);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Map getConfig() {
		return configuration;
	}

	public void onContextChanged(@Observes(notifyObserver = Reception.ALWAYS) Context context) {
		try {
			engine.process(configuration, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
