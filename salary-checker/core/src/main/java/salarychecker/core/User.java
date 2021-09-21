package salarychecker.core;

/** 
 * Class for creating a user, and store their information.
 * 
*/
public class User {
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private long socialNumber;
    private int employeeNumber;
    private String employerEmail;
    private double taxCount;
    private UserValidation validation;

    /**
     * Constructor
     * Not sure if we need all of these parameters.
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @param soscialNumber
     * @param employeeNumber
     * @param employerEmail
     * @param taxCount
     */
    public User(String firstname, String lastname, String email, String password, long socialNumber,
            int employeeNumber, String employerEmail, double taxCount) {
        this.validation = new UserValidation();
        if (!validation.isValidUser(firstname, lastname, email, password, 
            socialNumber, employeeNumber, employerEmail, taxCount)) {
            throw new IllegalArgumentException("Kan ikke opprette bruker");
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.socialNumber = socialNumber;
        this.employeeNumber = employeeNumber;
        this.employerEmail = employerEmail;
        this.taxCount = taxCount;
        
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getSocialNumber() {
        return socialNumber;
    }
    public void setSoscialNumber(long socialNumber) {
        this.socialNumber = socialNumber;
    }
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public String getEmployerEmail() {
        return employerEmail;
    }
    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }
    public double getTaxCount() {
        return taxCount;
    }
    public void setTaxCount(double taxCount) {
        this.taxCount = taxCount;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", employee_number=" + employeeNumber + ", employer_email=" + employerEmail
                + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", soscial_number="
                + socialNumber + ", tax_count=" + taxCount + "]";
    }
}