package salarychecker.core;

public interface IUserObserver {
    public void usersaleAdded(User user, UserSale uSale);
    public void userInfoStringChanged(User user, String changedstring);
    public void userInfoDoubleChanged(User user, Double changeddouble);
    public void userInfoIntChanged(User user, Integer changedint);



}
