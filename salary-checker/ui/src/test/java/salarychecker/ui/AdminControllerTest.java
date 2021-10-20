package salarychecker.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.AdminUser;
import salarychecker.json.SalaryCheckerPersistence;

public class AdminControllerTest extends ApplicationTest {

    AdminUser adminUser;
    SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();

    private static Text adminName;
    private static Button createUserButton;
    private static Button logOutButton;
    private TextField createFirstNameField;
    private TextField createLastNameField;
    private TextField createEmailField;
    private TextField createPasswordField;
    private TextField createEmployeeNumberField;
    private TextField createSocialNumberField;
    private TextField createTaxField;
    private TextField createWageField;
    private ListView<String> existingUsers;
    private Text errorMessageDisplay;
    private Node listViewTab;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        adminUser = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");
        AdminController adminController = loader.getController();
        adminController.setAdminUser(adminUser);
        SCP.setSaveFile("Accounts.json");
        adminController.setAccounts(SCP.loadAccounts());
        adminController.loadInfo();
        adminController.loadListView();
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void initFields(){
        adminName = lookup("#adminName").query();
        createUserButton = lookup("#createUserButton").query();
        logOutButton = lookup("#logOutButton").query();
        createFirstNameField = lookup("#createFirstNameField").query();
        createLastNameField = lookup("#createLastNameField").query();
        createEmailField = lookup("#createEmailField").query();
        createPasswordField = lookup("#createPasswordField").query();
        createEmployeeNumberField = lookup("#createEmployeeNumberField").query();
        createSocialNumberField = lookup("#createSocialNumberField").query();
        createTaxField = lookup("#createTaxField").query();
        createWageField = lookup("#createWageField").query();
        existingUsers = lookup("#userList").query();
        errorMessageDisplay = lookup("#errorMessageDisplay").query();
        listViewTab = lookup(".tab-pane > .tab-header-area > .headers-region > .tab").nth(1).query();
    }

    @Test
    @Order(2)
    public void correctNameLoaded(){
        String expected = adminUser.getFirstname() + " " + adminUser.getLastname();
        String notexpected = "Seran Shanmugathas";
        assertEquals(expected, adminName.getText());
        assertNotEquals(notexpected, adminName.getText());
    }

    @Test
    @Order(3)
    public void createUser(){
        clickOn(createUserButton);
        assertEquals("All fields must be filled out.", errorMessageDisplay.getText());

        writeInFields("J", "Kessler", "jakob@gmail.no", "Password123!", 12345, "22031212345", 30.2, 133.3);
        assertEquals("Name should only contain letters, and be atleast two letters..", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "K", "jakob@gmail.no", "Password123!", 12345, "22031212345", 30.2, 133.3);
        assertEquals("Name should only contain letters, and be atleast two letters..", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "Kessler", "j", "Password123!", 12345, "22031212345", 30.2, 133.3);
        assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "Kessler", "jakob@gmail.no", "P", 12345, "22031212345", 30.2, 133.3);
        assertEquals("Invalid password, must be at least 8 characters and contain at least 1 digit and 1 lower and uppercase letter.", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "Kessler", "jakob@gmail.no", "Password123!", 1, "22031212345", 30.2, 133.3);
        assertEquals("Employee number should be exactly 5 numbers.", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "Kessler", "jakob@gmail.no", "Password123!", 12345, "abcdef", 30.2, 133.3);
        assertEquals("The entered social number, is not valid.", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "Kessler", "jakob@gmail.no", "Password123!", 12345, "22031212345", 159.0, 133.3);
        assertEquals("Tax count should be a decimal number between 0 and 100.", errorMessageDisplay.getText());
        clearFields();

        writeInFields("Jakob", "Kessler", "jakob@gmail.no", "", 12345, "22031212345", 159.0, 133.3);
        assertEquals("Please enter a password.", errorMessageDisplay.getText());
    }

    @Test
    @Order(1)
    public void existingUsersLoadedInListView(){
        clickOn(listViewTab);
        ObservableList<String> expected = FXCollections.observableArrayList("Seran Shanmugathas");
        ObservableList<String> notexpected = FXCollections.observableArrayList("Francin Vincent");
        assertEquals(expected, existingUsers.getItems());
        assertNotEquals(notexpected, existingUsers.getItems());
    }

    @Test
    @Order(4)
    public void testCreateUserUpdatedListView(){
        writeInFields("Jakob", "Kessler", "test@mail.com", "Test123!", 12345, "28040112345", 32, 132);
        clickOn(listViewTab);
        ObservableList<String> expected = FXCollections.observableArrayList("Seran Shanmugathas", "Jakob Kessler");
        ObservableList<String> notexpected = FXCollections.observableArrayList("Francin Vincent", "Test User");
        assertEquals(expected, existingUsers.getItems());
        assertNotEquals(notexpected, existingUsers.getItems());
    }


    private void clearFields(){
        createFirstNameField.clear();
        createLastNameField.clear();
        createEmailField.clear();
        createPasswordField.clear();
        createEmployeeNumberField.clear();
        createSocialNumberField.clear();
        createTaxField.clear();
        createWageField.clear();
    }

    private void writeInFields(String firstname, String lastname, String email, String password, int employeenumber, String socialnumber, double taxcount, double hoursal){
        clickOn(createFirstNameField).write(firstname);
        clickOn(createLastNameField).write(lastname);
        clickOn(createEmailField).write(email);
        clickOn(createPasswordField).write(password);
        clickOn(createEmployeeNumberField).write(String.valueOf(employeenumber));
        clickOn(createSocialNumberField).write(socialnumber);
        clickOn(createTaxField).write(String.valueOf(taxcount));
        clickOn(createWageField).write(String.valueOf(hoursal));
        clickOn(createUserButton);
    }




}
