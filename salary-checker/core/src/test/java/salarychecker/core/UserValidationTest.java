package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserValidationTest {

    private User testUser;
    private UserValidation userValidation;
    private Accounts accounts;

    @BeforeEach
    public void setUp() {
        testUser = new User("Firstname", "Lastname", "email@email.com", "Password!123", 
            "22010192834", 33333, "employeremail@email.com", 35.5, 130.0);
        userValidation = new UserValidation();
        accounts = new Accounts();
    }

    @Test
    public void testValidCredentials() {
        String firstname = testUser.getFirstname();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidFirstname(firstname));
        String lastname = testUser.getLastname();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidLastname(lastname));;
        String email = testUser.getEmail();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidEmail(email));
        String password = testUser.getPassword();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidPassword(password));
        String socialNumber = testUser.getSocialNumber();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidSocialNumber(socialNumber));
        int employeeNumber = testUser.getEmployeeNumber();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidEmployeeNumber(employeeNumber));
        String employerEmail = testUser.getEmployerEmail();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidEmail(employerEmail));
        double taxCount = testUser.getTaxCount();
        Assertions.assertDoesNotThrow(() -> userValidation.checkValidTaxCount(taxCount));

        Assertions.assertDoesNotThrow(() -> userValidation.checkValidUser(firstname, lastname, email, password, 
            socialNumber, employeeNumber, employerEmail, taxCount));
    }
}
