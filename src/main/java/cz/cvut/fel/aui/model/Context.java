package cz.cvut.fel.aui.model;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 15.11.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Context {

    private Age age;

    private String language;

    private String country;

    private ScreenSize screenSize;

    public enum Age {
        CHILD,
        STUDENT,
        ADULT,
        ELDER
    }

    public enum ScreenSize{
        none,
        small,
        normal,
        wide
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ScreenSize getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }
}
