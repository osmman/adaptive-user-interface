package cz.cvut.fel.aui.uiml;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Tomáš
 * Date: 16.11.13
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class UimlToJsfTest {

    private File in = null;

    @Before
    public void setup() {
        URL url = this.getClass().getResource("/helloWorld.uiml");
        in = new File(url.getFile());
    }

    @Test
    public void testFileExistence(){
        Assert.assertNotNull("Test file missing", in);
    }

    @Test
    public void testConvert() throws IOException, SAXException, ParserConfigurationException {
        UimlToJsf.convert(in);
    }
}
