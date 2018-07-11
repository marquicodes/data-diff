package me.markakis.demo.datadiff.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Performs <code>Data</code> validation according to {@code Validator}
 * interface.
 * 
 * @author marqui
 */
@Component
public class DataRequestValidator implements Validator {

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
        String data = (String) target;
        if (StringUtils.isEmpty(data.trim())) {
            errors.reject("REQ_BODY_EMPTY",
                    "Request body should not be blank or contain only white space characters.");
        }
    }

}
