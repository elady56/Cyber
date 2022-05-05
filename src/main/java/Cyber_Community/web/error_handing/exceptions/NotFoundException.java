package Cyber_Community.web.error_handing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a {@code NotFoundException} with no detail message.
     */
    public NotFoundException() {
    }

    /**
     * Constructs a {@code NotFoundException} with the specified detail message.
     *
     * @param message the detailed message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
