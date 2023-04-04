package br.com.totvs.hotel.validation.validator;

import br.com.totvs.hotel.validation.annotation.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    private Class<? extends Enum<?>> enumerator;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        enumerator = constraintAnnotation.enumerator();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        return Arrays.stream(enumerator.getEnumConstants()).map(Enum::name).toList().contains(s);
    }

}
