package cz.cvut.fel.aui.uiml.elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 16.11.13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class Element {

    private String id;

    private Map<String, Object> params;

    public Element(String id) {
        this.id = id;
        params = new HashMap<String, Object>();
    }

    public Object getParam(String param){
        return params.get(param);
    }

    public void addParam(String param, Object value){
        params.put(param,value);
    }
}
