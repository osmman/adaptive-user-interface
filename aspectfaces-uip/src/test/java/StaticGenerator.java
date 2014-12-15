import com.codingcrayons.aspectfaces.exceptions.AFException;
import cz.cvut.fel.aui.model.*;
import uip.uipaf.auiGenerator.UipAuiAppGenerator;
import uip.uipaf.auiGenerator.app.FormGeneratorSource;
import uip.uipaf.auiGenerator.app.UipApplication;
import uip.uipaf.auiGenerator.app.writers.UipFileAppWriter;
import uips.support.tools.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StaticGenerator {

    public static void main(String[] args) throws FileNotFoundException, AFException {
        String outputFolder = "./src/test/resources/";


        UipAuiAppGenerator generator = new UipAuiAppGenerator(null);

        List<InputStream> bundleInputStreams = loadBundles();

        FormGeneratorSource[] sources = new FormGeneratorSource[3];
        Class<?>[] classes = new Class<?>[1];
        classes[0] = Context.class;
        sources[0] = new FormGeneratorSource("context.form", "context.form", classes, "submit.context", "cancel", true, "layouts/form.xml", false, null, null, null);


        classes = new Class<?>[4];
        classes[0] = Person.class;
        classes[1] = PersonInfo.class;
        classes[2] = Degree.class;
        classes[3] = Address.class;
        sources[1] = new FormGeneratorSource("person.registration", "person.registration", classes, "submit.person", "cancel", true, "layouts/form.xml", false, null, null, new HashSet<String>() {{
            add("student");
        }});

        classes = new Class<?>[4];
        classes[0] = Person.class;
        classes[1] = PersonInfo.class;
        classes[2] = Degree.class;
        classes[3] = Address.class;
        sources[2] = new FormGeneratorSource("person.detail", "person.list.0", classes, null, "cancel", false, "layouts/detail.xml", true, new HashSet<String>() {{
            add("password");
            add("personInfo");
            add("id");
            add("fullName");
            add("address");
            add("degree");
        }}, null, new HashSet<String>() {{
            add("student");
        }});


//        event.callHandler("integration.aui.generate", {
//                "class.0": "cz.cvut.fel.aui.model.Person",
//                "class.1": "cz.cvut.fel.aui.model.PersonInfo",
//                "class.2": "cz.cvut.fel.aui.model.Address",
//                "class.3": "cz.cvut.fel.aui.model.Degree",
//                "bundle.0": "/bundle/uip_locale_en.properties",
//                "form.name": "person.detail",
//                "model.postfix": "person.list." + modelNumber,
//                "handler.cancel": "cancel",
//                "model.rewrite": "false",
//                "form.readonly": "true",
//                "fields.ignore": "password,personInfo,id,fullName,address,degree",
////    "field.email.name": "true",
//                "form.layout": "layouts/detail.xml",
//                "client.notify.uiUpdate": "false"
//        });

        UipApplication application = generator.generateApplication(sources,
                true, bundleInputStreams, "root", true,
                "apps/registration",
                "./src/test/resources/apps/registration",
                false, null);

        File staticFile = new File(outputFolder + "/static.uipapp");
        UipFileAppWriter.doUiProtocolArchive(application, true, staticFile.getAbsolutePath());

        bundleInputStreams = loadBundles();

        application = generator.generateApplication(
                bundleInputStreams, "root",
                "apps/registration", "./src/test/resources/apps/registration");

        File dynamicFile = new File(outputFolder + "/dynamic.uipapp");
        UipFileAppWriter.doUiProtocolArchive(application, true, dynamicFile.getAbsolutePath());
    }

    private static List<InputStream> loadBundles() throws FileNotFoundException {
        List<InputStream> bundleInputStreams = new ArrayList<InputStream>();

        Class<StaticGenerator> clazz = StaticGenerator.class;
        URL location = clazz.getResource('/' + clazz.getName().replace('.', '/') + ".class");

        InputStream in = null;
        if (location.getProtocol().contains("jar")) {
            in = FileUtils.class.getResourceAsStream("/bundle/uip_locale_en.properties");
        } else {
            in = new FileInputStream("./src/main/resources/bundle/uip_locale_en.properties");
        }
        bundleInputStreams.add(in);

        if (location.getProtocol().contains("jar")) {
            in = FileUtils.class.getResourceAsStream("/bundle/uip_locale_cs.properties");
        } else {
            in = new FileInputStream("./src/main/resources/bundle/uip_locale_cs.properties");
        }
        bundleInputStreams.add(in);

        return bundleInputStreams;
    }

}
