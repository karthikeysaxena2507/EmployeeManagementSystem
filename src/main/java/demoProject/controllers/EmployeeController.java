package demoProject.controllers;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Employee;
import demoProject.repositories.EmployeeRepository;
import demoProject.repositories.EmployeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        return Response
                .status(Response.Status.OK)
                .entity(employeeRepository.getAllEmployees())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Long employeeId) throws NoSuchElementFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(employeeRepository.getEmployeeById(employeeId))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name") String name) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeRepository.getEmployeeByName(name))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/get/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesByDepartmentId(@PathParam("departmentId") Long departmentId) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeRepository.findEmployeesByDepartmentId(departmentId))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(@Valid Employee employee){
        return Response
                .status(Response.Status.OK)
                .entity(employeeRepository.addEmployee(employee))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@Valid Employee employee) throws NoSuchElementFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(employeeRepository.updateEmployee(employee))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Long employeeId) throws NoSuchElementFoundException {
        employeeRepository.deleteEmployee(employeeId);
        return Response
                .status(Response.Status.OK)
                .entity("SUCCESS: EMPLOYEE DELETED")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
