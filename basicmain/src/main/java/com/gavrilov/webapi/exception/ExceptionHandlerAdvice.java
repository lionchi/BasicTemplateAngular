package com.gavrilov.webapi.exception;

import com.gavrilov.core.exception.ExceptionData;
import com.gavrilov.core.exception.ModelValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ModelValidationException.class)
    public ResponseEntity<?> handle(ModelValidationException mve) {
        logger.error("Создаваемая или изменяемая вами сущность не прошла валидацию: " + mve.getMessage(), mve);
        ExceptionData exceptionData = new ExceptionData(mve.getCode(), mve.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionData);
    }
}
