package salarychecker.restserver.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

/**
 * Exception shown when API error occurs.
 */
public class ApiError {

  private final String message;
  private final int status;
  private final HttpStatus httpStatus;
  private final LocalDateTime timestamp;
  
  /**
   * Api error message including following.
   *
   * @param message a message
   * @param status the status
   * @param httpStatus the http status
   * @param timestamp a timestamp
   */
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