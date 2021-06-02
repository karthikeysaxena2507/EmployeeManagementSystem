package demoProject.exceptions;

public class ErrorResponse {

    private String errorMessage;

    private Integer status;

    public ErrorResponse() {

    }

    public ErrorResponse(String errorMessage, Integer status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
