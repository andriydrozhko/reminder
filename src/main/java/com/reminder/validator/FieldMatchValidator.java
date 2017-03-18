package com.reminder.validator;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

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
            e.printStackTrace();
        }

        if (!toReturn) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(errorMessage).addNode(firstFieldName).addConstraintViolation();
        }
        return toReturn;
    }
}