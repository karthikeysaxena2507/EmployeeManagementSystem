package demoProject.config;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        /** Will include all classes with @Path and @Provider in the package **/
        packages("demoProject");
    }
}