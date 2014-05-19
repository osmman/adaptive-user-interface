package cz.cvut.fel.aui.utils.context;

import cz.cvut.fel.caf.ContextItem;

/**
 * Created by Tomáš on 8. 5. 2014.
 */
public class Locale implements ContextItem {

    private String country;

    private String language;

    public Locale(String country, String language) {
        this.country = country;
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }
}
