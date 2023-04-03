package br.com.totvs.hotel.validation.annotation;

import br.com.totvs.hotel.validation.validator.IdadeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdadeValidator.class)
public @interface Idade {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "idade informada inválida";
    String pattern() default "dd/MM/yyyy";
    int minimum() default 0;
    int maximum() default 100;

}
