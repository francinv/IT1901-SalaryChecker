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

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.restserver.exceptions.UserAlreadyExistsException;
import salarychecker.restserver.exceptions.UserNotFoundException;
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
  
  //localhost:8080//salarychecker/users?employerEmail={employerEmail}
  @PostMapping(path = "login")
  public AbstractUser userLogin(String email, String password) {
    if (salaryCheckerService.userLogin(email, password)) {
      return salaryCheckerService.getUserByEmail(email);
    }
    throw new UserNotFoundException();
  }

  @PostMapping
  public void registerNewAccounts(@RequestBody Accounts accounts) {
    salaryCheckerService.setAccounts(accounts);
  }

  @PostMapping(path = "create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createUser(@RequestBody User user) {
    try {
      salaryCheckerService.createUser(user);
    } catch(Exception e) {
      throw new UserAlreadyExistsException();
    }
  }

  @PostMapping(path = "create-user/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createAdminUser(@RequestBody AdminUser newUser) {
    try {
      salaryCheckerService.createAdminUser(newUser);
    } catch(Exception e) {
      throw new UserAlreadyExistsException();
    }
  }
  
  @PutMapping(path = "user/calculate-sale", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void calculateUsersUserSale(@RequestBody Calculation calculation) {
      //salaryCheckerService.calculateUsersUserSale(calculation.getURL(), hours, mobileAmount, salesPeriod, paid);
  }

  /**
   * Performs a PUT request
   * localhost:8080//salarychecker/users/update-profile?index={indexOfUser}
  */
  @PutMapping(path = "user/update-profile", 
    consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUserAttributes(@RequestBody User user, 
      @RequestParam("index") int indexOfUser) {
    salaryCheckerService.updateUserAttributes(user, indexOfUser);
  }

  @DeleteMapping
  public void deleteAccounts() {
    salaryCheckerService.setAccounts(null);
  }
}  
