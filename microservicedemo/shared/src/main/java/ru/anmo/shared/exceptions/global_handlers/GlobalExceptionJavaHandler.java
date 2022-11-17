package java.ru.anmo.shared.exceptions.global_handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.ru.anmo.shared.exceptions.model.AppError;
import java.ru.anmo.shared.exceptions.model.EasterEggException;
import java.ru.anmo.shared.exceptions.model.IllegalIdException;
import java.ru.anmo.shared.exceptions.model.InternalException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionJavaHandler {

    @ExceptionHandler(IllegalIdException.class)
    public ResponseEntity<AppError> handleIllegalIdException(IllegalIdException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EasterEggException.class)
    public ResponseEntity<AppError> handleEasterEggException(EasterEggException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.I_AM_A_TEAPOT.value(), "You found a easter egg, take a cake"), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<AppError> handleInternalException(InternalException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
