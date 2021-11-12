package salarychecker.ui;

import java.io.IOException;
import java.util.List;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;

/**
 * Interface for classes that wants to take advantage of a REST APi.
 */
public interface SalaryCheckerAccess {
    
    /**
     * Gets all the accounts registred to use Salary Checker.
     * 
     * @return all the users
     * @throws IOException
     */
    public Accounts readAccounts() throws IOException;

    /**
     * Get a specific user by email.
     * 
     * @param email of the user to get
     * @return the user
     */
    public User readUser(String email);

    /**
     * Finds all the users with same employer. 
     *
     * @param employerEmail
     * @return list of all the users with same employer
     */
    public List<AbstractUser> readAccountsWithSameEmployer(String employerEmail);

    /**
     * Method to log in as a regular user.
     * 
     * @param email of the user
     * @param password of the user
     * @return the user
     */
    public AbstractUser userLogin(String email, String password);

    /**
     * This is meant for set the users that can use Salary Checker.
     */
    public void registerNewAccounts(Accounts accounts);

    /**
     * Creates a new user and adds it to Accounts.
     * 
     * @param User the user to create
     */
    public void createUser(AbstractUser user);

    /**
     * Creates a new AdminUser and adds it to Accounts.
     * 
     * @param adminUser the user to create
     */
    public void createAdminUser(AdminUser adminUser);

    /**
     * Updates thes atrributes of a specific user.
     * 
     * @param user the user to update
     * @param indexOfUser in Accounts
     */
    public void updateUserAttributes(AbstractUser user, int indexOfUser);

    /**
     * This method is to remove the Accounts object currently used by
     * Salary Checker.
     */
    public void deleteAccounts();
}
