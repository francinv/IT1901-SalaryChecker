package salarychecker.restserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;
import salarychecker.restserver.exceptions.FileStorageException;
import salarychecker.restserver.properties.FileStorageProperties;


/**
 * This class is used by the controller to manage Accounts-objects that are sent or requested
 * by the client.
 */
@Service
public class SalaryCheckerService {

  private Accounts accounts;
  private Calculation calculation;
  private final static SalaryCheckerPersistence PERSISTENCE = new SalaryCheckerPersistence();
  private Path fileStorageLocation;

  /**
   * Constructor for SalaryCheckerService.
   *
   * @param accounts takes in an Accounts object
   */
  public SalaryCheckerService(Accounts accounts) {
    this.accounts = accounts;
    this.calculation = new Calculation();
    PERSISTENCE.setFilePath("springbootserver-salarychecker.json");
  }

  /**
  * This constructor calls the other, while adding a default configuration for Accounts.
  */
  @Autowired
  public SalaryCheckerService(FileStorageProperties fileStorageProperties) {
    this(createDeafaultAccounts());
    setFileStorage(fileStorageProperties);
    autoSave();
  }

  /**
   * sets properties for storage when file is uploaded.
   *
   * @param fileStorageProperties storage path
   */
  public void setFileStorage(FileStorageProperties fileStorageProperties) {
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
        .toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception e) {
      throw new FileStorageException(
        "Could not create the directory where the uploaded files will be stored.", e);
    }

  }


  public Accounts getAccounts() {
    return accounts;
  }

  public void setAccounts(Accounts accounts) {
    this.accounts = accounts;
    autoSave();
  } 

  public Calculation getCalculation() {
    return calculation;
  }

  /**
   * Creates default users from json-file.
   *
   * @return creates two test users if json file is not found.
   */
  public static Accounts createDeafaultAccounts() {
    try {
      Accounts accounts = PERSISTENCE.loadAccounts();
      if (accounts != null) {
        return accounts;
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Could not read json file." 
                            + "\n Manually creating users. ("
                            + e + ")");
    }
    return manuallyCreateAccounts();
  }

  /**
   * Method that creates two test users.
   */
  private static Accounts manuallyCreateAccounts() {
    User user = new User("Test", "User",
        "test@live.no", "Password123!", "22030191349",
        12345, "employeer1@gmail.com", 30.0, 130.0);
    UserSale testsale1 = new UserSale("August 2021", 15643.0, 10000.0);
    user.addUserSale(testsale1);
    UserSale testsale2 = new UserSale("September 2021", 13000.0, 8000.0);
    user.addUserSale(testsale2);
    AdminUser adminUser = new AdminUser("Test", "Admin",
        "test@admin.no", "Password123!");
    Accounts acc = new Accounts();
    acc.addUser(user);
    acc.addUser(adminUser);
    return acc;
  }

  public SalaryCheckerPersistence getPersistence() {
    return PERSISTENCE;
  }

  /**
   * Sets the location of JSON file.
   *
   * @param fileName name of JSON file.
   */
  public void setPersistenceLocation(String fileName) {
    PERSISTENCE.setFilePath(fileName);
  }

  /**
   * Find user by email.
   *
   * @param email to get user by this email
   * @return a abstractUser object
   */
  public AbstractUser getUserByEmail(String email) {
    return accounts.getUser(email);
  }

  /**
   * Get all users with same employer.
   *
   * @param employerEmail employers email
   * @return a list with AbstractUser objects
   */
  public List<AbstractUser> getUsersByEmployerEmail(String employerEmail) {
    return accounts.getUsersByEmployerEmail(employerEmail);
  }
  /**
   * Calculate users UserSale.
   *
   * @param calculation calculation object
   * @param emailOfUser user email
   * @throws NumberFormatException exception for wrong format
   * @throws IOException when not found
   */

  public void calculateUsersUserSale(Calculation calculation, String emailOfUser)
      throws NumberFormatException, IOException {
    User user = (User) accounts.getUser(emailOfUser);
    int index = accounts.indexOf(user);
    Path path = this.fileStorageLocation.resolve("SalesReport.csv");
    calculation.doCalculation(path, user);
    this.updateUserAttributes(user, index);
  }

  /**
   * Method to create a new User. The user to create is given by the client.
   * Adds the user object to accounts.
   */
  public void createUser(User newUser) {
    if (newUser != null) {
      accounts.addUser(newUser);
      autoSave();
    }
  }

  /**
  * Method to create a new AdminUser. The AdminUser to create is given by the client.
  * Adds the AdminUser object to accounts.
  */
  public void createAdminUser(AdminUser newUser) {
    if (newUser != null) {
      accounts.addUser(newUser);
      autoSave();
    }
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

  public void updateUserAttributes(User user, int indexOfUser) {
    accounts.updateUserObject(user, indexOfUser);
    autoSave();
  }

  /**
   * Saves Accounts to disk.
   */
  protected void autoSave() {
      try {
        PERSISTENCE.saveAccounts(accounts);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Could not automatically save the accounts: " + e);
      }
  }

  public UserSale getUserSale(String salesperiod, String emailOfUser) {
    User user = (User) getUserByEmail(emailOfUser);
    return user.getUserSale(salesperiod);
  }
  /**
   * Removes invalid characters from file name.
   *
   * @param file the file
   * @return normalized file name
   */

  public String storeFile(MultipartFile file) {
    // Normalize file name
    String fileName = null;
    if (file != null) {
      fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    }
    if (fileName == null) {
      fileName = "";
    }
    if (fileName != null) {
      try {
        // Check if the file's name contains invalid characters
        if (fileName.contains("..")) {
          throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }


        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException ex) {
        throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
      }
    }
    return fileName;
  }
}
