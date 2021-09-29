package salarychecker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("Firstname", "Lastname", "email@email.com", "password!123", 
            22019893456L, 33333, "employer_email@email.com", 35.5);
    }

    @Test
    public void testConstructor() {
        User loginConstructor = new User("email@email.com", "password!123");
        Assertions.assertNotNull(loginConstructor.getEmail());

        User emptyConstructor = new User();
        Assertions.assertNull(emptyConstructor.getEmail());
    }

    @Test
    public void testGetMethods() {
        Assertions.assertEquals("Firstname", testUser.getFirstname());
        Assertions.assertEquals("Lastname", testUser.getLastname());
        Assertions.assertEquals("email@email.com", testUser.getEmail());
        Assertions.assertEquals("password!123", testUser.getPassword());
        Assertions.assertEquals(22019893456L, testUser.getSocialNumber());
        Assertions.assertEquals(33333, testUser.getEmployeeNumber());
        Assertions.assertEquals("employer_email@email.com", testUser.getEmployerEmail());
        Assertions.assertEquals(35.5, testUser.getTaxCount());
    }

    @Test
    public void testSetMethods() {
        testUser.setFirstname("Sander");
        Assertions.assertNotEquals("Firstname", testUser.getFirstname());
        testUser.setLastname("Olsen");
        Assertions.assertNotEquals("Lastname", testUser.getLastname());
        testUser.setEmail("email@gmail.com");
        Assertions.assertNotEquals("email@email.com", testUser.getEmail());
        testUser.setPassword("passord");
        Assertions.assertNotEquals("password!123", testUser.getPassword());
        testUser.setSocialNumber(22030199999L);
        Assertions.assertNotEquals(22019893456L, testUser.getSocialNumber());
        testUser.setEmployeeNumber(11111);
        Assertions.assertNotEquals(33333, testUser.getEmployeeNumber());
        testUser.setEmployerEmail("employer_email@gmail.com");
        Assertions.assertNotEquals("employer_email@email.com", testUser.getEmployerEmail());
        testUser.setTaxCount(34.5);
        Assertions.assertNotEquals(35.5, testUser.getTaxCount());
    }
}
