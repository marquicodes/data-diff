package me.markakis.demo.datadiff.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import me.markakis.demo.datadiff.web.ApiError;
import me.markakis.demo.datadiff.web.ApiResponse;

/**
 * Contains common functionality that is used by its subclasses.
 * 
 * @author marqui
 */
public abstract class BaseController {

    /**
     * Builds and returns a ResponseEntity instance for success requests.
     * 
     * @param message
     *            info message about the request.
     * @return a ResponseEntity instance.
     */
    protected ResponseEntity<Object> successResponse(String message) {
        return buildResponseEntity(new ApiResponse(HttpStatus.OK, message));
    }

    /**
     * Builds and returns a ResponseEntity instance for requests containing
     * binding errors.
     * 
     * @param bindingErrors
     *            object containing binding errors.
     * @return a ResponseEntity instance containing the errors.
     */
    protected ResponseEntity<Object> bindingErrorsResponse(Errors bindingErrors) {
        List<String> errors = new ArrayList<String>();
        for (ObjectError error : bindingErrors.getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (FieldError error : bindingErrors.getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return buildResponseEntity(
                new ApiError(HttpStatus.BAD_REQUEST, "Malformed request", String.join(", ", errors)));
    }

    /**
     * Builds the ResponseEntity object from the supplied ApiResponse instance.
     * 
     * @param apiResponse
     *            the object containing info about the result of the call.
     * @return a ResponseEntity instance.
     */
    private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
