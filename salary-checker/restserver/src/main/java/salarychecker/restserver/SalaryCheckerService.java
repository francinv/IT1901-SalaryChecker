package salarychecker.restserver;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * This class is used by the controller to manage Tracker-objects that are sent or requested
 * by the client.
 */
@Service
public class SalaryCheckerService {

    private Accounts accounts;
    private SalaryCheckerPersistence salaryCheckerPersistence;
 
    public SalaryCheckerService(Accounts accounts) {
        this.accounts = accounts;
        this.salaryCheckerPersistence = new SalaryCheckerPersistence();
        salaryCheckerPersistence.setFilePath("springbootserver-salarychecker.json");
    }

    public SalaryCheckerService() {
        this(createDeafaultAccounts());
    }

    public List<AbstractUser> getAccounts() {
        return accounts.getAccounts();
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public static Accounts createDeafaultAccounts() {
        SalaryCheckerPersistence salaryCheckerPersistence = new SalaryCheckerPersistence();
        URL url = SalaryCheckerService.class.getResource("default-accounts.json");
        if (url != null) {
            try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                return salaryCheckerPersistence.readAccounts(reader);
              } catch (IOException e) {
                System.out.println("Could not read default-salarychecker.json. \n Rigging Accounts manually ("
                    + e + ")");
              }
        }
        return manuallyCreateAccounts();
    }

  /**
   * Method that creates two test users.
   */
  private static Accounts manuallyCreateAccounts() {
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no",
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
    AdminUser testuser2 = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com",
        "Vandre333!");

    Accounts acc = new Accounts();
    acc.addUser(testuser1);
    acc.addUser(testuser2);
    return acc;
   }

  public void autoSaveAccounts() {
    if (salaryCheckerPersistence != null) {
      try {
      salaryCheckerPersistence.saveAccounts(accounts);
      } catch (IllegalStateException | IOException e) {
      System.err.println("Couldn't auto-save Accounts: " + e);
      }
    }
  }

  public AbstractUser getUserByEmail(String email) {
    return accounts.getUser(email);
  }

  public List<AbstractUser> getUsersByEmployerEmail(String employerEmail) {
    return accounts.getUsersByEmployerEmail(employerEmail);
  }
    
}