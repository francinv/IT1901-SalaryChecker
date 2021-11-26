package salarychecker.core;

/**
 * Interface for user observers.
 */
public interface IUserObserver {

  void usersaleAdded(User user, UserSale userSale);

  void userInfoStringChanged(User user, String changedstring);

  void userInfoDoubleChanged(User user, double changeddouble);

  void userInfoTaxCountChanged(User user, Integer changedTaxCount);
}