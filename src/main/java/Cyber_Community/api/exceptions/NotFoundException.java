package Cyber_Community.api.exceptions;


public class NotFoundException extends RuntimeException{

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

    /**
     * Constructs a {@code NotFoundException} with the specified cause and detail message.
     *
     * @param message the detail message.
     * @param cause   the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a {@code NotFoundException} with the specified cause and detail message as {@code (cause==null ? null : cause.toString())}.
     *
     * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
