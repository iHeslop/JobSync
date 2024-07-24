package jobsync.jobsync.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public <T> NotFoundException(Class<T> entityType, Long id) {
        super(String.format("Could not find %s with id %s", entityType.getSimpleName(), id));
    }

    public static HttpStatus getStatusCode() {
        return statusCode;
    }
}
