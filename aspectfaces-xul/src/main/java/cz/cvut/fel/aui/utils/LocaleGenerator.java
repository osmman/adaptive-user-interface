package cz.cvut.fel.aui.utils;

import java.io.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class LocaleGenerator {

    public static void main(String[] args) throws IOException {
        Locale[] locales = {Locale.US, new Locale("cs","CZ")};
        LocaleGenerator generator = new LocaleGenerator();

        for (Locale locale : locales) {
            generator.generateLocaleFile(locale);
        }
    }

    public void generateLocaleFile(Locale locale) throws IOException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream(new File(String.format("bundle/src/main/resources/bundle/locale_%s.properties",locale.getLanguage())));
        properties.load(input);


        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(String.format("aspectfaces-xul/src/main/xulrunner/chrome/locale/%s-%s/locale.dtd", locale.getLanguage(), locale.getCountry())));
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            output.append(String.format("<!ENTITY %s \"%s\" >", entry.getKey(), entry.getValue()));
            output.append("\r\n");
        }

        output.close();
    }
}
