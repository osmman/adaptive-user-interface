package cz.cvut.fel.aui.utils;

import java.io.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class LocaleGenerator {

    public static void main(String[] args) throws IOException {
        Locale[] locales = {Locale.US, Locale.forLanguageTag("CZ")};
        LocaleGenerator generator = new LocaleGenerator();

        for (Locale locale : locales) {
            generator.generateLocaleFile(locale);
        }
    }

    public void generateLocaleFile(Locale locale) throws IOException {
        Properties properties = new Properties();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("/bundle/locale_" + locale.getLanguage() + ".properties");
        properties.load(input);

        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(String.format("src/main/xulrunner/chrome/locale/%s_%s/locale.dtd", locale.getLanguage(), locale.getCountry())));
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            output.append(String.format("<!ENTITY %s \"%s\" >", entry.getKey(), entry.getValue()));
            output.append("\r\n");
        }

        output.close();
    }
}
