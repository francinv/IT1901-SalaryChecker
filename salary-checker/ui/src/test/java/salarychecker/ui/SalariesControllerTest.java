package salarychecker.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalariesControllerTest extends ApplicationTest {

  private TableView<UserSale> salariesTable;
  SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Salaries.fxml"));
    SalariesController salariesController = new SalariesController();
    loader.setController(salariesController);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);

    User user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
    createTestUsers();
    salariesController.setUser(user);
    salariesController.setAccounts(persistence.loadAccounts());
    UserSale testsale1 = new UserSale("August 2021", 15643.0, 10000.0);
    user.addUserSale(testsale1);
    UserSale testsale2 = new UserSale("September 2021", 13000.0, 8000.0);
    user.addUserSale(testsale2);
    salariesController.loadTableView();
    stage.setScene(scene);
    stage.show();
  }

  @BeforeEach
  public void initFields(){
    salariesTable = lookup("#salariesTable").query();
  }

  @Test
  public void checkIfUserSalesShown(){
    UserSale userSale1 = salariesTable.getItems().get(0);
    assertEquals("August 2021", userSale1.getSalesperiod());
    assertEquals(15643.0, userSale1.getExpected());
    assertEquals(10000.0, userSale1.getPaid());
    assertEquals(5643.0, userSale1.getDifference());
    UserSale userSale2 = salariesTable.getItems().get(1);
    assertEquals("September 2021", userSale2.getSalesperiod());
    assertEquals(13000.0, userSale2.getExpected());
    assertEquals(8000.0, userSale2.getPaid());
    assertEquals(5000.0, userSale2.getDifference());
  }


  private void createTestUsers() throws IOException {
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
    User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "29059848796", 34567, "employeer2@gmail.com", 23.0, 130.0);

    Accounts accounts = new Accounts();
    accounts.addUser(testuser1);
    accounts.addUser(testuser2);

    persistence.setFilePath("Accounts.json");
    persistence.saveAccounts(accounts);
  }
}
