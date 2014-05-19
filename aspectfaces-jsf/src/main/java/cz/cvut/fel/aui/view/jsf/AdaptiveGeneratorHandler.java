package cz.cvut.fel.aui.view.jsf;

import com.codingcrayons.aspectfaces.cache.ResourceCache;
import com.codingcrayons.aspectfaces.ondemand.DefaultAFGeneratorHandler;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.utils.ContextResources;
import cz.cvut.fel.aui.utils.FacUtil;
import cz.cvut.fel.aui.utils.context.DeviceInfo;
import cz.cvut.fel.aui.utils.context.Locale;
import cz.cvut.fel.aui.utils.context.User;
import cz.cvut.fel.caf.ContextService;
import cz.cvut.fel.caf.impl.contexts.ContextItemImpl;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * Created by Tomáš on 28.12.13.
 */
public class AdaptiveGeneratorHandler extends DefaultAFGeneratorHandler {
    private static final String DEFAULT_CONFIG = "default";

    private Boolean _development = true;

    private Boolean _debug = false;

    private Integer _af_cache = -1;

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
        cz.cvut.fel.caf.Context config = getContext();
        DeviceInfo device = (DeviceInfo) config.getContextItem(ContextResources.DEVICE);
        if (device == null && (device.getDevice() == Device.PHONE || device.getDevice() == Device.TABLET)) {
            context.setLayout(applySettings("mobile", null));
            context.getVariables().put("table", "list");
        } else {
            context.setLayout(applySettings("desktop", null));
        }

        Locale locale = (Locale) config.getContextItem(ContextResources.LOCALE);
        context.setProfiles(new String[]{
                "COUNTRY_" + locale.getCountry()
        });

        User user = (User) config.getContextItem(ContextResources.USER);
        context.setRoles(new String[]{
                user.getAge().name().toLowerCase()
        });

        context.getVariables().put("country", locale.getLanguage());
        context.getVariables().put("applyImage", user.getAge() == Age.CHILD);
        context.getVariables().put("applyHelp", user.getAge() == Age.ELDER || user.isConfused());
    }

    @Override
    protected String getConfig() {
        if (configName == null || configName.getValue().isEmpty()) {
            cz.cvut.fel.caf.Context context = getContext();
            DeviceInfo item = (DeviceInfo) context.getContextItem(ContextResources.DEVICE);
            if (item == null)
                return DEFAULT_CONFIG;
            if (item.getDevice() == Device.PHONE || item.getDevice() == Device.TABLET) {
                return "mobile";
            } else {
                return DEFAULT_CONFIG;
            }
        }
        return configName.getValue();
    }

    private cz.cvut.fel.caf.Context getContext() {
        return ((ContextService) FacUtil.getBeanByClass(ContextService.class)).getContext();
    }
}
