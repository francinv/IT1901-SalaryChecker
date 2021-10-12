package salarychecker.core;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class AbstractUser {

    protected String firstname;
    protected String lastname;
    protected String email;
    protected String password;

    protected UserValidation userValidation = new UserValidation();
    protected static EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        userValidation.checkValidFirstname(firstname);
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        userValidation.checkValidLastname(lastname);
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        userValidation.checkValidEmail(email);
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        userValidation.checkValidPassword(password);
        try {
            this.password = encryptDecrypt.encrypt(password, firstname);
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
