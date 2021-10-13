package salarychecker.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Lists of items in a todo list.
 */
public class Accounts implements Iterable<AbstractUser> {

    public List<AbstractUser> accounts = new ArrayList<>();

    public List<AbstractUser> getAccounts() {
        return accounts;
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

    @Override
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

    EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

    public boolean checkValidUserLogin(String email, String password) {
        AbstractUser user = null;
        String passwordDecrypted = null;

        for (AbstractUser ab : accounts) {
            if (ab.getEmail().equals(email)) {
                user = ab;
            }
        }
        try {
            passwordDecrypted = encryptDecrypt.decrypt(user.getPassword(), user.getFirstname());
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return accounts.stream().anyMatch(u -> u.getEmail().equals(email) && u.getPassword().equals(password));
        return passwordDecrypted.equals(password);
    }

    public AbstractUser getUser(String email, String password) {
        // return accounts.stream()
        //                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
        //                .findAny()
        //                .orElse(null);
        AbstractUser user = null;
        String passwordDecrypted = null;

        for (AbstractUser ab : accounts) {
            if (ab.getEmail().equals(email)) {
                user = ab;
            }
        }
        try {
            passwordDecrypted = encryptDecrypt.decrypt(user.getPassword(), user.getFirstname());
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (passwordDecrypted.equals(password)) {
            return user;
        }
        return null;
    }

    public void updatePassword(String email, String newpassword) {
        accounts.stream().filter(u->u.getEmail().equals(email)).findAny().ifPresent(u->u.setPassword(newpassword));
    }

}

