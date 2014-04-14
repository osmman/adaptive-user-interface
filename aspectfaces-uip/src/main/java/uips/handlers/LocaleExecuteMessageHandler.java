package uips.handlers;

import uips.builders.ModelBuilderImp;
import uips.events.api.exceptions.UIPException;
import uips.events.interfaces.IUipAutonomousEventHandler;
import uips.instances.IAppClient;
import uips.instances.IInstance;
import uips.support.settings.tree.IHandlerSettings;
import uips.support.tools.ResultHolder;
import uips.tree.interfaces.IEvent;
import uips.tree.interfaces.IModel;
import uips.uipserver.IUipServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Tomáš on 11.4.14.
 */
public class LocaleExecuteMessageHandler implements IUipAutonomousEventHandler {

    public static final String BUNDLE_MODEL_NAME = "locale.bundle";

    private Map<String, Properties> languages = new HashMap<String, Properties>();

    @Override
    public String getHandlerClass() {
        return "integration.java.locale";
    }

    @Override
    public boolean handleEvent(IEvent eventInn, IAppClient appClient, IInstance instanceReference, ResultHolder resultHolder) {
        IModel user = instanceReference.getModelManager().getModel(appClient, "public.user", true).get(null);
        String language = user.getProperty("locale.language").getValueAttribute();
        ModelBuilderImp builder = new ModelBuilderImp();
        try {
            if (!languages.containsKey(language)) {
                Properties properties = new Properties();
                InputStream input = this.getClass().getClassLoader().getResourceAsStream("/bundle/locale_" + language + ".properties");
                properties.load(input);
                languages.put(language, properties);
            }

            Properties properties = languages.get(language);

            builder.initializeModel(BUNDLE_MODEL_NAME);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                builder.createProperty((String) entry.getValue(), (String) entry.getKey(), null, null);
            }
                        byte[] a = new byte[]{110, 120};
            IModel model = builder.getModelInn();
            appClient.sendModel(model);
        } catch (UIPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void initHandler(IUipServer uipServer, IHandlerSettings settings) {
        // nothing to do
    }

    @Override
    public boolean startBeforeUipEventHandlers() {
        return true;
    }

    @Override
    public boolean startAfterUipEventHandlers() {
        return false;
    }

    @Override
    public Class<? extends IUipAutonomousEventHandler> getHandlerTypeInterfaceClass() {
        return IUipAutonomousEventHandler.class;
    }


}
