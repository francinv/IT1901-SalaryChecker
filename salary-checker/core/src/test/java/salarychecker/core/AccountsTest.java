package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsTest {

    private Accounts accounts;
    private User user;
    private User user1;
    private User user2;
    
    @BeforeEach
    void setUp(){
        accounts = new Accounts();
        user = new User("Jens", "Jensen", "jensen@salarychecker.com", "Jensen123!", "31129969420", 66638, "sjef@salarychecker.com", 22.0, 131.0);
        user1 = new User("Olav", "Olavson", "olavsen@salarychecker.com", "Olavson123!", "01018732455", 63789, "sjef@salarychecker.com", 22.0, 132);
        user2 = new User("Bro", "Fist", "fist@salarychecker.com", "Fist123!", "17051430730", 17051, "sjef@salarychecker.com", 42.5, 133);
        accounts.addUser(user);
    }

    @Test
    public void testAddMethods() {
        accounts.addUser(user1);
        assertTrue(2 == accounts.getAccounts().size());
        assertTrue(accounts.iterator().hasNext());
        assertThrows(IllegalArgumentException.class, () -> accounts.addUser(user));
        accounts.usersaleAdded(user, new UserSale("Januar 2021", 15000, 10000));
        assertNotNull(((User) accounts.getAccounts().get(0)).getUserSaleList().get(0));
    }
    
    @Test
    public void getAccountsTest(){
        assertNotNull(accounts.getAccounts());
    }

    @Test
    public void removeUserTest(){
        accounts.removeUser(user);
        assertTrue(0 == accounts.getAccounts().size());
        assertThrows(IllegalArgumentException.class, () -> accounts.removeUser(user));
    }

    @Test
    public void indexOfTest(){
        accounts.addUser(user1);
        accounts.addUser(user2);
        assertTrue(0 == accounts.indexOf(user) );
        assertTrue(1 == accounts.indexOf(user1) );
        assertTrue(2 == accounts.indexOf(user2) );
    }

    @Test
    public void containsTest(){
        assertTrue(accounts.contains(user));
    }

    @Test
     public void getUserTest(){
        assertEquals(user, accounts.getUser("jensen@salarychecker.com", "Jensen123!"));
    }

    @Test
    public void checkValidUserLoginTest(){
        assertTrue(accounts.checkValidUserLogin("jensen@salarychecker.com", "Jensen123!"));
    }

    @Test
    public void testUpdateMethods(){
        accounts.userInfoStringChanged(user, "Jens");
        assertEquals("Jens", user.getFirstname());
        accounts.userInfoStringChanged(user, "Jensen");
        assertEquals("Jensen", user.getLastname());
        accounts.userInfoStringChanged(user, "jensen@salarychecker.com");
        assertEquals("jensen@salarychecker.com", user.getEmail());
        accounts.userInfoStringChanged(user, "sjef@salarychecker.com");
        assertEquals("sjef@salarychecker.com", user.getEmployerEmail());
        accounts.userInfoStringChanged(user, "Jensen123!");
        assertEquals("Jensen123!", user.getPassword());
        accounts.userInfoDoubleChanged(user, 22.0);
        assertEquals(22.0, user.getTaxCount());
        accounts.userInfoDoubleChanged(user, 131.0);
        assertNotEquals(131.0, user.getHourRate());
        accounts.userInfoTaxCountChanged(user, 66638);
        assertEquals(66638, user.getEmployeeNumber());
    }
}