package salarychecker.restserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
  private SalaryCheckerPersistence salaryCheckerPersistence;
  private Path fileStorageLocation;

  /**
   * Constructor for SalaryCheckerService.
   *
   * @param accounts takes in an Accounts object
   */
  public SalaryCheckerService(Accounts accounts) {
    this.accounts = accounts;
    this.calculation = new Calculation();
    this.salaryCheckerPersistence = new SalaryCheckerPersistence();
    salaryCheckerPersistence.setFilePath("springbootserver-salarychecker.json");
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


  public void setFileStorage(FileStorageProperties fileStorageProperties) {
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
        .toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.fileStorageLocation);
    }
    catch (Exception e) {
      throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
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
    SalaryCheckerPersistence salaryCheckerPersistence = new SalaryCheckerPersistence();
    try {
      Accounts accounts = salaryCheckerPersistence.loadAccounts();
      if (accounts != null) {
        return accounts;
      }
    } catch (IllegalStateException | IOException e) {
      System.out.println("Could not read json file." 
                            + "\n Rigging Accounts manually ("
                            + e + ")");
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

  public void calculateUsersUserSale(Calculation calculation, String emailOfUser)
      throws NumberFormatException, IOException {
    User user = (User) accounts.getUser(emailOfUser);
    int index = accounts.indexOf(user);
    String url = "/Library/SalaryChecker/SalesReport.csv";
    calculation.doCalculation(url, user);
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
   * Saves Accounts to disk
   */
  private void autoSave() {
    if (salaryCheckerPersistence != null) {
      try {
        salaryCheckerPersistence.saveAccounts(accounts);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Could not auto-save Accounts: " + e);
      }
    }
  }

  public UserSale getUserSale(String salesperiod, String emailOfUser) {
    User user = (User) getUserByEmail(emailOfUser);
    return user.getUserSale(salesperiod);
  }

  public String storeFile(MultipartFile file) {
    // Normalize file name
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      // Check if the file's name contains invalid characters
      if(fileName.contains("..")) {
        throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
      }

      // Copy file to the target location (Replacing existing file with the same name)
      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
  }

}
