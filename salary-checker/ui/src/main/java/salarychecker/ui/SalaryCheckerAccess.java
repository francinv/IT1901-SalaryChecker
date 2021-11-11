package salarychecker.ui;

import java.util.List;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.User;

/**
 * Interface for classes that wants to take advantage of a REST APi.
 */
public interface SalaryCheckerAccess {
    
    /**
     * Gets all the accounts registred to use Salary Checker.
     * 
     * @return all the users
     */
    public Accounts getAccounts();

    /**
     * Get a specific user by email.
     * 
     * @param email of the user to get
     * @return the user
     */
    public User getUser(String email);

    /**
     * Finds all the users with same employer. 
     *
     * @param employerEmail
     * @return list of all the users with same employer
     */
    public List<AbstractUser> getEmployersUser(String employerEmail);

    /**
     * Method to log in as a regular user.
     * 
     * @param email of the user
     * @param password of the user
     */
    public void userLogin(String email, String password);

    /**
     * This is meant for set the users that can use Salary Checker.
     */
    public void registerNewAccounts();

    /**
     * Creates a new user and adds it to Accounts.
     */
    public void createUser();

    /**
     * Updates thes atrributes of a specific user.
     */
    public void updateUserAttributes();

    /**
     * This method is to remove the Accounts object currently used by
     * Salary Checker.
     */
    public void deleteAccounts();
}
