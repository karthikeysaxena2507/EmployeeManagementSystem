package demoProject.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class GenericExceptionMapper extends Throwable implements ExceptionMapper<Throwable> {

    public GenericExceptionMapper() {
        log.info("Generic Mapper Created now");
    }

    @Override
    public Response toResponse(Throwable throwable) {
        log.debug("toResponse Generic Method Called");
        ErrorResponse errorResponse = new ErrorResponse("Unknown Error Occurred", 500);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
