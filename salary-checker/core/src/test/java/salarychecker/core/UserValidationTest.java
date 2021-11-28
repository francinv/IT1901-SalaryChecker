package salarychecker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserValidationTest {

    private User testUser;
    private UserValidation userValidation;

    @BeforeEach
    public void setUp() {
        testUser = new User("Firstname", "Lastname", "email@email.com", "Password!123", 
            "22010192834", 33333, "employeremail@email.com", 35.5, 130.0);
        userValidation = new UserValidation();
    }

    @Test
    public void testValidCredentials() {
        User wrongUser = new User();
        String firstname = testUser.getFirstname();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setFirstname("2"));
        String lastname = testUser.getLastname();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setLastname("2"));
        String email = testUser.getEmail();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setEmail("2"));
        String password = testUser.getPassword();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setPassword("password"));
        String socialNumber = testUser.getSocialNumber();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setSocialNumber("socialNumber"));
        int employeeNumber = testUser.getEmployeeNumber();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setEmployeeNumber(2));
        String employerEmail = testUser.getEmployerEmail();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setEmployerEmail("employerEmail"));
        double taxCount = testUser.getTaxCount();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setTaxCount(22222));
        double hourRate = testUser.getHourRate();
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setHourRate(0.0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setFirstname(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setLastname(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setEmail(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setPassword(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setSocialNumber(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setEmployeeNumber(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setEmployerEmail(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> wrongUser.setTaxCount(0));
        Assertions.assertDoesNotThrow(() -> UserValidation.checkValidUser(firstname, lastname, email, password, 
            socialNumber, employeeNumber, employerEmail, taxCount, hourRate ));

        Assertions.assertThrows(IllegalArgumentException.class, () -> UserValidation.isEqualPassword("password1", "password2"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserValidation.isEqualEmail("email1", "email2"));
        Accounts accounts = new Accounts();
        accounts.addUser(testUser);
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserValidation.isValidLogIn("email@email.com", "Password123!", accounts));
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserValidation.isNotExistingUser("email@emaile.com", "Password123!", accounts));
    }
}
