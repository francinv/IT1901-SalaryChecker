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

  private final List<AbstractUser> accounts = new ArrayList<>();

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

  public AbstractUser getUser(String email) {
    return getAccounts().stream().filter(u -> u.getEmail().equals(email))
                                 .findAny()
                                 .orElse(null);
  }

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
   * Updates password of a specific user.
   *
   * @param user the user
   * @param password new password
   */
  public void updatePassword(User user, String password) {
    accounts.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                     .findAny()
                     .ifPresent(u -> u.setPassword(password));
  }

  /**
   * Updates email of a specific user.
   *
   * @param user the user
   * @param email new email
   */
  public void updateEmail(User user, String email) {
    accounts.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                     .findAny()
                     .ifPresent(u -> u.setEmail(email));
  }

  /**
   * Updates firstname of a specific user.
   *
   * @param user the user
   * @param firstname the firstname
   */
  public void updateFirstname(User user, String firstname) {
    accounts.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                     .findAny()
                     .ifPresent(u -> u.setFirstname(firstname));
  }

  /**
   * Updates lastname of a specific user.
   *
   * @param user the user
   * @param lastname new lastname
   */
  public void updateLastname(User user, String lastname) {
    accounts.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                     .findAny()
                     .ifPresent(u -> u.setLastname(lastname));
  }

  /**
   * Updates employer email of a specific user.
   *
   * @param user the user
   * @param employermail the employermail
   */
  public void updateEmployerEmail(User user, String employermail) {
    for (AbstractUser u : accounts) {
      if (u.getEmail().equals(user.getEmail())) {
        user = (User) u;
      }
    }
    user.setEmployerEmail(employermail);
  }

  /**
   * Updates houre rate of a specific user.
   *
   * @param user the user
   * @param hoursal new hoursal
   */
  public void updateHourSal(User user, Double hoursal) {
    for (AbstractUser u : accounts) {
      if (u.getEmail().equals(user.getEmail())) {
        user = (User) u;
      }
    }
    user.setHourRate(hoursal);
  }

  /**
   * Updates tax count of a specific user.
   *
   * @param user the user
   * @param taxcount new tax count
   */
  public void updateTaxCount(User user, Double taxcount) {
    for (AbstractUser u : accounts) {
      if (u.getEmail().equals(user.getEmail())) {
        user = (User) u;
      }
    }
    user.setHourRate(taxcount);
  }

  /**
   * Updates employee number of a specific user.
   *
   * @param user the user
   * @param employeenumber new employee number
   */
  public void updateEmployeeNumber(User user, int employeenumber) {
    for (AbstractUser u : accounts) {
      if (u.getEmail().equals(user.getEmail())) {
        user = (User) u;
      }
    }
    user.setHourRate(employeenumber);
  }

  /**
   * Adds usersale to the user.
   *
   * @param user the user
   * @param usale the user sale
   */
  public void addUserSale(User user, UserSale usale) {
    accounts.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                     .findAny()
                     .ifPresent(u -> ((User) u).addUserSale(usale));
  }

  /**
   * This method is used to inform the observers.
   *
   * @param user the user
   * @param userSale the usersale
   */
  public void usersaleAdded(User user, UserSale userSale) {
    this.addUserSale(user, userSale);
  }

  /**
   * This method is used to inform the observers.
   * The method checks wether the double is a tax count or hour rate,
   * and than calls the method meant for each of the possibilities.
   *
   * @param user the user
   * @param changeddouble the changed double
   */
  public void userInfoDoubleChanged(User user, Double changeddouble) {
    if (changeddouble.equals(user.getTaxCount())) {
      updateTaxCount(user, changeddouble);
    } else if (changeddouble.equals(user.getHourRate())) {
      updateHourSal(user, changeddouble);
    }
  }

  /**
   * This method is used to inform the observers.
   *
   * @param user the user
   * @param changedTaxCount the changed tax count
   */
  public void userInfoTaxCountChanged(User user, Integer changedTaxCount) {
    if (changedTaxCount.equals(user.getEmployeeNumber())) {
      updateEmployeeNumber(user, changedTaxCount);
    }
  }

  /**
   * This method is used to inform the observers.
   * The method checks wether the string is a email, employer email,
   * password, firstname, or lastname, and than calls the method
   * meant for each of the possibilities.
   *
   * @param user the user
   * @param changedstring the changed string
   */
  public void userInfoStringChanged(User user, String changedstring) {
    if (changedstring.equals(user.getEmail())) {
      updateEmail(user, changedstring);
    } else if (changedstring.equals(user.getEmployerEmail())) {
      updateEmployerEmail(user, changedstring);
    } else if (changedstring.equals(user.getPassword())) {
      updatePassword(user, changedstring);
    } else if (changedstring.equals(user.getFirstname())) {
      updateFirstname(user, changedstring);
    } else if (changedstring.equals(user.getLastname())) {
      updateLastname(user, changedstring);
    }
  }

  @Override
  public String toString() {
    return "{ accounts='" + getAccounts() + "'}";
  }
}
