package salarychecker.core;

import java.util.ArrayList;

/** 
 * Class for creating a user, and store their information.
 * 
*/
public class User {
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String socialNumber;
    private int employeeNumber;
    private String employerEmail;
    private double taxCount;
    private UserValidation validation;
    private double timesats;

    private ArrayList<UserSale> userSales = new ArrayList<UserSale>();

    /**
     * Constructor
     * Not sure if we need all of these parameters.
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @param socialNumber
     * @param employeeNumber
     * @param employerEmail
     * @param taxCount
     */
    public User(String firstname, String lastname, String email, String password, String socialNumber,
            int employeeNumber, String employerEmail, double taxCount, double timesats) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.socialNumber = socialNumber;
        this.employeeNumber = employeeNumber;
        this.employerEmail = employerEmail;
        this.taxCount = taxCount;
        this.timesats = timesats;
    }

    public User(String email, String password) {
        this.validation = new UserValidation();
        if (validation.isExistingUser(email, password)) {
            this.email = email;
            this.password = password;
        }
    }

    public User() {}

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
    public String getSocialNumber() {
        return socialNumber;
    }
    public void setSocialNumber(String socialNumber) {
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
    public double getTimesats(){
        return timesats;
    }
    public void setTimesats(int timesats){
        this.timesats = timesats;
    }

    public ArrayList<UserSale> getUserSaleList(){
        return userSales;
    }

    public void addUserSale(String salesperiod, double expected, double paid){
        UserSale userSale = new UserSale();
        userSale.setSalesperiod(salesperiod);
        userSale.setExpected(expected);
        userSale.setPaid(paid);
        userSale.setDifference();

        userSales.add(userSale);
    }
    

    @Override
    public String toString() {
        return "{" +
            " firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", socialNumber='" + getSocialNumber() + "'" +
            ", employeeNumber='" + getEmployeeNumber() + "'" +
            ", employerEmail='" + getEmployerEmail() + "'" +
            ", taxCount='" + getTaxCount() + "'" +
            ", timesats='" + getTimesats() + "'" +
            ", userSales='" + getUserSaleList() + "'" +
            "}";
    }
}