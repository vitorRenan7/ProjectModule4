package br.com.totvs.hotel.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RGValidator.class)
public @interface RG {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "rg informado é inválido";

}
