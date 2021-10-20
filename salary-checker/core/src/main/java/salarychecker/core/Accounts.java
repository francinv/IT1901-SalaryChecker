package salarychecker.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lists of users.
 */
public class Accounts implements IUserObserver {

    private List<AbstractUser> accounts = new ArrayList<>();

    public List<AbstractUser> getAccounts() {
        return new ArrayList<>(accounts);
    }

    /**
     * Adds new User to this list of Users.
     *
     * @param user the user to add
     * @throws IllegalStateException if the user already exists.
     */
    public void addUser(AbstractUser user) {
        if (contains(user)) {
            throw new IllegalArgumentException("User already exists!");
        }
        this.accounts.add(user);
    }

    /**
     * Removes already existing User.
     *
     * @param user the user to remove
     * @throws IllegalStateException if the user dosen't already exists.
     */
    public void removeUser(AbstractUser user) {
        if (!contains(user)) {
            throw new IllegalArgumentException("User does not exists!");
        }
        this.accounts.remove(user);
    }

    public Iterator<AbstractUser> iterator() {
        return accounts.iterator();
    }

    /**
     * Finds the index of the user in list
     * @param user to find index for
     */
    public int indexOf(AbstractUser user) {
        return accounts.indexOf(user);
    }

    public boolean contains(AbstractUser user) {
        return accounts.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    private EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

    public boolean checkValidUserLogin(String email, String password) {
        AbstractUser user = null;

        for (AbstractUser ab : accounts) {
            if (ab.getEmail().equals(email)) {
                user = ab;
            }
        }
        return user.getPassword().equals(password);
    }


    public AbstractUser getUser(String email, String password) {
        AbstractUser user = null;

        User u = new User();
        AdminUser a = new AdminUser();

        for (AbstractUser ab : accounts) {
            if (ab.getEmail().equals(email)) {
                if (getTypeOfUser(email).equals(u.getClass())){
                    user = (User) ab;
                } 
                if (getTypeOfUser(email).equals(a.getClass())){
                    user = (AdminUser) ab;
                }
                user = ab;
            } 
        }
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Class getTypeOfUser(String email){
        AbstractUser user = null;
        for (AbstractUser ab: accounts){
            if (ab.getEmail().equals(email)){
                user = ab;
            }
        }
        return user.getClass();
    }
    protected void updatePassword(User user, String password) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setPassword(password));
    }

    private void updateEmail(User user, String email) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setEmail(email));
    }

    private void updateFirstname(User user, String firstname) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setFirstname(firstname));
    }

    private void updateLastname(User user, String lastname) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setLastname(lastname));
    }

    private void updateEmployerEmail(User user, String employermail){
        for (AbstractUser u : accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setEmployerEmail(employermail);
    }

    private void updateHourSal(User user, Double hoursal){
        for (AbstractUser u: accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setTimesats(hoursal);
    }

    private void updateTaxCount(User user, Double taxcount){
        for (AbstractUser u: accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setTimesats(taxcount);
    }

    private void updateEmployeeNumber(User user, int employeenumber){
        for (AbstractUser u: accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setTimesats(employeenumber);
    }

    private void addUserSale(User user, UserSale usale){
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->((User) u).addUserSale(usale));
    }

    public void usersaleAdded(User user, UserSale uSale) {
        this.addUserSale(user, uSale);
    }

    public void userInfoDoubleChanged(User user, Double changeddouble) {
        if (changeddouble.equals(user.getTaxCount())){
            updateTaxCount(user, changeddouble);
        }
        else if (changeddouble.equals(user.getTimesats())){
            updateHourSal(user, changeddouble);
        }

    }

    public void userInfoIntChanged(User user, Integer changedint) {
        if(changedint.equals(user.getEmployeeNumber())){
            updateEmployeeNumber(user, changedint);
        }
    }

    public void userInfoStringChanged(User user, String changedstring) {
        if(changedstring.equals(user.getEmail())){
            updateEmail(user, changedstring);
        }
        else if (changedstring.equals(user.getEmployerEmail())){
            updateEmployerEmail(user, changedstring);
        }
        else if (changedstring.equals(user.getPassword())){
            updatePassword(user, changedstring);
        }
        else if (changedstring.equals(user.getFirstname())){
            updateFirstname(user, changedstring);
        }
        else if (changedstring.equals(user.getLastname())) {
            updateLastname(user, changedstring);
        }
    }

    @Override
    public String toString() {
        return "{" +
            " accounts='" + getAccounts() + "'" +
            "}";
    }

    


}

