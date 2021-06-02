package demoProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper extends Throwable implements ExceptionMapper<Throwable> {

    Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

    public GenericExceptionMapper() {
        logger.info("Generic Mapper Created now");
    }

    @Override
    public Response toResponse(Throwable throwable) {
        logger.debug("toResponse Generic Method Called");
        ErrorResponse errorResponse = new ErrorResponse("Unknown Error Occurred", 500);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
