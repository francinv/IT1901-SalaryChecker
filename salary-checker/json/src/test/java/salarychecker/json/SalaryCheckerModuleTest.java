package salarychecker.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
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
        User hammad = new User("Hammad","Siddiqui","ham@mad.no","Qwerty!123", "23020094332", 12345, "employer@gmail.com", 30, 152.50);
        AdminUser francin = new AdminUser("Francin", "Vincent", "francin@vincent.no", "Vandre!123");
        accounts.addUser(hammad);
        accounts.addUser(francin);

        checkUser(hammad, "Hammad", "Siddiqui", "ham@mad.no", 12345, "employer@gmail.com", 30, 152.50);
    }

    static void checkUser(User user, String firstname, String lastname, String email, int employeeNumber, 
        String employerEmail, double taxCount, double hourRate) {

        Assertions.assertEquals(firstname, user.getFirstname());
        Assertions.assertEquals(lastname, user.getLastname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(employeeNumber, user.getEmployeeNumber());
        Assertions.assertEquals(employerEmail, user.getEmployerEmail());
        Assertions.assertEquals(taxCount, user.getTaxCount());
        Assertions.assertEquals(hourRate, user.getHourRate());
    }

    static void checkAdminUser(AdminUser adminUser, String firstname, String lastname, String email) {
        Assertions.assertEquals(firstname, adminUser.getFirstname());
        Assertions.assertEquals(lastname, adminUser.getLastname());
        Assertions.assertEquals(email, adminUser.getEmail());
    }

    @Test
    public void testSerializersDeserializers() {

        Accounts accounts = new Accounts();
        User hammad = new User("Hammad","Siddiqui","ham@mad.no","Qwerty!123", "23020094332", 12345, "employer@gmail.com", 30, 152.50);
        AdminUser francin = new AdminUser("Francin", "Vincent", "francin@vincent.no", "Vandre!123");
        accounts.addUser(hammad);
        accounts.addUser(francin);

        try {
            String jsonAsString = mapper.writeValueAsString(accounts);
            Accounts accounts2 = mapper.readValue(jsonAsString, Accounts.class);
            Assertions.assertEquals(2, accounts2.getAccounts().size());
            Iterator<AbstractUser> it = accounts.iterator();
            AbstractUser user = it.next();
            Assertions.assertEquals("Hammad", user.getFirstname());
            Assertions.assertTrue(it.hasNext());
            checkAdminUser((AdminUser)it.next(), "Francin", "Vincent", "francin@vincent.no");
            Assertions.assertFalse(it.hasNext());
        } catch (JsonProcessingException e) {
            Assertions.fail();
        }
    }
}
