package cz.cvut.fel.aui.view.jsf;

import com.codingcrayons.aspectfaces.cache.ResourceCache;
import com.codingcrayons.aspectfaces.ondemand.DefaultAFGeneratorHandler;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.rules.AuiRuleEngine;
import cz.cvut.fel.aui.service.ContextService;
import cz.cvut.fel.aui.utils.FacUtil;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import javax.inject.Inject;
import javax.rules.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomáš on 28.12.13.
 */
public class AdaptiveGeneratorHandler extends DefaultAFGeneratorHandler {
    private static final String DEFAULT_CONFIG = "default";

    private Boolean _development = true;

    private Boolean _debug = false;

    private Integer _af_cache = -1;

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
    private String applySettings(String base, String calcLayout) {
        if (layout != null) {
            calcLayout = layout.getValue();
            calcLayout = (String) executeExpressionInElContext(FacesContext.getCurrentInstance()
                    .getApplication().getExpressionFactory(), FacesContext.getCurrentInstance()
                    .getELContext(), calcLayout);
        }
        return (calcLayout == null) ? null : ("layouts/" + base + '/' + calcLayout);
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
            getRuleEngine().process(context.getVariables(),config,context);
        } catch (Exception e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }

        context.setLayout(applySettings(context.getLayout(), null));
    }

    @Override
    protected String getConfig() {
        if (configName == null || configName.getValue().isEmpty()) {
            Map<String,Object> env = new HashMap<String, Object>(){{
                put("layout",DEFAULT_CONFIG);
            }};
            try {
                Context config = getContext();
                getRuleEngine().process(env,config);
            } catch (Exception e) {
                logger.log(Level.SEVERE,e.getMessage(),e);
            }
            return (String) env.get("layout");
        }
        return configName.getValue();
    }



    private Context getContext() {
        return ((ContextService) FacUtil.getBeanByClass(ContextService.class)).getContext();
    }

    private AuiRuleEngine getRuleEngine() {
        return (AuiRuleEngine) FacUtil.getBeanByClass(AuiRuleEngine.class);
    }
}
