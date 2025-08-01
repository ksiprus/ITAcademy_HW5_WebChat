package ksiprus.service.exception;

public class UserServiceException extends Exception {
  public UserServiceException(String message) {
    super(message);
  }
  public UserServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
