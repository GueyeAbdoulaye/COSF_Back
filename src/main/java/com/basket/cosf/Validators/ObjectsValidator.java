package com.basket.cosf.Validators;

import com.basket.cosf.Exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate) {
        if (objectToValidate == null) {
            throw new ObjectValidationException(
                Set.of("Object to validate cannot be null"), 
                "Unknown"
            );
        }

        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            Set<String> errorMessages = violations.stream()
                    .map(violation -> {
                        String propertyPath = violation.getPropertyPath().toString();
                        String message = violation.getMessage();
                        return propertyPath + ": " + message;
                    })
                    .collect(Collectors.toSet());

            if (errorMessages.isEmpty()) {
                errorMessages.add("Validation failed without specific messages");
            }

            throw new ObjectValidationException(
                errorMessages, 
                objectToValidate.getClass().getName()
            );
        }
    }
}