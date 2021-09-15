package core;

/** 
 * Class for creating a user, and store their information.
 * 
*/
public class User {
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String soscial_number;
    private String employee_number;
    private String employer_email;
    private double tax_count;

    /**
     * Constructor
     * Not sure if we need all of these parameters.
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @param soscial_number
     * @param employee_number
     * @param employer_email
     * @param tax_count
     */
    public User(String firstname, String lastname, String email, String password, String soscial_number,
            String employee_number, String employer_email, double tax_count) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.soscial_number = soscial_number;
        this.employee_number = employee_number;
        this.employer_email = employer_email;
        this.tax_count = tax_count;
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
    public String getSoscial_number() {
        return soscial_number;
    }
    public void setSoscial_number(String soscial_number) {
        this.soscial_number = soscial_number;
    }
    public String getEmployee_number() {
        return employee_number;
    }
    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }
    public String getEmployer_email() {
        return employer_email;
    }
    public void setEmployer_email(String employer_email) {
        this.employer_email = employer_email;
    }
    public double getTax_count() {
        return tax_count;
    }
    public void setTax_count(double tax_count) {
        this.tax_count = tax_count;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", employee_number=" + employee_number + ", employer_email=" + employer_email
                + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", soscial_number="
                + soscial_number + ", tax_count=" + tax_count + "]";
    }
}