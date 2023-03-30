package br.com.totvs.hotel.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class IdadeValidator implements ConstraintValidator<Idade, LocalDate> {
    private int minimum;
    private int maximum;

    @Override
    public void initialize(Idade constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        minimum = constraintAnnotation.minimum();
        maximum = constraintAnnotation.maximum();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
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
