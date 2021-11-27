package salarychecker.core;

/**
 * Interface for user observers.
 */
public interface IUserObserver {

  void userInfoChanged(AbstractUser user);
}