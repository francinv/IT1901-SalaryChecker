package salarychecker.restserver.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ApiError {

    private final String message;
    private final int status;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

    public ApiError(String message, int status, HttpStatus httpStatus, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}