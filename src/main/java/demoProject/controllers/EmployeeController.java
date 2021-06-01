package demoProject.controllers;

import demoProject.models.Employee;
import demoProject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.getAllEmployees())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long employeeId) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.getEmployeeById(employeeId))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(@Valid Employee employee) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.addEmployee(employee))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@Valid Employee employee) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.updateEmployee(employee))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteEmployee(@PathParam("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return Response
                .status(Response.Status.OK)
                .entity("SUCCESS: EMPLOYEE DELETED")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
