package salarychecker.restserver;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.restserver.exceptions.UserDoesNotExistException;
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
  public List<AbstractUser> getAccounts() {
    return salaryCheckerService.getAccounts();
  }  

  //localhost:8080//salarychecker/users?email={email}
  @GetMapping(path = "user")
  public AbstractUser getUser(@RequestParam("email") String email) {
    if (salaryCheckerService.getUserByEmail(email) == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    }
    return salaryCheckerService.getUserByEmail(email);
  } 

  //localhost:8080//salarychecker/users?employerEmail={employerEmail}
  @GetMapping(path = "users")
  public List<AbstractUser> getEmployersUser(@RequestParam("employerEmail") String employerEmail)
      throws UserDoesNotExistException {
      return salaryCheckerService.getUsersByEmployerEmail(employerEmail);
  } 

  @PostMapping
  public void registerNewAccounts(@RequestBody Accounts accounts) {
    salaryCheckerService.setAccounts(accounts);
  }

  @GetMapping("create-user")
  public User createUser() {
    return salaryCheckerService.createUser();
  }
  
  @PutMapping(path = "user/calculate-sale")
  public void calculateUsersUserSale(@RequestBody User user, @RequestParam("hours") String hours, 
      @RequestParam("mobileamount") String mobileAmount, @RequestParam("url") String url) {

    try {
      salaryCheckerService.calculateUsersUserSale(user, hours, mobileAmount, url);
    } catch (NumberFormatException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @DeleteMapping
  public void deleteAccounts() {
    salaryCheckerService.setAccounts(null);
  }
}  
