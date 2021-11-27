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

  protected final List<AbstractUser> accounts = new ArrayList<>();

  /**
   * Access method for accounts.
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
      throw new IllegalStateException("User already exists!");
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
   * Iterator to easilly move between objects in list.
   *
   * @return iterator of accounts
   */
  public Iterator<AbstractUser> iterator() {
    return accounts.iterator();
  }

  /**
   * Finds the index of the user in list.
   *
   * @param user to find index for
   */
  public int indexOf(AbstractUser user) {
    return accounts.indexOf(user);
  }

  /**
   * Checks if the given user exists in the list of users.
   *
   * @param user to check
   * @return true or false based on if the user exists
   */
  public boolean contains(AbstractUser user) {
    return accounts.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()));
  }

  /**
   * Checks if the user login is valid.
   *
   * @param email email to check
   * @param password password to check
   * @return boolean
   */
  public boolean checkValidUserLogin(String email, String password) {
    AbstractUser user = null;

    for (AbstractUser ab : accounts) {
      if (ab.getEmail().equals(email)) {
        user = ab;
      }
    }
    return user != null && user.getPassword().equals(password);
  }

  /**
   * Gets the user by email and password.
   *
   * @param email the email
   * @param password the password
   * @return the user
   */
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
  /**
   * get accounts by email.
   * 
   * @param email the email
   * @return accounts if they exist, null else
   */

  public AbstractUser getUser(String email) {
    return getAccounts().stream().filter(u -> u.getEmail().equals(email))
                                 .findAny()
                                 .orElse(null);
  }
  /**
   * gets accouns by employerEmail.
   * 
   * @param employerEmail employers email
   * @return users with the same employer.
   */
  
  public List<AbstractUser> getUsersByEmployerEmail(String employerEmail) {
    List<AbstractUser> usersWithSameEmployer = new ArrayList<>();
    for (AbstractUser abstractUser : accounts) {
      if (abstractUser instanceof User) {
        if (((User)abstractUser).getEmployerEmail().equals(employerEmail)) {
          usersWithSameEmployer.add(abstractUser);
        }
      }
    }
    return usersWithSameEmployer;
  }


  /**
   * This method is used when something is changed.
   * The accounts object will be updated accordingly.
   * 
   * @param user        to change
   * @param indexOfUser in list
   */
  public void updateUserObject(AbstractUser user, int indexOfUser) {
    accounts.set(indexOfUser, user);
  }

  /**
   * This method is used to inform the observers.
   * This method is called when anything is changed.
   *
   * @param user the user
   * @param changeddouble the changed double
   */
  public void userInfoChanged(AbstractUser user) {
    int index = accounts.indexOf(user);
    this.updateUserObject(user, index);
  }
  
}