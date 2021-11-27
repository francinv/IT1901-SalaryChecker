package salarychecker.ui.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

/**
 * Tests for SalariesController.
 */
public class SalariesControllerTest extends ApplicationTest {

  private TableView<UserSale> salariesTable;
  SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();
  private User user;

  @Override
  public void start(final Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    SalariesController controller = new SalariesController();
    loader.setController(controller);
    controller.setDataAccess(dataAccess);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/Salaries.fxml"));
    createTestUsers();
    user = (User) dataAccess.userLogin("testsalaries@live.no", "Password123!");
    final Parent parent = loader.load();
    controller.loadTableView();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  @BeforeEach
  public void initFields() {
    salariesTable = lookup("#salariesTable").query();
  }

  @Test
  public void checkIfUserSalesShown() {
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
    try {
      User user = new User("Test", "User",
          "testsalaries@live.no", "Password123!", "22030191349",
          12345, "employeer1@gmail.com", 30.0, 130.0);
      UserSale testsale1 = new UserSale("August 2021", 15643.0, 10000.0);
      user.addUserSale(testsale1);
      UserSale testsale2 = new UserSale("September 2021", 13000.0, 8000.0);
      user.addUserSale(testsale2);
      dataAccess.createUser(user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
