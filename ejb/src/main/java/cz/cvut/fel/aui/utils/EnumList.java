package cz.cvut.fel.aui.utils;

import cz.cvut.fel.aui.model.Person;
import cz.cvut.fel.aui.model.PersonInfo;
import cz.cvut.fel.aui.model.context.Age;
import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import cz.cvut.fel.aui.utils.comparator.ValueComparator;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
@Named("enum")
public class EnumList {
    public Age[] getAge() {
        return Age.values();
    }

    public ScreenSize[] getScreenSize() {
        return ScreenSize.values();
    }

    public Map<String, String> getCountry() {
        Map<String, String> countries = new HashMap<String, String>();
        for (String countryCode : Locale.getISOCountries()) {
            if (countryCode.equals("CZ") || countryCode.equals("US")) {
                Locale obj = new Locale("", countryCode);
                countries.put(countryCode, obj.getDisplayCountry());
            }
        }

        ValueComparator comparator = new ValueComparator(countries);
        Map<String, String> sorted = new TreeMap<String, String>(comparator);
        sorted.putAll(countries);
        return sorted;
    }

    public Device[] getDevice() {
        return Device.values();
    }

    public PersonInfo.Title[] getTitle(){
        return PersonInfo.Title.values();
    }

    public PersonInfo.Gender[] getGender() {
        return PersonInfo.Gender.values();
    }

}
