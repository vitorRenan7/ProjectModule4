package br.com.totvs.hotel.validation.validator;

import br.com.totvs.hotel.validation.annotation.Idade;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class IdadeValidator implements ConstraintValidator<Idade, String> {
    private String pattern;
    private Integer minimum;
    private Integer maximum;

    @Override
    public void initialize(Idade constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        pattern = constraintAnnotation.pattern();
        minimum = constraintAnnotation.minimum();
        maximum = constraintAnnotation.maximum();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate;

        try {
            localDate = LocalDate.parse(s, dateTimeFormatter);
        } catch (DateTimeParseException exception) {
            return false;
        }

        LocalDate dataAtual = LocalDate.now();
        Integer diferencaAno = dataAtual.minusYears(localDate.getYear()).getYear();

        if (localDate.isAfter(dataAtual)) {
            return false;
        }
        return !(diferencaAno < minimum | diferencaAno > maximum);
    }

}
