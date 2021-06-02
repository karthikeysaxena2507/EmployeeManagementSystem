package demoProject.controllers;

import demoProject.exceptions.NoSuchElementFoundException;
import demoProject.models.Department;
import demoProject.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String hello() {
        return "hello";
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        return Response
                .status(Response.Status.OK)
                .entity(departmentService.getAllDepartments())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") Long departmentId) throws NoSuchElementFoundException {
//        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(departmentService.getDepartmentById(departmentId))
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
//        }
//        catch(NoSuchElementFoundException exc) {
//            NoSuchElementFoundExceptionMapper mapper = new NoSuchElementFoundExceptionMapper();
//            return mapper.toResponse(exc);
//        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDepartment(@Valid Department department) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentService.addDepartment(department))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@Valid Department department) throws NoSuchElementFoundException {
        return Response
                .status(Response.Status.OK)
                .entity(departmentService.updateDepartment(department))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteDepartment(@PathParam("id") Long departmentId) throws NoSuchElementFoundException {
        departmentService.deleteDepartment(departmentId);
        return Response
                .status(Response.Status.OK)
                .entity("SUCCESS: DEPARTMENT DELETED")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
