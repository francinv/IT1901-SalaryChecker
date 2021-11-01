package salarychecker.restserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salarychecker.core.AbstractUser;
/**
 * Ensures that the server is capable of listening to HTTP-requests.
 * Decides how these requests are managed and what to do with them.
 */
@RestController
@RequestMapping(SalaryCheckerController.SALARY_CHECKER_SERVICE_PATH)
public class SalaryCheckerController {
    
    public static final String SALARY_CHECKER_SERVICE_PATH = "api/v1/SalaryChecker";

    private final SalaryCheckerService salaryCheckerService;

    @Autowired
    public SalaryCheckerController(final SalaryCheckerService salaryCheckerService) {
        this.salaryCheckerService = salaryCheckerService;
    }

    @GetMapping
    public List<AbstractUser> getAccounts() {
        return salaryCheckerService.getAccounts();
    }
}
