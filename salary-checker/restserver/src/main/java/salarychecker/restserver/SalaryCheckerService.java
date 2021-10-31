package salarychecker.restserver;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;

import java.util.List;

public class SalaryCheckerService {

    private final Accounts accounts = new Accounts();
 
    public List<AbstractUser> getAccounts() {
        return accounts.getAccounts();
    }

}