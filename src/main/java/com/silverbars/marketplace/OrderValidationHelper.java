package com.silverbars.marketplace;


import com.silverbars.domain.Order;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helper class to assist with the validation of Order creation requests.
 */
public class OrderValidationHelper {
    private final Validator validator;

    public OrderValidationHelper(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Validates the given order.
     * @throws MarketPlaceException if the Order is deemed to be invalid.
     * @param order
     */
    public void validate(Order order) {
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid Order: ");
            sb.append(violations.stream().map(v -> v.getMessage()).collect(Collectors.joining(",")));
            // Typically would actually throw a subclass of this - ie ValidationException
            // But as we have no other exceptions in this small example we will just
            // use the base class.
            throw new MarketPlaceException(sb.toString());
        }
    }
}
