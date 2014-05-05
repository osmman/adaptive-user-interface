package cz.cvut.fel.caf.test;

import cz.cvut.fel.caf.*;
import cz.cvut.fel.caf.impl.contexts.ContextItemImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.mockito.Mockito.*;


@RunWith(Arquillian.class)
public class ContextServiceTest {

    private static final String WSDL_PATH = "contextWS?wsdl";

    @Deployment
    public static WebArchive createDeployment() {

        MavenDependencyResolver resolver = DependencyResolvers.use(
                MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

        WebArchive archive = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, ContextService.class.getPackage())
                .addAsLibraries(
                        resolver.artifact("org.mockito:mockito-core")
                                .resolveAsFiles())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return archive;
    }

    @Inject
    ContextService service;

    @Mock
    ContextObserver observer1;

    @Mock
    ContextObserver observer2;

    @Mock
    ContextProvider provider;

    ContextItem context;

    private Client client;

//    @ArquillianResource(WebServiceProvider.class)
//    private URL deploymentUrl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        context = new ContextItemImpl("language", "cs");

//        try {
//            client = new Client(new URL(deploymentUrl, WSDL_PATH));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void observers() {
        service.addObserver(observer1);
        service.addContextItem(context);
        verify(observer1, times(1)).contextChanged(eq(context), eq(ContextState.ADDED), any(ContextService.class));
    }

    @Test
    public void contextRemoved() {
        service.addObserver(observer1);
        service.addObserver(observer2);

        service.addContextItem(context);
        service.removeContextItem(context);

        verify(observer1, times(1)).contextChanged(eq(context), eq(ContextState.REMOVED), any(ContextService.class));
        verify(observer1, times(1)).contextChanged(eq(context), eq(ContextState.ADDED), any(ContextService.class));
    }

}
