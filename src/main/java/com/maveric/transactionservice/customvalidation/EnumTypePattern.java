package com.maveric.transactionservice.customvalidation;

import com.maveric.transactionservice.constants.Type;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumTypePatternValidator.class)
public @interface EnumTypePattern {
    Type[] anyOf();
    String message() default "Type should be - 'CREDIT' or 'DEBIT'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
