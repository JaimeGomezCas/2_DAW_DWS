package com.cipfpmislata.validation;


import com.cipfpmislata.exception.ValidationException;
import jakarta.validation.*;

import java.util.Set;


public class DtoValidator {
    public static final Validator validator;
    static {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory();
        validator = factory.getValidator();
    }
    public static <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
