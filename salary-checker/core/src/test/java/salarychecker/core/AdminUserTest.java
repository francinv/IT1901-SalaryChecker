package salarychecker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminUserTest{

    private AdminUser adminUser;
    private Accounts accounts;

    @BeforeEach
    public void setUp(){
        adminUser = new AdminUser("Jakob", "Kessler", "kessler@salarychecker.com", "JakobKessler99!");
        accounts = new Accounts();
    }

    @Test
    public void constructorTest(){
       Assertions.assertNotNull(adminUser);
       AdminUser emptyConstructor = new AdminUser();
       Assertions.assertNull(emptyConstructor.getEmail());
    }
    
    @Test
    public void testGetMethods(){
        Assertions.assertEquals("Jakob", adminUser.getFirstname());
        Assertions.assertEquals("Kessler", adminUser.getLastname());
        Assertions.assertEquals("kessler@salarychecker.com", adminUser.getEmail());
        Assertions.assertNotEquals("JakobKessler99!", adminUser.getPassword());
    }

    @Test
    public void testSetMethods() {
        adminUser.setFirstname("Navn");
        Assertions.assertEquals("Navn", adminUser.getFirstname());

        adminUser.setLastname("Etternavn");
        Assertions.assertEquals("Etternavn", adminUser.getLastname());

        adminUser.setEmail("etternavn@salarychecker.com");
        Assertions.assertEquals("etternavn@salarychecker.com", adminUser.getEmail());

        adminUser.setPassword("Passord123!");
        Assertions.assertNotEquals("Passord123!", adminUser.getPassword());

    }

    @Test
    public void testCreateUser(){
        adminUser.createUser("Jens", "Jensen", "jensen@salarychecker.com", "Jensen123!", "31129969420", 66638, "sjef@salarychecker.com", 22.0);
        Assertions.assertNotNull(accounts.getUser("jensen@salarychecker.com", "Jensen123!"));
    }
}