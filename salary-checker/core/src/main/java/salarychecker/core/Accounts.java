package salarychecker.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lists of items in a todo list.
 */
public class Accounts implements Iterable<User> {

    public List<User> accounts = new ArrayList<>();

    public List<User> getAccounts() {
        return accounts;
    }

    /**
     * Adds new User to this list of Users.
     *
     * @param user the user to add
     * @throws IllegalStateException if the user already exists.
     */
    public void addUser(User user) {
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
    public void removeUser(User user) {
        if (!contains(user)) {
            throw new IllegalArgumentException("User does not exists!");
        }
        this.accounts.remove(user);
    }

    @Override
    public Iterator<User> iterator() {
        return accounts.iterator();
    }

    /**
     * Finds the index of the user in list
     * @param user to find index for
     */
    public int indexOf(User user) {
        return accounts.indexOf(user);
    }

    public boolean contains(User user) {
        return accounts.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    // @Override
    // public String toString() {
    //     StringBuilder ...
    //     for (User user : accounts) {
            
    //     }
    //     return String.format("[User \n%s %s \ne-mail: %s]",
    //         get,);
    // }

    public boolean checkValidUserLogin(String email, String password) {
        return accounts.stream().anyMatch(u -> u.getEmail().equals(email) && u.getPassword().equals(password));
    }

    public User getUser(String email, String password) {
        return accounts.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findAny()
                .orElse(null);
    }

    public void updatePassword(String email, String newpassword) {
        accounts.stream().filter(u->u.getEmail().equals(email)).findAny().ifPresent(u->u.setPassword(newpassword));
    }

}

