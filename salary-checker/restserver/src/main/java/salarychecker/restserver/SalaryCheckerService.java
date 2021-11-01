package salarychecker.restserver;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;

import java.util.List;

import org.springframework.stereotype.Service;
/**
 * This class is used by the controller to manage Tracker-objects that are sent or requested
 * by the client.
 */
@Service
public class SalaryCheckerService {

    private final Accounts accounts = new Accounts();
 
    public List<AbstractUser> getAccounts() {
        accounts.addUser(new AdminUser("firstname", "lastname", "email@gmail.com", "Vandre33!"));
        return accounts.getAccounts();
    }

}