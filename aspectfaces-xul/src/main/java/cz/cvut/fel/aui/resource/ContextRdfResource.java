package cz.cvut.fel.aui.resource;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.ByteArrayOutputStream;

import cz.cvut.fel.aui.annotations.Current;
import cz.cvut.fel.aui.model.Context;

@Path("/context")
public class ContextRdfResource {

	@Inject
	@Current
	private Context auiContext;

	@GET
	@Produces({MediaType.TEXT_XML})
	public String getContext() {
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("ctx","http://aui.cz/rdf/");
		Resource context = model.createResource("http://aui.cz/rdf/context");
		model.add(context, property("age"), auiContext.getAge().toString());
		model.add(context, property("country"), auiContext.getCountry().toString());
		model.add(context, property("language"), auiContext.getLanguage().toString());
		model.add(context, property("device"), auiContext.getDevice().toString());
		model.add(context, property("invalid"), auiContext.getInvalid().toString());
		model.add(context, property("screenSize"), auiContext.getScreenSize().toString());


		ByteArrayOutputStream os = new ByteArrayOutputStream();
		model.write(os);
		return os.toString();
	}

	private Property property(String local){
		return ResourceFactory.createProperty("http://aui.cz/rdf/", local);
	}
}
