package br.com.totvs.hotel.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CEPValidator.class)
public @interface CEP {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "cep informado é inválido";

}
