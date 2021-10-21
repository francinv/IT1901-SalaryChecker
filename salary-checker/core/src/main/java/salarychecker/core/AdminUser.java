package salarychecker.core;
/**
 * This class makes it possible to use the app with admin privileges 
 */
public class AdminUser extends AbstractUser {

    private Accounts accounts;

    /**
     * Constructor
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     */
    public AdminUser(String firstname, String lastname, String email, String password) {
        super.firstname = firstname;
        super.lastname = lastname;
        super.email = email;
        super.password = password;
    }

    /**
     * Empty constructor to use in json deserializer.
     */
    public AdminUser() {}

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }


    public void createUser(String firstname, String lastname, String email, String password, String socialNumber,
                           int employeeNumber, String employerEmail, double taxCount, double timesats) {
        UserValidation userValidation = new UserValidation();
        userValidation.checkValidUser(firstname, lastname, email, password, socialNumber, employeeNumber, employerEmail, taxCount, timesats);
        User user = new User(firstname, lastname, email, password, socialNumber, employeeNumber, employerEmail, taxCount, timesats);
        accounts.addUser(user);
    }

    

}