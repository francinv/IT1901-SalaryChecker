package salarychecker.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salarychecker.core.*;

public class SalaryCheckerModuleTest {
    
    private ObjectMapper mapper;


    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new SalaryCheckerModule());
    }


    @Test
    public void testSerializers() {
        Accounts accounts = new Accounts();
        User hammad = new User("Hammad","Siddiqui","ham@mad.no","Qwerty!123", "23020094332", 12345, "employer@gmail.com", 30);
        AdminUser francin = new AdminUser("Francin", "Vincent", "francin@vincent.no", "Vandre!123");
        accounts.addUser(hammad);
        accounts.addUser(francin);

        checkUser(hammad, "Hammad", "Siddiqui", "ham@mad.no", 12345, "employer@gmail.com", 30);
    }

    static void checkUser(User user, String firstname, String lastname, String email, int employeeNumber, 
        String employerEmail, double taxCount) {

        assertEquals(firstname, user.getFirstname());
        assertEquals(lastname, user.getLastname());
        assertEquals(email, user.getEmail());
        assertEquals(employeeNumber, user.getEmployeeNumber());
        assertEquals(employerEmail, user.getEmployerEmail());
        assertEquals(taxCount, user.getTaxCount());
    }

    static void checkAdminUser(AdminUser adminUser, String firstname, String lastname, String email) {
        assertEquals(firstname, adminUser.getFirstname());
        assertEquals(lastname, adminUser.getLastname());
        assertEquals(email, adminUser.getEmail());
    }

    @Test
    public void testSerializersDeserializers() {

        Accounts accounts = new Accounts();
        User hammad = new User("Hammad","Siddiqui","ham@mad.no","Qwerty!123", "23020094332", 12345, "employer@gmail.com", 30);
        AdminUser francin = new AdminUser("Francin", "Vincent", "francin@vincent.no", "Vandre!123");
        accounts.addUser(hammad);
        accounts.addUser(francin);

        try {
            String jsonAsString = mapper.writeValueAsString(accounts);
            Accounts accounts2 = mapper.readValue(jsonAsString, Accounts.class);
            assertEquals(2, accounts2.getAccounts().size());
            Iterator<AbstractUser> it = accounts.iterator();
            AbstractUser user = it.next();
            assertEquals("Hammad", user.getFirstname());
            assertTrue(it.hasNext());
            checkAdminUser((AdminUser)it.next(), "Francin", "Vincent", "francin@vincent.no");
            assertFalse(it.hasNext());
        } catch (JsonProcessingException e) {
            fail();
        }
    }
}
