package salarychecker.json;

import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import salarychecker.core.*;

public class SalaryCheckerPersistenceTest {
    
    private SalaryCheckerPersistence persistence  = new SalaryCheckerPersistence();


  @Test
  public void testSerializersDeserializers() {
    Accounts accounts = new Accounts();
    User hammad = new User("Hammad","Siddiqui","ham@mad.no","Qwerty!123", "23020094332", 12345, "employer@gmail.com", 30, 152.50);
    AdminUser francin = new AdminUser("Francin", "Vincent", "francin@vincent.no", "Vandre!123");
    
    UserSale userSale = new UserSale("Januar 2021", 15000, 10000);
    userSale.setDifference();
    hammad.addUserSale(userSale);
    accounts.addUser(hammad);
    accounts.addUser(francin);

    try {
      persistence.setFilePath("AccountsTest.json");
      persistence.saveAccounts(accounts);
      Accounts accounts2 = persistence.loadAccounts();
      Assertions.assertTrue(accounts2.iterator().hasNext());
      User hammad2 = ((User)accounts2.iterator().next());
      Assertions.assertNotEquals("Brage", hammad2.getFirstname());
      Iterator<AbstractUser> it = accounts2.iterator();
      Assertions.assertTrue(it.hasNext());
      SalaryCheckerModuleTest.checkUser((User) it.next(), "Hammad", "Siddiqui", "ham@mad.no", 12345, "employer@gmail.com", 30, 152.50);
      Assertions.assertTrue(it.hasNext());
      SalaryCheckerModuleTest.checkAdminUser((AdminUser) it.next(), "Francin", "Vincent", "francin@vincent.no");
      Assertions.assertFalse(it.hasNext());
      Assertions.assertTrue(hammad2.getUserSaleList().size() == 1);
      Assertions.assertTrue(hammad2.getUserSaleList().get(0).getDifference() == 5000);
      Assertions.assertTrue(hammad2.getUserSaleList().get(0).getPaid() == 10000);
      Assertions.assertTrue(hammad2.getUserSaleList().get(0).getExpected() == 15000);
      Assertions.assertEquals("Januar 2021", hammad2.getUserSaleList().get(0).getSalesperiod());
      
    } catch (IOException e) {
      Assertions.fail();
    }
  }
}
