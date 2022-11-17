package mykotlin.ru.anmo.shared.exceptions.global_handlers

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.ru.anmo.shared.exceptions.model.AppError

@ControllerAdvice
@Slf4j
class GlobalExceptionKotlinHandler {

    @ExceptionHandler(NotImplementedError::class)
    public fun handleNotImplementedError(e: NotImplementedError): ResponseEntity<AppError> {
        return ResponseEntity(AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}