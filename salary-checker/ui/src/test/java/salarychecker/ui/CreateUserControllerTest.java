package salarychecker.ui;

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
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserControllerTest extends ApplicationTest {

  AdminUser adminUser;
  SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

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
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
    CreateUserController createUserController = new CreateUserController();
    loader.setController(createUserController);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);

    adminUser = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");
    createTestUser();
    createUserController.setUser(adminUser);
    persistence.setFilePath("Accounts.json");
    createUserController.setAccounts(persistence.loadAccounts());
    createUserController.loadUserAndAccount();
    stage.setScene(scene);
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
  public void testCreateUser() {
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

  private void writeInFields(TextField wantedField, String write) {
    clickOn(wantedField).write(write);
  }

  private void createTestUser() throws IOException {
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
    AdminUser testuser2 = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");

    Accounts acc = new Accounts();
    acc.addUser(testuser1);
    acc.addUser(testuser2);

    persistence.setFilePath("Accounts.json");
    persistence.saveAccounts(acc);
  }


}
