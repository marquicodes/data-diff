package me.markakis.demo.datadiff.web;

import org.springframework.http.HttpStatus;

/**
 * A structure for sending error response to API calls.
 * 
 * @author marqui
 */
public class ApiError extends ApiResponse {

    private String details;

    /**
     * Constructs an ApiError with the specified status and error message.
     * 
     * @param status
     *            the HTTP status code.
     * @param error
     *            a user-friendly error message for the API call.
     */
    public ApiError(HttpStatus status, String error) {
        super(status, error);
    }

    /**
     * Constructs an ApiError with the specified status, error message and cause
     * of the error.
     * 
     * @param status
     *            the HTTP status code.
     * @param error
     *            a user-friendly error message for the API call.
     * @param ex
     *            the cause of the error.
     */
    public ApiError(HttpStatus status, String error, Throwable ex) {
        super(status, error);
        this.details = ex.getMessage();
    }

    /**
     * Constructs an ApiError with the specified status, error message and
     * details of the error.
     * 
     * @param status
     *            the HTTP status code.
     * @param error
     *            a user-friendly error message for the API call.
     * @param details
     *            details of the error.
     */
    public ApiError(HttpStatus status, String error, String details) {
        super(status, error);
        this.details = details;
    }

    /**
     * Returns the cause of the error.
     * 
     * @return the cause of the error.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the cause of the error.
     * 
     * @param details
     *            the cause of the error.
     */
    public void setDetails(String details) {
        this.details = details;
    }

}
