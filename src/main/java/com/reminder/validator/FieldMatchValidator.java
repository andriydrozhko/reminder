package com.reminder.validator;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private String firstFieldName;
    private String secondFieldName;
    private String errorMessage;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        errorMessage = constraintAnnotation.message();
    }

    public boolean isValid(final Object value, final ConstraintValidatorContext cvc) {
        boolean toReturn = false;

        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            //System.out.println("firstObj = "+firstObj+"   secondObj = "+secondObj);

            toReturn = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception e) {
            log.error("Internal server error, can't validate fields: ", e);
        }

        if (!toReturn) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(errorMessage).addNode(firstFieldName).addConstraintViolation();
        }
        return toReturn;
    }
}