package salarychecker.core;

/**
 * Interface for user observers.
 */
public interface UserObserver {

  void usersaleAdded(User user, UserSale userSale);

  void userInfoStringChanged(User user, String changedstring);

  void userInfoDoubleChanged(User user, Double changeddouble);

  void userInfoTaxCountChanged(User user, Integer changedTaxCount);
}