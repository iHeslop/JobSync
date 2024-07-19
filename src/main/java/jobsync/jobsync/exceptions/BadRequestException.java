package jobsync.jobsync.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Exception {
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public static HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

}
