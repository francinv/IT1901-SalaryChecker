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
        try {
            super.password = encryptDecrypt.encrypt(password, firstname);
            this.socialNumber = encryptDecrypt.encrypt(socialNumber, firstname);
        } catch (NumberFormatException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
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
            this.socialNumber = encryptDecrypt.encrypt(socialNumber, firstname);
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

    public static void main(String[] args) {
        User user = new User();
        try {
            user.setFirstname("3");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}