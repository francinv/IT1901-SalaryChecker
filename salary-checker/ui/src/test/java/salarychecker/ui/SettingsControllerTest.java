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
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingsControllerTest extends ApplicationTest {

    User user;
    SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
    Accounts accounts = new Accounts();
    SettingsController settingsController;

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
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        createTestUsers();
        settingsController = loader.getController();
        settingsController.setUser(user);
        accounts = SCP.loadAccounts();
        settingsController.setAccounts(accounts);
        settingsController.loadInfo();
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
    public void testUpdateName() throws IOException {
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
    }

    @Test
    public void testUpdateHourWage() {
        writeInFields(hourWageField, "150");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    }

    @Test
    public void testUpdateEmployeeNumber(){
        writeInFields(changeEmployeeNumberField, "12345");
        clickOn(saveChangesButton);
        assertEquals("Changes successfully saved.", successMessageDisplay.getText());
    }

    @Test
    public void testInvalidInfo() throws IOException {
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
        Window currentWindow = window(getTopModalStage().getScene());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml")); // load same anchorpane that currentWindow contains
            AnchorPane pane = loader.load();
            ObservableList<Node> unmodNodeListCurrentWindow = currentWindow.getScene().getRoot().getChildrenUnmodifiable(); // get the children of both
            ObservableList<Node> unmodNodeListLoadedWindow = pane.getChildrenUnmodifiable();
            for (int i = 0; i < unmodNodeListCurrentWindow.size(); i++) {
                assertEquals(unmodNodeListCurrentWindow.get(i).getId(), unmodNodeListLoadedWindow.get(i).getId()); // verify that they're identical by ID
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTestUsers() throws IOException {
        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "29059848796", 34567, "employeer2@gmail.com", 23.0, 130.0);

        Accounts acc = new Accounts();
        acc.addUser(testuser1);
        acc.addUser(testuser2);

        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(acc);

    }

    private void writeInFields(TextField typeField, String text) {
        clickOn(typeField).write(text);
    }

    private void clearFields(TextField typeField){
        typeField.clear();
    }

    private Stage getTopModalStage() {
        // Get a list of windows but ordered from top[0] to bottom[n] ones.
        // It is needed to get the first found modal window.
        final List<Window> allWindows = new ArrayList<>(new FxRobot().robotContext().getWindowFinder().listWindows());
        Collections.reverse(allWindows);

        return (Stage) allWindows
                .stream()
                .filter(window -> window instanceof Stage)
                .findFirst()
                .orElse(null);
    }

}
