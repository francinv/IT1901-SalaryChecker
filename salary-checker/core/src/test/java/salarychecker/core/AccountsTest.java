package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsTest {

    private Accounts accounts;
    private AbstractUser user;
    private AbstractUser user1;
    private AbstractUser user2;
    
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
        assertThrows(IllegalStateException.class, () -> accounts.addUser(user));
        ((User) user).addUserSale(new UserSale("August 2021", 10000.0, 5000.0));
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
    public void getUserEmailAndPasswordTest(){
        assertEquals(user, accounts.getUser("jensen@salarychecker.com", "Jensen123!"));
        assertNull(accounts.getUser("jensen@salarychecker.com", "Jensen12345!"));
    }

    @Test
    public void getUserByEmailTest() {
        assertEquals(user, accounts.getUser("jensen@salarychecker.com"));
    }

    @Test
    public void getUsersByEmployerEmailTest() {
        accounts.addUser(user1);
        accounts.addUser(user2);
        List<AbstractUser> usersWithSameEmployer = accounts.getUsersByEmployerEmail("sjef@salarychecker.com");
        assertEquals(3, usersWithSameEmployer.size());
        assertTrue(usersWithSameEmployer.contains(user1));
        assertTrue(usersWithSameEmployer.contains(user2));
        assertTrue(usersWithSameEmployer.contains(user));
    }

    @Test
    public void checkValidUserLoginTest(){
        assertTrue(accounts.checkValidUserLogin("jensen@salarychecker.com", "Jensen123!"));
    }

    @Test
    public void testUpdateUserObject() {
        user.setEmail("jensen@salarychecker.no");
        accounts.updateUserObject(user, 0);
        assertEquals("jensen@salarychecker.no", accounts.getAccounts().get(0).getEmail());
    }

    @Test
    public void testUserInfoChanged() {
        user.setEmail("jensen@salarychecker.no");
        accounts.userInfoChanged(user);
        assertEquals("jensen@salarychecker.no", accounts.getAccounts().get(0).getEmail());
    }
}