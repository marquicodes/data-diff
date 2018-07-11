package me.markakis.demo.datadiff.web.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.markakis.demo.datadiff.web.ApiError;

/**
 * @author marqui
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleHttpMessageNotReadable(org.
     * springframework.http.converter.HttpMessageNotReadableException,
     * org.springframework.http.HttpHeaders,
     * org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request.";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Builds the ResponseEntity object from the supplied ApiError instance.
     * 
     * @param apiError
     *            the object containing info about the error occurred.
     * @return a ResponseEntity instance.
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
