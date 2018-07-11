package me.markakis.demo.datadiff.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * A simple structure for sending response to API calls.
 * 
 * @author marqui
 */
public class ApiResponse {

    private HttpStatus    status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String        message;

    /**
     * Private constructor setting by default local date and time on time-stamp.
     */
    private ApiResponse() {
        super();
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an ApiResponse with the specified status and message.
     * 
     * @param status
     *            the HTTP status code.
     * @param message
     *            a user-friendly info message for the API call.
     */
    public ApiResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    /**
     * Returns the HTTP status code.
     * 
     * @return the HTTP status code.
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     * 
     * @param status
     *            the HTTP status code.
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Returns the the date-time instance of when the API call happened.
     * 
     * @return the the date-time instance of when the API call happened.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a user-friendly (info or error) message for the API call.
     * 
     * @return a user-friendly (info or error) message for the API call.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a user-friendly (info or error) message for the API call.
     * 
     * @param message
     *            a user-friendly (info or error) message for the API call.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
