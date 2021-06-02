package demoProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoSuchElementFoundExceptionMapper implements ExceptionMapper<NoSuchElementFoundException> {

    Logger logger = LoggerFactory.getLogger(NoSuchElementFoundExceptionMapper.class);

    @Override
    public Response toResponse(NoSuchElementFoundException e) {
        logger.error("Error: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Entity Doesn't Exists", 404);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
