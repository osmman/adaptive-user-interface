package cz.cvut.fel.aui.view;

import com.codingcrayons.aspectfaces.cache.ResourceCache;
import com.codingcrayons.aspectfaces.ondemand.DefaultAFGeneratorHandler;
import cz.cvut.fel.aui.model.Context;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.utils.FacUtil;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Tomáš on 28.12.13.
 */
public class AdaptiveGeneratorHandler extends DefaultAFGeneratorHandler
{
    private static final String DEFAULT_CONFIG = "default";

    private static final String DEFAULT_LAYOUT = "form.xhtml";

    public AdaptiveGeneratorHandler(ComponentConfig config)
    {
        super(config);
    }

    /*
     * Applies Adaptive settings
     */
    private String applySettings(String base, String calcLayout)
    {
        if (layout != null) {
            calcLayout = layout.getValue();
            calcLayout = (String) executeExpressionInElContext(FacesContext.getCurrentInstance()
                    .getApplication().getExpressionFactory(), FacesContext.getCurrentInstance()
                    .getELContext(), calcLayout);
        }
        return (calcLayout == null) ? null : ("layouts/" + base + '/' + calcLayout);
    }

    @Override
    protected InputStream createInputStream(String s)
    {
        try {

            if (true) {
                ResourceCache.getInstance().clear();
            }
            if (true) {
                System.out.println(s);
            }

            return new ByteArrayInputStream(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return viewFragmentExceptionIS(e);
        }
    }

    @Override
    protected void hookAddToAFContext(com.codingcrayons.aspectfaces.configuration.Context context)
    {
        Context config = getContext();

        if(config.getDevice() == Device.PHONE || config.getDevice() == Device.TABLET){
            context.setLayout(applySettings("mobile", null));
            context.getVariables().put("table", "list");
        }else {
            context.setLayout(applySettings("desktop",null));
        }

        context.setProfiles(new String[]{
                config.getAge().name().toLowerCase(),
                "COUNTRY_"+config.getCountry()
        });
        context.getVariables().put("country", config.getCountry());
        context.getVariables().put("applyImage", config.getAge() == Age.CHILD);
        context.getVariables().put("applyHelp", config.getAge() == Age.ELDER);
    }

    @Override
    protected String getConfig()
    {
        if(configName == null || configName.getValue().isEmpty()){
            Context config = getContext();
            if(config.getDevice() == Device.PHONE || config.getDevice() == Device.TABLET){
                return "default";
            }else {
                return "default";
            }
        }
        return configName.getValue();
    }

    private Context getContext()
    {
        return (Context) FacUtil.getBeanByName("context");
    }
}
