package cz.cvut.fel.aui.view.aspectfaces;

import com.codingcrayons.aspectfaces.cache.ResourceCache;
import com.codingcrayons.aspectfaces.ondemand.DefaultAFGeneratorHandler;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomáš on 27.12.13.
 */
public class AdaptiveGeneratorHandler extends DefaultAFGeneratorHandler
{

    private static final String DEFAULT_CONFIG = "html";

    private static final int AF_CACHE = -1;

    private Boolean _development = true;

    private Boolean _debug = false;

    public AdaptiveGeneratorHandler(ComponentConfig config)
    {
        super(config, AF_CACHE);

        FacesContext ctx = FacesContext.getCurrentInstance();
        Integer afCache = Integer.parseInt(ctx.getExternalContext().getInitParameter("AF.CACHE"));
        if (afCache == null) {
            afCache = -1;
        }
        super.afCacheTime = afCache;
        _development = Boolean.parseBoolean(ctx.getExternalContext().getInitParameter("AF.DEVELOPMENT"));
        if (_development == null) {
            _development = false;
        }
        _debug = Boolean.parseBoolean(ctx.getExternalContext().getInitParameter("AF.DEBUG"));
        if (_debug == null) {
            _debug = false;
        }
    }

    @Override
    protected InputStream hookCustomInputStream()
    {
        String[] roles = {
                "ADMIN"
        };
        List<String> profiles = new ArrayList<String>();
        String layout = "wide";

        return generateAsIS(classesToInspect, getConfig(), roles, profiles, layout);
    }

    @Override
    protected InputStream createInputStream(String s)
    {
        try {
            if (_development) {
                ResourceCache.getInstance().clear();
            }
            if (_debug) {
                System.out.println(s);
            }
            return new ByteArrayInputStream(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return viewFragmentExceptionIS(e);
        }
    }

    @Override
    protected String getConfig()
    {
        if (configName == null || configName.getValue().isEmpty()) {
            return DEFAULT_CONFIG;
        }
        return configName.getValue();
    }

}
