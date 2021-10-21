package salarychecker.core;

import java.util.ArrayList;

/** 
 * Class for creating a user, and store their information.
 * This class inheritance from {@link AbstractUser}
 */
public class User extends AbstractUser {
    
    private String socialNumber;
    private int employeeNumber;
    private String employerEmail;
    private double taxCount;
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
        super.firstname = firstname;
        super.lastname = lastname;
        super.email = email;
        super.password = password;
        this.socialNumber = socialNumber;
        this.employeeNumber = employeeNumber;
        this.employerEmail = employerEmail;
        this.taxCount = taxCount;
        this.timesats = timesats;
    }

    /**
     * Empty constructor to use in json deserializer.
     */
    public User() {}

    public String getSocialNumber() {
        return socialNumber;
    }
    public void setSocialNumber(String socialNumber) {
        userValidation.checkValidSocialNumber(socialNumber);
        this.socialNumber = socialNumber;
    }
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(int employeeNumber) {
        userValidation.checkValidEmployeeNumber(employeeNumber);
        this.employeeNumber = employeeNumber;
        for(IUserObserver IUserObserver :userObs){
            IUserObserver.userInfoIntChanged(this, employeeNumber);
        }
    }
    public String getEmployerEmail() {
        return employerEmail;
    }
    public void setEmployerEmail(String employerEmail) {
        userValidation.checkValidEmail(employerEmail);
        this.employerEmail = employerEmail;
        for (IUserObserver IUserObserver : userObs){
            IUserObserver.userInfoStringChanged(this, employerEmail);
        }
    }

    public double getTaxCount() {
        return taxCount;
    }
    public void setTaxCount(double taxCount) {
        userValidation.checkValidTaxCount(taxCount);
        this.taxCount = taxCount;
        for (IUserObserver IUserObserver : userObs){
            IUserObserver.userInfoDoubleChanged(this, taxCount);
        }
    }
    public double getTimesats(){
        return timesats;
    }
    public void setTimesats(double timesats){
        userValidation.checkValidHourRate(timesats);
        this.timesats = timesats;
        for (IUserObserver IUserObserver : userObs){
            IUserObserver.userInfoDoubleChanged(this, timesats);
        }
    }

    public ArrayList<UserSale> getUserSaleList(){
        return userSales;
    }

    public boolean isExistingUserSale (UserSale userSale){
        for (UserSale u: userSales){
            if(u.getSalesperiod().equals(userSale.getSalesperiod())){
                return true;
            }
            return false;
        }
        return false;
    }

    public void addUserSale(UserSale userSale){
        if (!(isExistingUserSale(userSale))){
            userSales.add(userSale);
            for (IUserObserver IUserObserver : userObs){
                IUserObserver.usersaleAdded(this, userSale);
            }
        }
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