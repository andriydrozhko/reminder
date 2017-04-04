package com.reminder.validator;

import com.reminder.entity.User;
import com.reminder.exception.RegistrationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.reminder.validator.UserValidator.BAD_VALIDATION_PARAMETERS_MESSAGE;

public class UserValidatorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static final String FAILED_VALIDATION_MESSAGE = "Failed validation message";

    private static final String FAKE_USER_NAME = "FAKE_USER_NAME";

    @Test
    public void testSuccessUserValidation() throws RegistrationException {
        User user = new User();
        user.setUserName(FAKE_USER_NAME);
        UserValidator.validate(user, usr -> FAKE_USER_NAME.equals(usr.getUserName()), FAILED_VALIDATION_MESSAGE);
    }

    @Test
    public void testFailedUserValidation() throws RegistrationException {
        User user = new User();
        user.setUserName(FAKE_USER_NAME + "_1");

        exception.expect(RegistrationException.class);
        exception.expectMessage(FAILED_VALIDATION_MESSAGE);
        UserValidator.validate(user, usr -> FAKE_USER_NAME.equals(usr.getUserName()), FAILED_VALIDATION_MESSAGE);
    }

    @Test
    public void testUserValidationWithNullableUser() throws RegistrationException {
        exception.expect(RegistrationException.class);
        exception.expectMessage(BAD_VALIDATION_PARAMETERS_MESSAGE);
        UserValidator.validate(null, usr -> Boolean.TRUE, FAILED_VALIDATION_MESSAGE);
    }

    @Test
    public void testUserValidationWithNullablePredicate() throws RegistrationException {
        exception.expect(RegistrationException.class);
        exception.expectMessage(BAD_VALIDATION_PARAMETERS_MESSAGE);
        UserValidator.validate(new User(), null, FAILED_VALIDATION_MESSAGE);
    }


}
