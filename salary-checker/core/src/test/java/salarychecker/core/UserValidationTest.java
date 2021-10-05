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
            22010192834L, 33333, "employeremail@email.com", 35.5);
        userValidation = new UserValidation();
        accounts = new Accounts();
    }

    @Test
    public void testValidCredentials() {
        String firstname = testUser.getFirstname();
        Assertions.assertTrue(userValidation.isValidFirstname(firstname));
        String lastname = testUser.getLastname();
        Assertions.assertTrue(userValidation.isValidLastname(lastname));
        String email = testUser.getEmail();
        Assertions.assertTrue(userValidation.isValidEmail(email));
        String password = testUser.getPassword();
        Assertions.assertTrue(userValidation.isValidPassword(password));
        Long socialNumber = testUser.getSocialNumber();
        Assertions.assertTrue(userValidation.isValidSocialNumber(socialNumber));
        int employeeNumber = testUser.getEmployeeNumber();
        Assertions.assertTrue(userValidation.isValidEmployeeNumber(employeeNumber));
        String employerEmail = testUser.getEmployerEmail();
        Assertions.assertTrue(userValidation.isValidEmail(employerEmail));
        double taxCount = testUser.getTaxCount();
        Assertions.assertTrue(userValidation.isValidTaxCount(taxCount));

        Assertions.assertTrue(userValidation.isValidUser(firstname, lastname, email, password, 
            socialNumber, employeeNumber, employerEmail, taxCount));
    }

    @Test
    public void testExistingUser() {
        accounts.addUser(testUser);
        //Todo add test
        assertFalse(userValidation.isExistingUser(testUser.getEmail(), testUser.getPassword()));
    }
}
