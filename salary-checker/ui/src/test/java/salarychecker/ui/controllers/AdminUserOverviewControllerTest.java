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
    adminUser = (AdminUser) dataAccess.userLogin("employeer1@gmail.com", "Vandre333!");
    final Parent parent = loader.load();
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
    assertEquals("Test", tableUsers.getItems().get(0).getFirstname());
  }

  @Test
  public void testResetSearchField() {
    clickOn(searchField).write("Test");
    assertEquals("Test", searchField.getText());
    clickOn(clearButton);
    assertEquals("", searchField.getText());
  }

  @Test
  public void testSearch() {
    clickOn(searchField).write("Te");
    clickOn(searchButton);
    assertEquals("Test", tableUsers.getItems().get(0).getFirstname());
  }

  private void createTestUsers() throws IOException {
    try {
      dataAccess.createAdminUser(new AdminUser("Test", "Admin",
          "employeer1@gmail.com", "Vandre333!"));
      dataAccess.createUser(new User("Test", "User",
          "test@live.no", "Password123!", "22030191349",
          12345, "employeer1@gmail.com", 30.0, 130.0));
      dataAccess.createUser(new User("TestTwo", "User",
          "test2@live.no", "Password123!", "22030191349",
          12345, "employeer1@gmail.com", 30.0, 130.0));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
