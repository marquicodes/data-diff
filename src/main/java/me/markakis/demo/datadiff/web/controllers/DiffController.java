package me.markakis.demo.datadiff.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.markakis.demo.datadiff.constants.Constants;
import me.markakis.demo.datadiff.constants.DiffSide;
import me.markakis.demo.datadiff.services.DiffService;
import me.markakis.demo.datadiff.web.validators.DataRequestValidator;

/**
 * The REST controller to map the end-points for receiving data and returning
 * comparison results.
 * 
 * @author marqui
 */
@RestController
@RequestMapping("/v" + Constants.REST_API_1 + "/diff/{id}")
public class DiffController extends BaseController {

    @Autowired
    private DataRequestValidator dataRequestValidator;

    @Autowired
    private DiffService          diffService;

    /**
     * The end-point to receive the left-side data for comparison.
     * 
     * @param id
     *            identifier of the comparison.
     * @param data
     *            the base64 encoded data used for the left-side comparison.
     * @param errors
     *            contains information about data-binding and validation errors
     *            for the data posted by the user.
     * @return a ResponseEntity instance with request status.
     */
    @PostMapping("/left")
    public ResponseEntity<Object> receiveLeft(@PathVariable("id") int id, @RequestBody String data, Errors errors) {
        dataRequestValidator.validate(data, errors);
        if (errors.hasErrors()) {
            return bindingErrorsResponse(errors);
        }
        diffService.save(id, data, DiffSide.LEFT);
        return successResponse("Success");
    }

    /**
     * The end-point to receive the right-side data for comparison.
     * 
     * @param id
     *            identifier of the comparison.
     * @param data
     *            the base64 encoded data used for the right-side comparison.
     * @param errors
     *            contains information about data-binding and validation errors
     *            for the data posted by the user.
     * @return a ResponseEntity instance with request status.
     */
    @PostMapping("/right")
    public ResponseEntity<Object> receiveRight(@PathVariable("id") int id, @RequestBody String data, Errors errors) {
        dataRequestValidator.validate(data, errors);
        if (errors.hasErrors()) {
            return bindingErrorsResponse(errors);
        }
        diffService.save(id, data, DiffSide.RIGHT);
        return successResponse("Success");
    }

    /**
     * The end-point to return data comparison results.
     * 
     * @param id
     *            identifier of the comparison.
     * @return the ResponseEntity with the result of the requested data
     *         comparison.
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> result(@PathVariable("id") int id) {
        String result = diffService.compare(id);
        return successResponse(result);
    }

}
