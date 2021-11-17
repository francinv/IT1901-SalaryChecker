package salarychecker.ui.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import salarychecker.ui.controllers.AdminStartPageController;
import salarychecker.ui.controllers.HomepageController;
import salarychecker.ui.controllers.LoginController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest extends ApplicationTest {
    
    private TextField emailField;
    private PasswordField passwordField;
    private Button logInButton;
    private Text errorDisplay;
    private Button createButton;


    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/LogIn.fxml"));
        LoginController loginController = new LoginController();
        fxmlLoader.setController(loginController);
        final Parent parent = fxmlLoader.load();
        final Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    @BeforeEach
    public void initFields() {
        emailField = lookup("#email").query();
        passwordField = lookup("#password").query();
        logInButton = lookup("#logIn").query();
        errorDisplay = lookup("#errorDisplay").query();
        createButton = lookup("#createButton").query();
    }

    @Test
    @Order(1)
    public void testCreateUsersButtonWorks(){
        clickOn(createButton);
        assertEquals("Test users created!", createButton.getText());
        assertEquals("0x008000ff", createButton.getTextFill().toString());
    }

    @Test
    @Order(2)
    public void testLoginUser() {
        writeInLoginFields("seran@live.no", "Password123!");
        clickOn(logInButton);
        Window currentWindow = window(getTopModalStage().getScene());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/HomePage.fxml")); // load same anchorpane that currentWindow contains
            HomepageController homepageController = new HomepageController();
            loader.setController(homepageController);
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

    @Test
    @Order(3)
    public void testLogInAdminUser() {
        writeInLoginFields("francin.vinc@gmail.com", "Vandre333!");
        clickOn(logInButton);
        Window currentWindow = window(getTopModalStage().getScene());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/AdminStartPage.fxml")); // load same anchorpane that currentWindow contains
            AdminStartPageController adminStartPageController = new AdminStartPageController();
            loader.setController(adminStartPageController);
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

    @Test
    @Order(4)
    public void testInvalidEmail() {
        writeInLoginFields("s", "Password123!");
        clickOn(logInButton);
        assertEquals("Invalid email, must be of format: name-part@domain, e.g. example@example.com.", errorDisplay.getText());
    }

    @Test
    @Order(5)
    public void testInvalidPwd() {
        writeInLoginFields("seran@live.no", "P");
        clickOn(logInButton);
        assertEquals("Invalid password, must be at least 8 characters and contain at least 1 digit and 1 lower and uppercase letter.", errorDisplay.getText());
    }

    @Test
    @Order(6)
    public void testNonExistingUser() {
        writeInLoginFields("fxtest@gmail.no", "Test123!");
        clickOn(logInButton);
        assertEquals("This user is not registered.", errorDisplay.getText());
    }

    @AfterEach
    public void clearLoginFields() {
        emailField.clear();
        passwordField.clear();
    }


    private void writeInLoginFields(String email, String pwd) {
        clickOn(emailField).write(email);
        clickOn(passwordField).write(pwd);
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
