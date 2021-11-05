package salarychecker.ui;

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
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminUserOverviewControllerTest extends ApplicationTest {

  AdminUser adminUser;
  SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

  private Button searchButton;
  private Button clearButton;
  private TextField searchField;
  private TableView<User> tableUsers;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUserOverview.fxml"));
    AdminUserOverviewController adminUserOverviewController = new AdminUserOverviewController();
    loader.setController(adminUserOverviewController);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);

    adminUser = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");
    createTestUser();
    adminUserOverviewController.setUser(adminUser);
    persistence.setFilePath("Accounts.json");
    adminUserOverviewController.setAccounts(persistence.loadAccounts());
    adminUserOverviewController.loadTableView();
    stage.setScene(scene);
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
    assertEquals("Seran", tableUsers.getItems().get(0).getFirstname());
    assertEquals("Jakob", tableUsers.getItems().get(1).getFirstname());
  }

  @Test
  public void testResetSearchField() {
    clickOn(searchField).write("Hello");
    assertEquals("Hello", searchField.getText());
    clickOn(clearButton);
    assertEquals("", searchField.getText());
  }

  @Test
  public void testSearch() {
    clickOn(searchField).write("Se");
    clickOn(searchButton);
    assertEquals("Seran", tableUsers.getItems().get(0).getFirstname());
  }

  private void createTestUser() throws IOException {
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no",
        "Password123!", "22030191349", 12345,
        "employeer1@gmail.com", 30.0, 130);
    User testuser2 = new User("Jakob", "Kessler", "jakob@mail.no",
        "Password123!", "22020312345", 12345,
        "employeer1@gmail.com", 30.0, 130);
    AdminUser admintest = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");

    Accounts acc = new Accounts();
    acc.addUser(testuser1);
    acc.addUser(testuser2);
    acc.addUser(admintest);

    persistence.setFilePath("Accounts.json");
    persistence.saveAccounts(acc);
  }
}
