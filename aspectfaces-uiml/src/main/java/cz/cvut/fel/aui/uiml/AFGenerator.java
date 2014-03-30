package cz.cvut.fel.aui.uiml;

import com.codingcrayons.aspectfaces.AFWeaver;
import com.codingcrayons.aspectfaces.configuration.Configuration;
import com.codingcrayons.aspectfaces.configuration.Context;
import com.codingcrayons.aspectfaces.configuration.StaticConfiguration;
import com.codingcrayons.aspectfaces.exceptions.*;
import com.codingcrayons.aspectfaces.metamodel.JavaInspector;
import com.codingcrayons.aspectfaces.variableResolver.TagParserException;

import java.io.File;
import java.net.URL;

/**
 * Created by Tomáš on 28.3.14.
 */
public class AFGenerator {

    public static void main(String[] args){
        try{
        AFWeaver.init();
        AFWeaver.registerAllAnnotations();
        AFWeaver.addConfiguration(new StaticConfiguration("default"),getResource("/af/default.config.xml"),false,false);

        // context
        Context context = new Context();
        context.setProfiles(new String[]{"US"});
        context.getVariables().put("myVariable", "CustomValue");

        // config with mappings
        File file = new File(getResource("/aspectfaces-config.xml").getPath());
        Configuration configuration = new StaticConfiguration(file.getName());

        // add mapping manually
        //configuration.addMapping(new Mapping("Long", "path"));
        context.setConfiguration(configuration);



        // link to the config
        AFWeaver weaver = new AFWeaver("default");
        // passing what to process to the inspection
        weaver.setInspector(new JavaInspector(cz.cvut.fel.aui.uiml.model.Context.class));

        // output
        String out = weaver.generate(context);
        System.out.println(out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static URL getResource(String s){
        return AFGenerator.class.getResource(s);
    }
}
