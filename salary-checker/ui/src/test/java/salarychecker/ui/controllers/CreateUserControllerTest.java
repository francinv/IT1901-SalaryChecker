package salarychecker.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserControllerTest extends ApplicationTest {

  private AdminUser adminUser;
  private SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();

  private Button createUserButton;
  private Button goBackButton;
  private Button goOnButton;
  private Text errorMessageDisplay;
  private AnchorPane firstLastPane;
  private AnchorPane empemailPane;
  private AnchorPane socialPassPane;
  private AnchorPane wageTaxPane;
  private TextField nameField;
  private TextField lastNameField;
  private TextField employerIdField;
  private TextField emailField;
  private TextField socialField;
  private TextField passwordField;
  private TextField confirmPasswordField;
  private TextField wageField;
  private TextField taxField;

  @BeforeEach
  public void initFields() {
    createUserButton = lookup("#createUserButton").query();
    goBackButton = lookup("#goBackButton").query();
    goOnButton = lookup("#goOnButton").query();
    errorMessageDisplay = lookup("#errorMessageDisplay").query();
    firstLastPane = lookup("#firstLastPane").query();
    empemailPane = lookup("#empemailPane").query();
    socialPassPane = lookup("#socialPassPane").query();
    wageTaxPane = lookup("#wageTaxPane").query();
    nameField = lookup("#nameField").query();
    lastNameField = lookup("#lastNameField").query();
    employerIdField = lookup("#employerIdField").query();
    emailField = lookup("#emailField").query();
    socialField = lookup("#socialField").query();
    passwordField = lookup("#passwordField").query();
    confirmPasswordField = lookup("#confirmPasswordField").query();
    wageField = lookup("#wageField").query();
    taxField = lookup("#taxField").query();
  }

  @Override
  public void start(final Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    CreateUserController controller = new CreateUserController();
    loader.setController(controller);
    controller.setDataAccess(dataAccess);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/CreateUser.fxml"));
    createTestUsers();
    adminUser = (AdminUser) dataAccess.userLogin("boss@mail.com", "Vandre333!");
    final Parent parent = loader.load();
    controller.loadUserAndAccount();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  @Test
  public void testCorrectlyLoaded() {
    assertFalse(empemailPane.isVisible());
    assertFalse(socialPassPane.isVisible());
    assertFalse(wageTaxPane.isVisible());
    assertFalse(goBackButton.isVisible());
    assertFalse(createUserButton.isVisible());
    assertTrue(firstLastPane.isVisible());
  }

  @Test
  public void testCreateUser() throws IOException {
    Path.of(System.getProperty("user.home"), "/.salarychecker/Accounts.json").toFile().delete();
    Path.of(System.getProperty("user.home"), "/.salarychecker/SalarycheckerKeystore.jks").toFile().delete();
    createTestUsers();
    clickOn(goOnButton);
    assertEquals("Please enter a name.",errorMessageDisplay.getText());
    writeInFields(nameField, "J");
    writeInFields(lastNameField, "Kessler");
    clickOn(goOnButton);
    assertEquals("Name should only contain letters, and be atleast two letters..", errorMessageDisplay.getText());
    nameField.clear();
    writeInFields(nameField, "Jakob");
    clickOn(goOnButton);
    assertFalse(firstLastPane.isVisible());
    assertTrue(empemailPane.isVisible());
    assertTrue(goBackButton.isVisible());
    clickOn(goBackButton);
    assertTrue(firstLastPane.isVisible());
    assertFalse(empemailPane.isVisible());
    assertFalse(goBackButton.isVisible());
    clickOn(goOnButton);
    clickOn(goOnButton);
    assertEquals("Please enter an employee number.", errorMessageDisplay.getText());
    writeInFields(employerIdField, "1");
    clickOn(goOnButton);
    assertEquals("Employee number should be exactly 5 numbers.", errorMessageDisplay.getText());
    employerIdField.clear();
    writeInFields(employerIdField, "12345");
    clickOn(goOnButton);
    assertEquals("Please enter an email.", errorMessageDisplay.getText());
    writeInFields(emailField, "t");
    clickOn(goOnButton);
    assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", errorMessageDisplay.getText());
    emailField.clear();
    writeInFields(emailField, "jakob@mail.no");
    clickOn(goOnButton);
    assertFalse(empemailPane.isVisible());
    assertTrue(socialPassPane.isVisible());
    clickOn(goBackButton);
    assertTrue(empemailPane.isVisible());
    assertFalse(socialPassPane.isVisible());
    clickOn(goOnButton);
    clickOn(goOnButton);
    assertEquals("Please enter a social number.", errorMessageDisplay.getText());
    writeInFields(socialField, "2");
    clickOn(goOnButton);
    assertEquals("The entered social number, is not valid.", errorMessageDisplay.getText());
    socialField.clear();
    writeInFields(socialField, "22030112345");
    clickOn(goOnButton);
    assertEquals("Please enter a password.", errorMessageDisplay.getText());
    writeInFields(passwordField, "Test123!");
    clickOn(goOnButton);
    assertEquals("Passwords does not match.", errorMessageDisplay.getText());
    passwordField.clear();
    writeInFields(passwordField, "t");
    writeInFields(confirmPasswordField, "t");
    clickOn(goOnButton);
    assertEquals("Invalid password, must be at least 8 characters and contain at least 1 digit and 1 lower and uppercase letter.", errorMessageDisplay.getText());
    passwordField.clear();
    confirmPasswordField.clear();
    writeInFields(passwordField, "Test123!");
    writeInFields(confirmPasswordField, "Test123!");
    clickOn(goOnButton);
    assertFalse(socialPassPane.isVisible());
    assertTrue(wageTaxPane.isVisible());
    assertFalse(goOnButton.isVisible());
    assertTrue(createUserButton.isVisible());
    clickOn(goBackButton);
    assertTrue(socialPassPane.isVisible());
    assertFalse(wageTaxPane.isVisible());
    assertTrue(goOnButton.isVisible());
    assertFalse(createUserButton.isVisible());
    clickOn(goOnButton);
    clickOn(createUserButton);
    assertEquals("Please enter a hour rate", errorMessageDisplay.getText());
    writeInFields(wageField, "132.3");
    clickOn(createUserButton);
    assertEquals("Please enter a tax count.", errorMessageDisplay.getText());
    writeInFields(taxField, "32.3");
    clickOn(createUserButton);
    assertEquals("User created!", errorMessageDisplay.getText());
    assertEquals("0x008000ff", errorMessageDisplay.getFill().toString());
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

  private void writeInFields(TextField wantedField, String write) {
    clickOn(wantedField).write(write);
  }

  public AdminUser getAdminUser() {
      return adminUser;
  }
}
