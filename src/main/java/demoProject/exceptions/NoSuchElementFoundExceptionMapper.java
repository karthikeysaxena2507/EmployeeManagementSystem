package demoProject.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class NoSuchElementFoundExceptionMapper implements ExceptionMapper<NoSuchElementFoundException> {

    @Override
    public Response toResponse(NoSuchElementFoundException e) {
        log.error("Error: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Entity Doesn't Exists", 404);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
