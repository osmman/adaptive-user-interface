package cz.cvut.fel.aui.uiml;

import cz.cvut.fel.aui.uiml.elements.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 16.11.13
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public class UimlToJsf {

    private Document document;

    private Map<String, Element> context;

    private void preProcessing(){

    }

    private void mainProccessing(){
        NodeList nodeList = document.getChildNodes();
        for(int count = 0; count < nodeList.getLength(); count++){
            Node node = nodeList.item(count);
            processNode(node);
        }
    }

    private void processNode(Node node){
        if(node.hasAttributes()){
            NamedNodeMap nodeMap = node.getAttributes();
             Node id = nodeMap.getNamedItem("id");
             if(id != null){
                  context.put(id.getNodeValue(),new Element(id.getNodeValue()));
             }

        }
    }

    private void postProccessing(){

    }

    private void proccess(){
        preProcessing();
        mainProccessing();
        postProccessing();
    }

    private UimlToJsf(Document document) {
        this.document = document;
        System.out.println(context);
    }

    public static void convert(File file) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document doc = dBuilder.parse(file);

        UimlToJsf uimlToJsf = new UimlToJsf(doc);

        uimlToJsf.proccess();
    }
}
