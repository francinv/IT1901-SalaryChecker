package salarychecker.core;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/** 
 * Class for creating a user, and store their information.
 * This class inheritance from {@link AbstractUser}
 */
public class User extends AbstractUser {
    
    private String socialNumber;
    private int employeeNumber;
    private String employerEmail;
    private double taxCount;
    private UserValidation validation;
    private int expectedsalary;
    private int paidsalary;
    private int timesats;

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
            int employeeNumber, String employerEmail, double taxCount) {
        super.firstname = firstname;
        super.lastname = lastname;
        super.email = email;
        super.password = password;
        this.socialNumber = socialNumber;
        this.employeeNumber = employeeNumber;
        this.employerEmail = employerEmail;
        this.taxCount = taxCount;    
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
        try {
            this.socialNumber = encryptDecrypt.encrypt(socialNumber, lastname + firstname);
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(int employeeNumber) {
        userValidation.checkValidEmployeeNumber(employeeNumber);
        this.employeeNumber = employeeNumber;
    }
    public String getEmployerEmail() {
        return employerEmail;
    }
    public void setEmployerEmail(String employerEmail) {
        userValidation.checkValidEmail(email);
        this.employerEmail = employerEmail;
    }
    public double getTaxCount() {
        return taxCount;
    }
    public void setTaxCount(double taxCount) {
        userValidation.checkValidTaxCount(taxCount);
        this.taxCount = taxCount;
    }
    public int getTimesats(){
        return timesats;
    }
    public void setTimesats(int timesats){
        this.timesats = timesats;
    }

    @Override
    public String toString() {
        return "{" +
            " firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            " socialNumber='" + getSocialNumber() + "'" +
            ", employeeNumber='" + getEmployeeNumber() + "'" +
            ", employerEmail='" + getEmployerEmail() + "'" +
            ", taxCount='" + getTaxCount() + "'" +
            "}";
    }
    
}