package salarychecker.ui;

import java.io.IOException;
import java.util.List;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

/**
 * This implementation of {@link SalaryCheckerAccess} is meant for 
 * using the app without the server running. 
 */
public class LocalSalaryCheckerAccess implements SalaryCheckerAccess {

    private Accounts accounts = new Accounts();
    private final SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

    public LocalSalaryCheckerAccess() {
        persistence.setFilePath("Accounts.json");
    }

    @Override
    public Accounts readAccounts() throws IOException {
        return persistence.loadAccounts();
    }

    @Override
    public User readUser(String email) {
        if (accounts.getUser(email) instanceof User) {
            return (User) accounts.getUser(email);
        }
        return  null;
    }

    @Override
    public List<AbstractUser> readAccountsWithSameEmployer(String employerEmail) {
        return accounts.getUsersByEmployerEmail(employerEmail);
    }

    @Override
    public AbstractUser userLogin(String email, String password) {
        return accounts.getUser(email, password);
    }

    @Override
    public void registerNewAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    @Override
    public void createUser(AbstractUser user) {
        if (user != null) {
            accounts.addUser(user);
        }
        try {
            persistence.saveAccounts(accounts);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserAttributes(AbstractUser user, int indexOfUser) {
        accounts.updateUserObject(user, indexOfUser);
    }

    @Override
    public void deleteAccounts() {
        accounts = null;
    }

}
