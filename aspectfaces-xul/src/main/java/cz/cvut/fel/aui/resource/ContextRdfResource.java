package cz.cvut.fel.aui.resource;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.RDFWriterFImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;

/**
 * Created by Tomáš on 3. 5. 2014.
 */
@Path("/context")
public class ContextRdfResource {

    @GET
    @Produces({MediaType.TEXT_XML})
    public String getContext() {
        Model model = ModelFactory.createDefaultModel();
        Resource origin = model.createResource("http://aaa.cc/rdf/context");
        origin.addProperty(model.createProperty("http://aaa.cc/rdf/context"), "fas");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        RDFWriterFImpl nn = new RDFWriterFImpl();
        nn.getWriter().write(model,os,"http://aaa.cc/rdf/context");
        return os.toString();
    }
}
