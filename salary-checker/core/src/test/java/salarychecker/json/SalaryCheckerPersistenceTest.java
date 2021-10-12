package salarychecker.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salarychecker.json.*;
import salarychecker.core.*;

public class SalaryCheckerPersistenceTest {
    
    private SalaryCheckerPersistence SCP  = new SalaryCheckerPersistence();


  @Test
  public void testSerializersDeserializers() {
    Accounts accounts = new Accounts();
    User hammad = new User("Hammad","Siddiqui","ham@mad.no","Qwerty!123", "23020094332", 12345, "employer@gmail.com", 30);
    AdminUser francin = new AdminUser("Francin", "Vincent", "francin@vincent.no", "Vandre!123");
    accounts.addUser(hammad);
    accounts.addUser(francin);

    try {
      StringWriter writer = new StringWriter();
      SCP.writeAccounts(accounts, writer);
      String json = writer.toString();
      Accounts accounts2 = SCP.readAccounts(new StringReader(json));
      assertTrue(accounts2.iterator().hasNext());
      AbstractUser user2 = accounts2.iterator().next();
      assertNotEquals("Brage", user2.getFirstname());
      Iterator<AbstractUser> it = accounts2.iterator();
      assertTrue(it.hasNext());
      SalaryCheckerModuleTest.checkUser((User) it.next(), "Hammad", "Siddiqui", "ham@mad.no", 12345, "employer@gmail.com", 30);
      assertTrue(it.hasNext());
      SalaryCheckerModuleTest.checkAdminUser((AdminUser) it.next(), "Francin", "Vincent", "francin@vincent.com");
      assertFalse(it.hasNext());

    } catch (IOException e) {
      fail();
    }
  }
}
