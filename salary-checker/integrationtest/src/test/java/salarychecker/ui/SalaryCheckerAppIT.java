package salarychecker.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.RemoteSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.json.internal.SalaryCheckerModule;
import salarychecker.ui.controllers.LoginController;
import salarychecker.ui.controllers.SalaryCheckerConfig;

/**
 * Salary Checker application.
 */
public class SalaryCheckerAppIT extends ApplicationTest {

  private ObjectMapper objectMapper;

  private SalaryCheckerConfig config;
  private LoginController loginController;
  private SalaryCheckerAccess dataAccess;
  private List<AbstractUser> testList = new ArrayList<>(); 

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loginController = new LoginController();
    loader.setController(loginController);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/LogIn.fxml"));
    config = new SalaryCheckerConfig();
    dataAccess = new RemoteSalaryCheckerAccess(new URI(config.getProperty("serverURI")));
    loginController.setDataAccess(dataAccess);
    final Parent parent = loader.load();  
    objectMapper = new ObjectMapper();
    objectMapper.registerModules(
            new SalaryCheckerModule()
    );

    stage.setScene(new Scene(parent));
    stage.show();
  }
    
  @BeforeAll
  public static void setupHeadless() {
    SalaryCheckerApp.supportHeadless();
  }

  @BeforeEach
  void generateData() {
    createTestUsers();
  }

  @AfterAll 
  public static void tearDown() {
    Path.of(System.getProperty("user.home") + "/.salarychecker/" + "Accounts.json").toFile().delete();
  }
    
  @Test
  @DisplayName("Client can read all users")
  void clientCanReadAllUsers() {
    try {
      Accounts accounts = loginController.getDataAccess().readAccounts();
      List<AbstractUser> readAccounts = accounts.getAccounts();

      for (int i = 0; i < readAccounts.size(); i++) {
        assertEquals(testList.get(i).getEmail(), readAccounts.get(i).getEmail());
      }
    } catch (IOException e) {
      fail();
    }
  }

  private void createTestUsers() {
    try {
      User user = new User("Test", "User",
            "test@live.no", "Password123!", "22030191349",
            12345, "employeer1@gmail.com", 30.0, 130.0);
      UserSale testsale1 = new UserSale("August 2021", 15643.0, 10000.0);
      user.addUserSale(testsale1);
      UserSale testsale2 = new UserSale("September 2021", 13000.0, 8000.0);
      user.addUserSale(testsale2);
      AdminUser adminUser = new AdminUser("Test", "Admin",
            "test@admin.no", "Password123!");
      testList.add(user);
      testList.add(adminUser);
      dataAccess.createUser(user);
      dataAccess.createAdminUser(adminUser);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      fail();
    }

  }



}
