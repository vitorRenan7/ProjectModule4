package br.com.totvs.hotel.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class ApplicationService {
    @Autowired
    private Validator validator;

    public void validarCampo(Object objeto, Object campoValor, String campoNome) {
        if (campoValor == null) {
            return;
        }

        Set<ConstraintViolation<Object>> violations = validator.validateProperty(objeto, campoNome);
        if (!violations.isEmpty()) {
            String message = violations.iterator().next().getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public <T> void validarCampos(T t) {
        List<String> erros = new ArrayList<String>();
        Set<ConstraintViolation<T>> violations = validator.validate(t);

        for (ConstraintViolation<T> violation : violations) {
            String campoNome = violation.getPropertyPath().toString();
            Field campo;
            Object valor;

            try {
                campo = t.getClass().getDeclaredField(campoNome);
                campo.setAccessible(true);
                valor = campo.get(t);
            } catch (NoSuchFieldException | IllegalAccessException exception) {
                throw new RuntimeException(exception);
            }

            if (valor == null) {
                continue;
            }
            erros.add(violation.getMessage());
        }

        if (!erros.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Arrays.toString(erros.toArray()));
        }
    }

}
