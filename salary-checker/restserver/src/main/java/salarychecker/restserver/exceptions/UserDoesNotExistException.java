package salarychecker.restserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(String message) {
      super(message);
    }
  
    public UserDoesNotExistException() {
      super("No matching user exists");
    }
}