package demoProject.controllers;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/departments")
public class DepartmentController {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.getAllDepartments())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") Long departmentId) throws NoSuchElementFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.getDepartmentById(departmentId))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDepartment(@Valid Department department) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.addDepartment(department))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@Valid Department department) throws NoSuchElementFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.updateDepartment(department))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") Long departmentId) throws NoSuchElementFoundException {
        departmentRepository.deleteDepartment(departmentId);
        return Response
                .status(Response.Status.OK)
                .entity("SUCCESS: DEPARTMENT DELETED")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentByName(@PathParam("name") String name) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.getDepartmentByName(name))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/count/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentsByMinEmployees(@PathParam("count") Long count) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.findDepartmentsByMinNoOfEmployees(count))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
