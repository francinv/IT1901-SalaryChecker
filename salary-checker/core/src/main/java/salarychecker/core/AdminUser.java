package salarychecker.core;

/**
 * This class makes it possible to use the app with admin privileges.
 */
public class AdminUser extends AbstractUser {

  private Accounts accounts;

  /**
   * Constructor that is used to create a instance of this class.
   *
   * @param firstname users firstname
   * @param lastname users lastname
   * @param email users email
   * @param password users password
   */
  public AdminUser(String firstname, String lastname, String email, String password) {
    super.firstname = firstname;
    super.lastname = lastname;
    super.email = email;
    super.password = password;
  }

  /**
   * Empty constructor to use in json deserializer.
   */
  public AdminUser() {
  }

  /**
   * Mutation method for new accounts.
   *
   * @param accounts the accounts to set
   */
  public void setAccounts(Accounts accounts) {
    this.accounts = accounts;
  }

  /**
   * This method gives admin user the possibility to create a regular user.
   *
   * @param firstname regular users firstname
   * @param lastname regular users lastname
   * @param email regular users email
   * @param password regular users password
   * @param socialNumber regular users socialNumber
   * @param employeeNumber regular users employeeNumber
   * @param employerEmail regular users employerEmail
   * @param taxCount regular users taxCount
   * @param hourRate regular users hourRate
   */
  public void createUser(String firstname, String lastname, String email, String password,
                         String socialNumber, int employeeNumber, String employerEmail,
                         double taxCount, double hourRate) {
    UserValidation userValidation = new UserValidation();
    userValidation.checkValidUser(firstname, lastname, email, password, socialNumber,
        employeeNumber, employerEmail, taxCount, hourRate);
    User user = new User(firstname, lastname, email, password, socialNumber,
        employeeNumber, employerEmail, taxCount, hourRate);
    createUser(user);
  }
  /**
   * Method for adding a user.
   * 
   * @param user the user
   */
  
  public void createUser(User user) {
    if (user != null) {
      accounts.addUser(user);
    }
  }
}
