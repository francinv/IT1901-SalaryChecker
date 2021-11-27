package salarychecker.core;

/**
 * Interface for user observers.
 */
public interface UserObserver {

  void userInfoChanged(AbstractUser user);
}