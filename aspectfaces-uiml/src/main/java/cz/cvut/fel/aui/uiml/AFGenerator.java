package cz.cvut.fel.aui.uiml;

import com.codingcrayons.aspectfaces.AFWeaver;
import com.codingcrayons.aspectfaces.configuration.Context;
import com.codingcrayons.aspectfaces.configuration.StaticConfiguration;
import com.codingcrayons.aspectfaces.exceptions.AFException;
import com.codingcrayons.aspectfaces.exceptions.AnnotationDescriptorNotFoundException;
import com.codingcrayons.aspectfaces.exceptions.ConfigurationFileNotFoundException;
import com.codingcrayons.aspectfaces.exceptions.ConfigurationParsingException;
import com.codingcrayons.aspectfaces.metamodel.JavaInspector;
import com.codingcrayons.aspectfaces.plugins.saving.SavingPlugin;
import com.codingcrayons.aspectfaces.util.Strings;
import cz.cvut.fel.aui.uiml.model.Person;
import cz.cvut.fel.aui.uiml.model.PersonInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Tomáš on 28.3.14.
 */
public class AFGenerator {

    private static final String PATH = "src/main/resources/af/";

    private static Class<?> classes[][] = {{Person.class, PersonInfo.class}, {cz.cvut.fel.aui.uiml.model.Context.class}};

    public static void main(String[] args) throws Exception{
        new AFGenerator().make("structure");
        new AFGenerator().make("style");
        new AFGenerator().make("layout");
    }

    /**
     * Build a selected profile
     * @throws Exception
     */
    public void make(String profile) throws Exception {

        Context context = init();
        context.setLayout("templates/"+profile+".xml");
        context.getVariables().put("profile", profile);

        AFWeaver af = new AFWeaver(profile);

        SavingPlugin saver = new SavingPlugin();
        for (Class<?>[] clazz : classes) {
            String widget = inspectAndTranslate(af, clazz, context);
            String path = saver.save(widget, context);
            System.out.println(path);
        }

    }

    protected Context init() throws ConfigurationFileNotFoundException, ConfigurationParsingException, AnnotationDescriptorNotFoundException {
        AFWeaver.init();
        AFWeaver.registerAllAnnotations();
        AFWeaver.addConfiguration(new StaticConfiguration("structure"), new File(PATH + "structure.config.xml"), false, true);
        AFWeaver.addConfiguration(new StaticConfiguration("style"), new File(PATH + "style.config.xml"), false, true);
        AFWeaver.addConfiguration(new StaticConfiguration("layout"), new File(PATH + "layout.config.xml"), false, true);

        Context context = new Context();
        context.setUseCover(true);
        context.getVariables().put("af", "rocks!");
        context.getVariables().put("dummyObject", new Object());
        context.setCollate(true);
        return context;
    }

    protected String inspectAndTranslate(AFWeaver afWeaver, Class<?>[] clazz, Context context)
            throws AFException, IOException {

        afWeaver.setInspector(new JavaInspector(clazz));
        context.setFragmentName(makeName(clazz[0], context));
        return afWeaver.generate(context);
    }

    protected String makeName(Class<?> clazz, Context context){
        return Strings.lowerFirstLetter(clazz.getSimpleName());
    }
}
