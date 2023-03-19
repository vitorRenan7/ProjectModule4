package br.com.totvs.hotel.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelularValidator implements ConstraintValidator<Celular, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        String expression = "^(?:\\(\\d{2}\\)\\s*|\\d{2}\\s*)?(?:\\d{4,5}-?\\d{4})$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

}
