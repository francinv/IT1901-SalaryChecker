package salarychecker.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class which contains a list of all registred Users.
 * This class implements IUserObserver, this is needed to 
 * observe changes in User object
 */
public class Accounts implements IUserObserver {

    private List<AbstractUser> accounts = new ArrayList<>();

    /**
     * Access method for accounts
     * 
     * @return the accounts
     */
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

    /**
     * Iterator to easilly move between objects in list
     * @return iterator of accounts 
     */
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

    /**
     * Checks if the given user exists in the list of users.
     * @param user to check
     * @return true or false based on if the user exists 
     */
    public boolean contains(AbstractUser user) {
        return accounts.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    /**
     * Checks if the user login is valid
     * @param email
     * @param password
     * @return boolean
     */
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

        for (AbstractUser ab : accounts) {
            if (ab.getEmail().equals(email)) {
                user = ab instanceof User ? (User) ab : (AdminUser) ab;
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

    public void updatePassword(User user, String password) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setPassword(password));
    }

    public void updateEmail(User user, String email) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setEmail(email));
    }

    public void updateFirstname(User user, String firstname) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setFirstname(firstname));
    }

    public void updateLastname(User user, String lastname) {
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->u.setLastname(lastname));
    }

    public void updateEmployerEmail(User user, String employermail){
        for (AbstractUser u : accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setEmployerEmail(employermail);
    }

    public void updateHourSal(User user, Double hoursal){
        for (AbstractUser u: accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setHourRate(hoursal);
    }

    public void updateTaxCount(User user, Double taxcount){
        for (AbstractUser u: accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setHourRate(taxcount);
    }

    public void updateEmployeeNumber(User user, int employeenumber){
        for (AbstractUser u: accounts){
            if(u.getEmail().equals(user.getEmail())){
                user = (User) u;
            }
        }
        user.setHourRate(employeenumber);
    }

    public void addUserSale(User user, UserSale usale){
        accounts.stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().ifPresent(u->((User) u).addUserSale(usale));
    }

    public void usersaleAdded(User user, UserSale uSale) {
        this.addUserSale(user, uSale);
    }

    public void userInfoDoubleChanged(User user, Double changeddouble) {
        if (changeddouble.equals(user.getTaxCount())){
            updateTaxCount(user, changeddouble);
        }
        else if (changeddouble.equals(user.getHourRate())){
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