package cz.cvut.fel.aui.utils.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 30.11.13
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class ValueComparator implements Comparator<String>
{
    Map<String, String> base;
    public ValueComparator(Map<String, String> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        return base.get(a).compareToIgnoreCase(base.get(b));
    }
}
