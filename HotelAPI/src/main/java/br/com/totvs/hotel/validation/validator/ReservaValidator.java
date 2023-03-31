package br.com.totvs.hotel.validation.validator;

import br.com.totvs.hotel.validation.annotation.Reserva;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReservaValidator implements ConstraintValidator<Reserva, String> {
    private String pattern;

    @Override
    public void initialize(Reserva constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        try {
            LocalDateTime.parse(s, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

}
