package salarychecker.restserver;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
import salarychecker.core.*;
import salarychecker.restserver.exceptions.UserAlreadyExistsException;
import salarychecker.restserver.exceptions.UserNotFoundException;
import salarychecker.restserver.payload.UploadFileResponse;

/**
 * Ensures that the server is capable of listening to HTTP-requests.
 * Decides how these requests are managed and what to do with them.
 */

@RestController
@RequestMapping(SalaryCheckerController.SALARY_CHECKER_SERVICE_PATH)
public class SalaryCheckerController {
    
  public static final String SALARY_CHECKER_SERVICE_PATH = "salarychecker";  
  private final SalaryCheckerService salaryCheckerService;  
  
  @Autowired
  public SalaryCheckerController(final SalaryCheckerService salaryCheckerService) {
    this.salaryCheckerService = salaryCheckerService;
  }  
  
  @GetMapping
  public Accounts getAccounts() {
    return salaryCheckerService.getAccounts();
  }  
  /**
   * Gets user by email if it exists.
   * @param email the email
   * @return user, if found
   */

  //localhost:8080//salarychecker/users?email={email}
  @GetMapping(path = "user")
  public AbstractUser getUser(@RequestParam("email") String email) {
    if (salaryCheckerService.getUserByEmail(email) == null) {
      throw new UserNotFoundException();
    }
    return salaryCheckerService.getUserByEmail(email);
  } 

  //localhost:8080//salarychecker/users?employerEmail={employerEmail}
  @GetMapping(path = "users")
  public List<AbstractUser> getEmployersUser(@RequestParam("employerEmail") String employerEmail) {
    return salaryCheckerService.getUsersByEmployerEmail(employerEmail);
  }
  /**
   * Posts the login requests entered from client.
   * 
   * @param email the email
   * @param password the password
   * @return the user if login is correct
   */

  //localhost:8080//salarychecker/login?email={email}&password={password}
  @PostMapping(path = "login")
  public AbstractUser userLogin(@RequestParam("email") String email, 
      @RequestParam("password") String password) {
    if (salaryCheckerService.userLogin(email, password)) {
      return salaryCheckerService.getUserByEmail(email);
    }
    throw new UserNotFoundException();
  }

  @PostMapping
  public void registerNewAccounts(@RequestBody Accounts accounts) {
    salaryCheckerService.setAccounts(accounts);
  }
  /**
   * Allows post requests to create new users, through the API.
   * @param user the user
   */

  @PostMapping(path = "create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createUser(@RequestBody User user) {
    try {
      salaryCheckerService.createUser(user);
    } catch (Exception e) {
      throw new UserAlreadyExistsException();
    }
  }
  /**
   * Method for creating a new admin user.
   * 
   * @param newUser new user
   */

  @PostMapping(path = "create-user/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createAdminUser(@RequestBody AdminUser newUser) {
    try {
      salaryCheckerService.createAdminUser(newUser);
    } catch (Exception e) {
      throw new UserAlreadyExistsException();
    }
  }
  /**
   * Method for calculating the sales of a user by email.
   * @param calculation calculates user sales
   * @param emailOfUser email of the user
   */

  //localhost:8080//salarychecker/user/calculate-sale?email={email}
  @PutMapping(path = "user/calculate-sale", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void calculateUsersUserSale(@RequestBody Calculation calculation, 
      @RequestParam("email") String emailOfUser) {
    try {
      salaryCheckerService.calculateUsersUserSale(calculation, emailOfUser);
    } catch (NumberFormatException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Makes it possible to perform a PUT request to update user.
   * localhost:8080//salarychecker/users/update-profile?index={indexOfUser}
  */

  @PutMapping(path = "user/update-profile", 
        consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUserAttributes(@RequestBody User user, 
      @RequestParam("index") int indexOfUser) {
    salaryCheckerService.updateUserAttributes(user, indexOfUser);
  }
  /**
   * Makes it possible to upload a CSV file.
   * @param file the file
   * @return file attributes
   */
  
  @PostMapping(path = "/uploadFile")
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    String fileName = salaryCheckerService.storeFile(file);

    return new UploadFileResponse(fileName,
        file.getContentType(), file.getSize());
  }

  @GetMapping(path = "user/get-user-sale")
  public UserSale getUserSale(@RequestParam("salesperiod") String salesperiod, @RequestParam("email") String emailOfUser) {
    return salaryCheckerService.getUserSale(salesperiod, emailOfUser);
  }

  @DeleteMapping
  public void deleteAccounts() {
    salaryCheckerService.setAccounts(null);
  }
}  
