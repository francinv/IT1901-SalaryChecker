package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        user = new User("Jens", "Jensen", "jensen@salarychecker.com", "Jensen123!", "31129969420", 66638, "sjef@salarychecker.com", 22.0, 131);
        user1 = new User("Olav", "Olavson", "olavsen@salarychecker.com", "Olavson123!", "01018732455", 63789, "sjef@salarychecker.com", 22.0, 132);
        user2 = new User("Bro", "Fist", "fist@salarychecker.com", "Fist123!", "17051430730", 17051, "sjef@salarychecker.com", 42.5, 133);
        accounts.addUser(user);
    }

    @Test
    public void addUserTest() {
        assertTrue(1 == accounts.getAccounts().size());
    }
    
    @Test
    public void getAccountsTest(){
        assertNotNull(accounts.getAccounts());
    }

    @Test
    public void removeUserTest(){
        accounts.removeUser(user);
        assertTrue(0 == accounts.getAccounts().size());
    }

    @Test
    public void testIterator(){
        assertTrue(accounts.iterator().hasNext());
        assertEquals("Jens", accounts.iterator().next().getFirstname());
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

    // @Test
    // public void getUserTest(){
    //     assertEquals(user, accounts.getUser("jensen@salarychecker.com", "Jensen123!"));
    // }
    
    // @Test
    // public void checkValidUserLoginTest(){
    //     assertEquals(user, accounts.checkValidUserLogin("jensen@salarychecker.com", "Jensen123!"));
    // }


    @Test
    public void updatePasswordTest(){
        accounts.updatePassword("jensen@salarychecker.com", "Jensen1234!");
        assertNotEquals("Jensen123!", user.getPassword());
    }
}