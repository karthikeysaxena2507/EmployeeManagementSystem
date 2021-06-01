package demoProject.config;

import demoProject.controllers.DepartmentController;
import demoProject.controllers.EmployeeController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(EmployeeController.class);
        register(DepartmentController.class);
    }
}