package salarychecker.ui.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

/**
 * Test for SettingsController.
 */
public class SettingsControllerTest extends ApplicationTest {
  User user;
  Accounts accounts;
  private SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();

  private Button closeButton;
  private TextField changeFirstNameField;
  private TextField changeLastNameField;
  private TextField changeEmailField;
  private TextField changeConfirmedEmailField;
  private TextField changeEmployerField;
  private TextField changeConfirmedEmployerField;
  private TextField hourWageField;
  private TextField changePasswordField;
  private TextField changeConfirmedPasswordField;
  private TextField changeTaxBracketField;
  private TextField changeEmployeeNumberField;
  private Button saveChangesButton;
  private Text successMessageDisplay;
  private Text errorTextDisplay;


  @Override
  public void start(final Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    SettingsController controller = new SettingsController();
    loader.setController(controller);
    controller.setDataAccess(dataAccess);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/Settings.fxml"));
    createTestUsers();
    user = (User) dataAccess.userLogin("peter@live.no", "Test123!");
    final Parent parent = loader.load();
    controller.loadSettingsInfo();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  @BeforeEach
  public void initFields() {
    closeButton = lookup("#closeButton").query();
    changeFirstNameField = lookup("#changeFirstNameField").query();
    changeLastNameField = lookup("#changeLastNameField").query();
    changeEmailField = lookup("#changeEmailField").query();
    changeConfirmedEmailField = lookup("#changeConfirmedEmailField").query();
    changeEmployerField = lookup("#changeEmployerField").query();
    changeConfirmedEmployerField = lookup("#changeConfirmedEmployerField").query();
    hourWageField = lookup("#hourWageField").query();
    changePasswordField = lookup("#changePasswordField").query();
    changeConfirmedPasswordField = lookup("#changeConfirmedPasswordField").query();
    changeTaxBracketField = lookup("#changeTaxBracketField").query();
    changeEmployeeNumberField = lookup("#changeEmployeeNumberField").query();
    saveChangesButton = lookup("#saveChangesButton").query();
    successMessageDisplay = lookup("#successMessageDisplay").query();
    errorTextDisplay = lookup("#errorTextDisplay").query();
    user.addObserver(accounts);
  }

  @AfterEach
  public void tearDown() {
    Path.of(System.getProperty("user.home"), "/.salarychecker/Accounts.json").toFile().delete();
    Path.of(System.getProperty("user.home"), "/.salarychecker/SalarycheckerKeystore.jks").toFile().delete();
  }

  @Order(1)
  @Test
  public void testUpdateName() {
    writeInFields(changeFirstNameField, "Tes");
    writeInFields(changeLastNameField, "User");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    assertEquals("Tes", changeFirstNameField.getPromptText());
    assertEquals("User", changeLastNameField.getPromptText());
  }

  @Order(2)
  @Test
  public void testUpdateEmail() {
    writeInFields(changeEmailField, "test@mail.no");
    writeInFields(changeConfirmedEmailField, "test@mail.no");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    assertEquals("test@mail.no", changeEmailField.getPromptText());
    assertEquals("test@mail.no", changeConfirmedEmailField.getPromptText());
  }

  @Order(3)
  @Test
  public void testUpdateEmployerEmail() {
    writeInFields(changeEmployerField, "employertest@mail.no");
    writeInFields(changeConfirmedEmployerField, "employertest@mail.no");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    assertEquals("employertest@mail.no", changeEmployerField.getPromptText());
    assertEquals("employertest@mail.no", changeConfirmedEmployerField.getPromptText());
  }

  @Order(4)
  @Test
  public void testUpdatePassword() {
    writeInFields(changePasswordField, "Test123!");
    writeInFields(changeConfirmedPasswordField, "Test123!");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
  }

  @Order(5)
  @Test
  public void testUpdateTaxBracket() {
    writeInFields(changeTaxBracketField, "22.3");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    assertEquals("22.3", changeTaxBracketField.getPromptText());
  }

  @Order(6)
  @Test
  public void testUpdateHourWage() {
    writeInFields(hourWageField, "150");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    assertEquals("150.0", hourWageField.getPromptText());
  }

  @Order(7)
  @Test
  public void testUpdateEmployeeNumber() {
    writeInFields(changeEmployeeNumberField, "54321");
    clickOn(saveChangesButton);
    assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    assertEquals("54321", changeEmployeeNumberField.getPromptText());
  }

  @Order(8)
  @Test
  public void testInvalidInfo() {
    writeInFields(changeFirstNameField, "T");
    writeInFields(changeLastNameField, "User");
    clickOn(saveChangesButton);
    assertEquals("Name should only contain letters, and be atleast two letters..", 
        errorTextDisplay.getText());
    assertEquals("Peter", changeFirstNameField.getPromptText());
    clearFields(changeFirstNameField);
    clearFields(changeLastNameField);

    writeInFields(changeEmailField, "t");
    writeInFields(changeConfirmedEmailField, "t");
    clickOn(saveChangesButton);
    assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", 
        errorTextDisplay.getText());
    assertEquals("peter@live.no", changeEmailField.getPromptText());
    assertEquals("peter@live.no", changeConfirmedEmailField.getPromptText());
    clearFields(changeEmailField);
    clearFields(changeConfirmedEmailField);

    writeInFields(changeEmployerField, "t");
    writeInFields(changeConfirmedEmployerField, "t");
    clickOn(saveChangesButton);
    assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", 
        errorTextDisplay.getText());
    assertEquals("employeer1@gmail.com", changeEmployerField.getPromptText());
    assertEquals("employeer1@gmail.com", changeConfirmedEmployerField.getPromptText());
    clearFields(changeEmployerField);
    clearFields(changeConfirmedEmployerField);

    writeInFields(changePasswordField, "t");
    writeInFields(changeConfirmedPasswordField, "t");
    clickOn(saveChangesButton);
    assertEquals("Invalid password, must be at least 8 characters and contain at least 1 "
        + "digit and 1 lower and uppercase letter.", errorTextDisplay.getText());
    clearFields(changePasswordField);
    clearFields(changeConfirmedPasswordField);


    writeInFields(changeTaxBracketField, "150");
    clickOn(saveChangesButton);
    assertEquals("Tax count should be a decimal number between 0 and 100.", 
        errorTextDisplay.getText());
    assertEquals("30.0", changeTaxBracketField.getPromptText());
    clearFields(changeTaxBracketField);

    writeInFields(changeEmployeeNumberField, "1");
    clickOn(saveChangesButton);
    assertEquals("Employee number should be exactly 5 numbers.", errorTextDisplay.getText());
    assertEquals("12345", changeEmployeeNumberField.getPromptText());
    clearFields(changeEmployeeNumberField);
  }

  @Order(9)
  @Test
  public void testCloseButton() {
    clickOn(closeButton);
    AnchorPane profilePane = lookup("#profilePane").query();
    assertTrue(profilePane.isVisible());
  }

  private void writeInFields(TextField typeField, String text) {
    clickOn(typeField).write(text);
  }

  private void clearFields(TextField typeField) {
    typeField.clear();
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
