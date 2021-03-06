package salarychecker.core;

import java.util.ArrayList;

/**
 * Class for creating a user, and store their information.
 * This class inheritance from {@link AbstractUser}
 */
public class User extends AbstractUser {

  private String socialNumber;
  private int employeeNumber;
  private String employerEmail;
  private double taxCount;
  private double hourRate;

  private final ArrayList<UserSale> userSales = new ArrayList<>();

  /**
   * Constructor that is used to create a instance of this class.
   *
   *  @param firstname users firstname
   *  @param lastname users lastname
   *  @param email users email
   *  @param password users password
   *  @param socialNumber users socialNumber
   *  @param employeeNumber users employeeNumber
   *  @param employerEmail users employers email
   *  @param taxCount users tax count
   *  @param hourRate users hour rate
   */
  public User(String firstname, String lastname, String email, String password, String socialNumber,
              int employeeNumber, String employerEmail, double taxCount, double hourRate) {
    super.firstname = firstname;
    super.lastname = lastname;
    super.email = email;
    super.password = password;
    this.socialNumber = socialNumber;
    this.employeeNumber = employeeNumber;
    this.employerEmail = employerEmail;
    this.taxCount = taxCount;
    this.hourRate = hourRate;
  }

  /**
   * Empty constructor to use in json deserializer.
   */
  public User() {
  }

  /**
   * Super constructor.
   *
   * @param firstname first name
   * @param lastname last name
   * @param email e-mail
   * @param employeeNumber employee number
   */

  public User(String firstname, String lastname, String email, int employeeNumber) {
    super.firstname = firstname;
    super.lastname = lastname;
    super.email = email;
    this.employeeNumber = employeeNumber;
  }

  /**
   * Access method for social number.
   *
   * @return the social number
   */
  public String getSocialNumber() {
    return socialNumber;
  }

  /**
   * Mutation method for new socialNumber.
   *
   * @param socialNumber the socialNumber to set
   */
  public void setSocialNumber(String socialNumber) {
    UserValidation.checkValidSocialNumber(socialNumber);
    this.socialNumber = socialNumber;
  }

  /**
   * Access method for employee number.
   *
   * @return the employee number
   */
  public int getEmployeeNumber() {
    return employeeNumber;
  }

  /**
   * Mutation method for new employee number.
   *
   * @param employeeNumber the employee number to set
   */
  public void setEmployeeNumber(int employeeNumber) {
    UserValidation.checkValidEmployeeNumber(employeeNumber);
    this.employeeNumber = employeeNumber;
    for (UserObserver userObserver : userObs) {
      userObserver.userInfoChanged(this);
    }
  }

  /**
   * Access method for employer email.
   *
   * @return the employer email
   */
  public String getEmployerEmail() {
    return employerEmail;
  }

  /**
   * Mutation method for new employer email.
   *
   * @param employerEmail the employer email to set
   */
  public void setEmployerEmail(String employerEmail) {
    UserValidation.checkValidEmail(employerEmail);
    this.employerEmail = employerEmail;
    for (UserObserver userObserver : userObs) {
      userObserver.userInfoChanged(this);
    }
  }

  /**
   * Access method for employer email.
   *
   * @return the employer email
   */
  public double getTaxCount() {
    return taxCount;
  }

  /**
   * Mutation method for new tax count.
   *
   * @param taxCount the tax count to set
   */
  public void setTaxCount(double taxCount) {
    UserValidation.checkValidTaxCount(taxCount);
    this.taxCount = taxCount;
    for (UserObserver userObserver : userObs) {
      userObserver.userInfoChanged(this);
    }
  }

  /**
   * Access method for hour rate.
   *
   * @return the employer hour rate
   */
  public double getHourRate() {
    return hourRate;
  }

  /**
   * Mutation method for new hour rate.
   *
   * @param hourRate the hour rate to set
   */
  public void setHourRate(double hourRate) {
    UserValidation.checkValidHourRate(hourRate);
    this.hourRate = hourRate;
    for (UserObserver userObserver : userObs) {
      userObserver.userInfoChanged(this);
    }
  }

  /**
   * Access method for user sale list.
   *
   * @return a copy of user sale list
   */
  public ArrayList<UserSale> getUserSaleList() {
    return new ArrayList<>(userSales);
  }

  /**
   * Checks if the userSale exists.
   *
   * @param userSale the user sale to check
   * @return boolean
   */
  public boolean isExistingUserSale(UserSale userSale) {
    for (UserSale u : userSales) {
      return u.getSalesperiod().equals(userSale.getSalesperiod());
    }
    return false;
  }

  /**
   * Adds user sale to userSales.
   *
   * @param userSale to be added
   */
  public void addUserSale(UserSale userSale) {
    if (!(isExistingUserSale(userSale))) {
      userSales.add(userSale);
      for (UserObserver userObserver : userObs) {
        userObserver.userInfoChanged(this);
      }
    }
  }

  public UserSale getUserSale(String salesperiod) {
    return getUserSaleList().stream().filter(s -> 
      s.getSalesperiod().equals(salesperiod)).findAny().orElse(null);
  }


}
