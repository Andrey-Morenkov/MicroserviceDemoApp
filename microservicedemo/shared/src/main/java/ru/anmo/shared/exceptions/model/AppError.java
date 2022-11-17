package java.ru.anmo.shared.exceptions.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppError {
    private int httpStatusCode;
    private String message;

    public AppError(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
