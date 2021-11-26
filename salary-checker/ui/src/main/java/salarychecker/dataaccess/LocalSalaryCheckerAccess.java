package salarychecker.dataaccess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;

/**
 * This implementation of {@link SalaryCheckerAccess} is meant for 
 * using the app without the server running. 
 */
public class LocalSalaryCheckerAccess implements SalaryCheckerAccess {

  private Accounts accounts;
  private final SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
  private File salaryCSV;

  /**
   * Loads accounts from json-file.
   */
  public LocalSalaryCheckerAccess() {
    persistence.setFilePath("Accounts.json");
    try {
      this.accounts = persistence.loadAccounts();
    } catch (IllegalStateException | IOException e) {
      this.accounts = new Accounts();
      try {
        persistence.saveAccounts(accounts);
      } catch (IllegalStateException | IOException e1) {
        System.out.println(e1.getMessage());
      }
    }
  }

  @Override
  public Accounts readAccounts() throws IOException {
    return persistence.loadAccounts();
  }

  @Override
  public User readUser(String email) {
    if (accounts.getUser(email) instanceof User) {
      return (User) accounts.getUser(email);
    }
    return  null;
  }

  @Override
  public List<AbstractUser> readAccountsWithSameEmployer(String employerEmail) {
    return accounts.getUsersByEmployerEmail(employerEmail);
  }

  @Override
  public AbstractUser userLogin(String email, String password) {
    return accounts.getUser(email, password);
  }

  @Override
  public void registerNewAccounts(Accounts accounts) {
    this.accounts = accounts;
  }

  @Override
  public void createUser(User user) {
    if (user != null) {
      accounts.addUser(user);
    }
    try {
      persistence.saveAccounts(accounts);
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void createAdminUser(AdminUser adminUser) {
    if (adminUser != null) {
      accounts.addUser(adminUser);
      try {
        persistence.saveAccounts(accounts);
      } catch (IllegalStateException | IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void updateUserAttributes(AbstractUser user, int indexOfUser) {
    accounts.updateUserObject(user, indexOfUser);
    try {
      persistence.saveAccounts(accounts);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteAccounts() {
    accounts = null;
  }

  @Override
  public void uploadFile(File file) {
    this.salaryCSV = file;
  }

  @Override 
  public UserSale getUserSale(String salesperiod, String emailOfUser) {
    User user = readUser(emailOfUser);
    return user.getUserSale(salesperiod);
  }

  public String getFilePath() {
    return this.salaryCSV.getAbsolutePath();
  }

  public String getFileName() {
    return this.salaryCSV.getName();
  }

  @Override
  public void calculateSale(Calculation calculation, String emailOfUser) throws IOException {
    User user = this.readUser(emailOfUser);
    calculation.doCalculation(this.getFilePath(), user);
    try {
      persistence.saveAccounts(accounts);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
