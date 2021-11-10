package salarychecker.restserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
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
  
  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public List<AbstractUser> getAccounts() {
    return salaryCheckerService.getAccounts();
  }  

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path = "user")
  public AbstractUser getUser(@RequestParam("email") String email) {
    return salaryCheckerService.getUserByEmail(email);
  } 

  @GetMapping(path = "users")
  public List<AbstractUser> getEmployersUser(@RequestParam("employerEmail") String employerEmail) {
    return salaryCheckerService.getUsersByEmployerEmail(employerEmail);
  } 

  @PostMapping
  public void registerNewAccounts(@RequestBody Accounts accounts) {
    salaryCheckerService.setAccounts(accounts);
  } 

  @DeleteMapping
  public void deleteAccounts() {
    salaryCheckerService.setAccounts(null);
  }
}  
