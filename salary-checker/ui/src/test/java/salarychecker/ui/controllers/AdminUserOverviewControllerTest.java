package salarychecker.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminUserOverviewControllerTest extends ApplicationTest {

  AdminUser adminUser;
  SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();

  private Button searchButton;
  private Button clearButton;
  private TextField searchField;
  private TableView<User> tableUsers;

  @Override
  public void start(final Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    AdminUserOverviewController controller = new AdminUserOverviewController();
    loader.setController(controller);
    controller.setDataAccess(dataAccess);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/AdminUserOverview.fxml"));
    createTestUsers();
    adminUser = (AdminUser) dataAccess.userLogin("boss@mail.com", "Vandre333!");
    final Parent parent = loader.load();
    createTestUsers();
    controller.loadTableView();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  @BeforeEach
  public void initFields() {
    searchButton = lookup("#searchButton").query();
    clearButton = lookup("#clearButton").query();
    searchField = lookup("#searchField").query();
    tableUsers = lookup("#tableUsers").query();
  }

  @Test
  public void testTableViewLoaded() {
    assertEquals("Ola", tableUsers.getItems().get(0).getFirstname());
  }

  @Test
  public void testResetSearchField() {
    clickOn(searchField).write("Ola");
    assertEquals("Ola", searchField.getText());
    clickOn(clearButton);
    assertEquals("", searchField.getText());
  }

  @Test
  public void testSearch() {
    clickOn(searchField).write("Ol");
    clickOn(searchButton);
    assertEquals("Ola", tableUsers.getItems().get(0).getFirstname());
  }

  private void createTestUsers() throws IOException {
    try {
      dataAccess.createAdminUser(new AdminUser("Kari", "Nordmann",
          "boss@mail.com", "Vandre333!"));
      User user = new User("Ola", "Nordmann",
          "ola@live.no", "Password123!", "22030191349",
          12345, "boss@mail.com", 30.0, 130.0);
      UserSale testsale1 = new UserSale("August 2021", 15643.0, 10000.0);
      user.addUserSale(testsale1);
      UserSale testsale2 = new UserSale("September 2021", 13000.0, 8000.0);
      user.addUserSale(testsale2);
      dataAccess.createUser(user);
      dataAccess.createUser(new User("Peter", "Nordmann",
          "peter@live.no", "Test123!", "22030191349",
          12345, "employeer1@gmail.com", 30.0, 130.0));
    } catch (Exception e) {
      e.getMessage();
    }
  }
}
