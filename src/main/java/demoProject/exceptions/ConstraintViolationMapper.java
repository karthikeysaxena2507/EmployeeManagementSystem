package demoProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    Logger logger = LoggerFactory.getLogger(ConstraintViolationMapper.class);

    public ConstraintViolationMapper() {
        logger.info("Mapper Created now");
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
