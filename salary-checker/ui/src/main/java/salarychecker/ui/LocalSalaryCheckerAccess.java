package salarychecker.ui;

import java.util.List;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.User;

public class LocalSalaryCheckerAccess implements SalaryCheckerAccess {

    @Override
    public Accounts readAccounts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User readUser(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AbstractUser> readAccountsWithSameEmployer(String employerEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void userLogin(String email, String password) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void registerNewAccounts(Accounts accounts) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createUser(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateUserAttributes(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAccounts() {
        // TODO Auto-generated method stub
        
    }

}
