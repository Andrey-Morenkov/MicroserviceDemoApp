package java.ru.anmo.shared.exceptions.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class IllegalIdException extends RuntimeException {
    public IllegalIdException(String message) {
        super(message);
    }
}
