package br.com.totvs.hotel.validation.validator;

import br.com.totvs.hotel.validation.annotation.RG;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RGValidator implements ConstraintValidator<RG, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        String expression = "^([0-9]{1,3}).?([0-9]{1,3}).?([0-9]{1,3})-?([0-9Xx])$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

}
