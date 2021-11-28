package salarychecker.restserver.exceptions;

/**
 * Exception for file storage.
 */
public class FileStorageException extends  RuntimeException {
  public FileStorageException(String message) {
    super(message);
  }

  public FileStorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
