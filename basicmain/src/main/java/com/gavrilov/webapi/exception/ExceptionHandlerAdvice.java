package com.gavrilov.webapi.exception;

import com.gavrilov.core.exception.ExceptionData;
import com.gavrilov.core.exception.ModelValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ModelValidationException.class)
    public ResponseEntity<?> handle(ModelValidationException mve) {
        ExceptionData exceptionData = new ExceptionData(mve.getCode(), mve.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionData);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handle(BadCredentialsException mve) {
        ExceptionData exceptionData = new ExceptionData("error.authentication.user", "Не верно указаны данные при авторизации");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionData);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handle(AccessDeniedException mve) {
        ExceptionData exceptionData = new ExceptionData("error.access.denied", "У вас ограничен доступ к данной функции. Обратитесь к администратору");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionData);
    }
}
