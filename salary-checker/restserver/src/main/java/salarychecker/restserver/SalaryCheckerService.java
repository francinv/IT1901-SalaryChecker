package salarychecker.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;


/**
 * This class is used by the controller to manage Tracker-objects that are sent or requested
 * by the client.
 */
@Service
public class SalaryCheckerService {

  private Accounts accounts;
  private SalaryCheckerPersistence salaryCheckerPersistence;

  /**
   * Constructor for SalaryCheckerService.
   *
   * @param accounts takes in an Accounts object
   */
  public SalaryCheckerService(Accounts accounts) {
    this.accounts = accounts;
    this.salaryCheckerPersistence = new SalaryCheckerPersistence();
    salaryCheckerPersistence.setFilePath("springbootserver-salarychecker.json");
  }

  /**
  * This constructor calls the other, while adding a default configuration for Accounts.
  */
  public SalaryCheckerService() {
    this(createDeafaultAccounts());
  } 

  public List<AbstractUser> getAccounts() {
    return accounts.getAccounts();
  } 

  public void setAccounts(Accounts accounts) {
    this.accounts = accounts;
  } 

  /**
   * Creates default users from json-file.
   * 
   * @return creates two test users if json file is not found.
   */
  public static Accounts createDeafaultAccounts() {
    SalaryCheckerPersistence salaryCheckerPersistence = new SalaryCheckerPersistence();
    URL url = SalaryCheckerService.class.getResource("default-accounts.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return salaryCheckerPersistence.readAccounts(reader);
      } catch (IOException e) {
        System.out.println("Could not read default-salarychecker.json." 
                            + "\n Rigging Accounts manually ("
                            + e + ")");
      }
    }
    return manuallyCreateAccounts();
  }

  /**
   * Method that creates two test users.
   */
  private static Accounts manuallyCreateAccounts() {
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no",
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
    AdminUser testuser2 = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com",
        "Vandre333!");

    Accounts acc = new Accounts();
    acc.addUser(testuser1);
    acc.addUser(testuser2);
    return acc;
  }

  /**
   * Method that saves accounts automatically.
   */
  public void autoSaveAccounts() {
    if (salaryCheckerPersistence != null) {
      try {
        salaryCheckerPersistence.saveAccounts(accounts);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Couldn't auto-save Accounts: " + e);
      }
    }
  }

  /**
   * Find user by email
   * 
   * @param email to get user by this email
   * @return a abstractUser object
   */
  public AbstractUser getUserByEmail(String email) {
    return accounts.getUser(email);
  }

  /**
   * Get all users with same employer
   * 
   * @param employerEmail
   * @return a list with AbstractUser objects
   */
  public List<AbstractUser> getUsersByEmployerEmail(String employerEmail) {
    return accounts.getUsersByEmployerEmail(employerEmail);
  }

  public UserSale calculateUsersUserSale(User user, String hours, String mobileamount, String url) 
    throws NumberFormatException, IOException {
    Calculation calculation = new Calculation(user);
    calculation.doCalculation(url, Double.parseDouble(hours), Integer.parseInt(mobileamount));
    return new UserSale();
  }

  /**
   * Method to create a user object.
   * Adds the user object to accounts.
   *
   * @return the user object
   */
  public User createUser() {
    User user = new User();
    accounts.addUser(user);
    return user;
  }

  /**
   * Method to check if the user exists.
   *
   * @param email email
   * @param password password
   * @return true if exists
   */
  public boolean userLogin(String email, String password) {
    return accounts.checkValidUserLogin(email, password);
  }
}