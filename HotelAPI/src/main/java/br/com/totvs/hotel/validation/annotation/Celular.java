package br.com.totvs.hotel.validation.annotation;

import br.com.totvs.hotel.validation.validator.CelularValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CelularValidator.class)
public @interface Celular {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "celular informado inválido";

}
