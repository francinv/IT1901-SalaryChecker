package salarychecker.core;

public interface IUserObserver {

    void usersaleAdded(User user, UserSale uSale);

    void userInfoStringChanged(User user, String changedstring);

    void userInfoDoubleChanged(User user, Double changeddouble);

    void userInfoIntChanged(User user, Integer changedint);
}