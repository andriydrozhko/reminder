package com.reminder.validator;

import com.reminder.entity.User;
import com.reminder.exception.RegistrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public final class UserValidator {

    private UserValidator() { }

    private static final Logger log = LoggerFactory.getLogger(UserValidator.class);

    static final String BAD_VALIDATION_PARAMETERS_MESSAGE = "Wrong validation parameters";

    public static void validate(User user, Predicate<User> expectedPredicate, String errorMessage) throws RegistrationException {
        if (null == user || null == expectedPredicate) {
            throw new RegistrationException(BAD_VALIDATION_PARAMETERS_MESSAGE);
        }

        if (!expectedPredicate.test(user)) {
            log.info("User validation failed: error message: ", errorMessage);
            throw new RegistrationException(errorMessage);
        }

    }

}
