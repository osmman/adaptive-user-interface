package cz.cvut.fel.aui.view.xul;

import com.codingcrayons.aspectfaces.cache.ResourceCache;
import com.codingcrayons.aspectfaces.ondemand.DefaultAFGeneratorHandler;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.rules.AuiRuleEngine;
import cz.cvut.fel.aui.utils.FacUtil;

/**
 * Created by Tomáš on 28.12.13.
 */
public class AdaptiveGeneratorHandler extends DefaultAFGeneratorHandler {
	private static final String DEFAULT_LAYOUT = "form.xhtml";

	private static final String DEFAULT_CONFIG = "default";

	private Boolean _development = true;

	private Boolean _debug = false;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public AdaptiveGeneratorHandler(ComponentConfig config) {
		super(config);

		FacesContext ctx = FacesContext.getCurrentInstance();

		Integer _af_cache = Integer.parseInt(ctx.getExternalContext().getInitParameter("AF.CACHE"));
		if (_af_cache == null) {
			_af_cache = -1;
		}
		this.afCacheTime = _af_cache;

		_development = Boolean.parseBoolean(ctx.getExternalContext().getInitParameter("AF.DEVELOPMENT"));
		if (_development == null) {
			_development = false;
		}
		_debug = Boolean.parseBoolean(ctx.getExternalContext().getInitParameter("AF.DEBUG"));
		if (_debug == null) {
			_debug = false;
		}
	}

	/*
	 * Applies Adaptive settings
	 */
	private String applySettings(String calcLayout) {
		if (layout != null) {
			calcLayout = layout.getValue();
			calcLayout = (String) executeExpressionInElContext(FacesContext.getCurrentInstance()
					.getApplication().getExpressionFactory(), FacesContext.getCurrentInstance()
					.getELContext(), calcLayout);
		}
		return (calcLayout == null) ? null : (calcLayout);
	}

	@Override
	protected InputStream createInputStream(String s) {
		try {

			if (_development) {
				ResourceCache.getInstance().clear();
			}
			if (_debug) {
				Logger logger = Logger.getLogger("AF.DEBUG");
				logger.info(s);
			}

			return new ByteArrayInputStream(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return viewFragmentExceptionIS(e);
		}
	}

	@Override
	protected void hookAddToAFContext(com.codingcrayons.aspectfaces.configuration.Context context) {
		Context config = getContext();
		context.setLayout("desktop");

		try {
			getRuleEngine().process(context.getVariables(), config, context);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}

		context.setLayout(applySettings(null));
	}

	@Override
	protected String getConfig() {
		if (configName == null || configName.getValue().isEmpty()) {
			return "default";
		}
		return configName.getValue();
	}

	private Context getContext() {
		return (Context) FacUtil.getBeanByName("context");
	}

	private AuiRuleEngine getRuleEngine() {
		return (AuiRuleEngine) FacUtil.getBeanByClass(AuiRuleEngine.class);
	}
}
