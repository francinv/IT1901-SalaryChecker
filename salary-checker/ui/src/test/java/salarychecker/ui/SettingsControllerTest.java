package salarychecker.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingsControllerTest extends ApplicationTest {

    User user;
    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    Accounts accounts = new Accounts();

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
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        SettingsController settingsController = new SettingsController();
        loader.setController(settingsController);
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        createTestUsers();
        settingsController.setUser(user);
        accounts = persistence.loadAccounts();
        settingsController.setAccounts(accounts);
        settingsController.loadSettingsInfo();
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void initFields(){
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

    @Test
    public void testUpdateName() {
        writeInFields(changeFirstNameField, "Ser");
        writeInFields(changeLastNameField, "Shanmugathas");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
        assertEquals("Ser", changeFirstNameField.getPromptText());
        assertEquals("Shanmugathas", changeLastNameField.getPromptText());
    }

    @Test
    public void testUpdateEmail(){
        writeInFields(changeEmailField, "seran@mail.no");
        writeInFields(changeConfirmedEmailField, "seran@mail.no");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
        assertEquals("seran@mail.no", changeEmailField.getPromptText());
        assertEquals("seran@mail.no", changeConfirmedEmailField.getPromptText());
    }

    @Test
    public void testUpdateEmployerEmail() {
        writeInFields(changeEmployerField, "employertest@mail.no");
        writeInFields(changeConfirmedEmployerField, "employertest@mail.no");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
        assertEquals("employertest@mail.no", changeEmployerField.getPromptText());
        assertEquals("employertest@mail.no", changeConfirmedEmployerField.getPromptText());
    }

    @Test
    public void testUpdatePassword() {
        writeInFields(changePasswordField, "Test123!");
        writeInFields(changeConfirmedPasswordField, "Test123!");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    }

    @Test
    public void testUpdateTaxBracket() {
        writeInFields(changeTaxBracketField, "22.3");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
        assertEquals("22.3", changeTaxBracketField.getPromptText());
    }

    @Test
    public void testUpdateHourWage() {
        writeInFields(hourWageField, "150");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
        assertEquals("150.0", hourWageField.getPromptText());
    }

    @Test
    public void testUpdateEmployeeNumber(){
        writeInFields(changeEmployeeNumberField, "54321");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
        assertEquals("54321", changeEmployeeNumberField.getPromptText());
    }

    @Test
    public void testInvalidInfo() {
        writeInFields(changeFirstNameField, "S");
        writeInFields(changeLastNameField, "Shanmugathas");
        clickOn(saveChangesButton);
        assertEquals("Name should only contain letters, and be atleast two letters..", errorTextDisplay.getText());
        assertEquals("Seran", changeFirstNameField.getPromptText());
        clearFields(changeFirstNameField);
        clearFields(changeLastNameField);

        writeInFields(changeEmailField, "s");
        writeInFields(changeConfirmedEmailField, "s");
        clickOn(saveChangesButton);
        assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", errorTextDisplay.getText());
        assertEquals("seran@live.no", changeEmailField.getPromptText());
        assertEquals("seran@live.no", changeConfirmedEmailField.getPromptText());
        clearFields(changeEmailField);
        clearFields(changeConfirmedEmailField);

        writeInFields(changeEmployerField, "s");
        writeInFields(changeConfirmedEmployerField, "s");
        clickOn(saveChangesButton);
        assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", errorTextDisplay.getText());
        assertEquals("employeer1@gmail.com", changeEmployerField.getPromptText());
        assertEquals("employeer1@gmail.com", changeConfirmedEmployerField.getPromptText());
        clearFields(changeEmployerField);
        clearFields(changeConfirmedEmployerField);

        writeInFields(changePasswordField, "t");
        writeInFields(changeConfirmedPasswordField, "t");
        clickOn(saveChangesButton);
        assertEquals("Invalid password, must be at least 8 characters and contain at least 1 digit and 1 lower and uppercase letter.", errorTextDisplay.getText());
        clearFields(changePasswordField);
        clearFields(changeConfirmedPasswordField);


        writeInFields(changeTaxBracketField, "150");
        clickOn(saveChangesButton);
        assertEquals("Tax count should be a decimal number between 0 and 100.", errorTextDisplay.getText());
        assertEquals("30.0", changeTaxBracketField.getPromptText());
        clearFields(changeTaxBracketField);

        writeInFields(changeEmployeeNumberField, "1");
        clickOn(saveChangesButton);
        assertEquals("Employee number should be exactly 5 numbers.", errorTextDisplay.getText());
        assertEquals("12345", changeEmployeeNumberField.getPromptText());
        clearFields(changeEmployeeNumberField);
    }

    @Test
    public void testCloseButton(){
        clickOn(closeButton);
        AnchorPane profilePane = lookup("#profilePane").query();
        assertTrue(profilePane.isVisible());
    }

    private void createTestUsers() throws IOException {
        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "29059848796", 34567, "employeer2@gmail.com", 23.0, 130.0);

        Accounts acc = new Accounts();
        acc.addUser(testuser1);
        acc.addUser(testuser2);

        persistence.setSaveFile("Accounts.json");
        persistence.saveAccounts(acc);
    }

    private void writeInFields(TextField typeField, String text) {
        clickOn(typeField).write(text);
    }

    private void clearFields(TextField typeField){
        typeField.clear();
    }


}
