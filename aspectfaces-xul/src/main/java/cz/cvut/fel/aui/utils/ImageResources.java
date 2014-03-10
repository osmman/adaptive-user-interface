package cz.cvut.fel.aui.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Tomáš on 5.3.14.
 */
@Named
@Singleton
@Startup
public class ImageResources {

    private Properties properties;

    @PostConstruct
    private void init() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/images.properties"));
    }

    public String getImage(String id) {
        return properties.getProperty(id);
    }
}
