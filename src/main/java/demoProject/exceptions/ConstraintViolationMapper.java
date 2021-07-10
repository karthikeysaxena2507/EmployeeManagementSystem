package demoProject.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
@Slf4j
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    public ConstraintViolationMapper() {
        log.info("Mapper Created now");
    }

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Input", 400);
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
