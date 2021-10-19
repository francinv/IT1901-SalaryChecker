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
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setSocialNumber(socialNumber);
        user.setEmployeeNumber(employeeNumber);
        user.setEmployerEmail(employerEmail);
        user.setTaxCount(taxCount);
        user.setTimesats(timesats);
        accounts.addUser(user);
    }

    

}