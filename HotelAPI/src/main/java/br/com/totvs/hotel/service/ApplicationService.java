package br.com.totvs.hotel.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

}
