package com.maveric.transactionservice.customvalidation;

import com.maveric.transactionservice.constants.Type;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumTypePatternValidator implements ConstraintValidator<EnumTypePattern, Type> {
    private Type[] subset;

    @Override
    public void initialize(EnumTypePattern constraint) {
        System.out.println("Inside initialize");
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Type value, ConstraintValidatorContext context) {
        System.out.println("Inside isValid");
        return value == null || Arrays.asList(subset).contains(value);
    }


}
