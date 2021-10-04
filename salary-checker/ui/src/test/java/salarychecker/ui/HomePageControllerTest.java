package salarychecker.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.Accounts;
import salarychecker.core.User;

import java.io.IOException;

public class HomePageControllerTest extends ApplicationTest {

    //LOGIN - VARIABLES
    private TextField emailField;
    private PasswordField passwordField;
    private Button logInButton;

    //HOMEPAGE - VARIABLES
    private Button changePasswordButton;
    private TextField firstPasswordField;
    private TextField secondPasswordField;
    private Text nameDisplay;
    private Text emailDisplay;
    private Text idDisplay;

    FXMLLoader fxmlLoader = new FXMLLoader();
    HomepageController homepageController = new HomepageController();

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        homepageController = loader.getController();
    }

    @BeforeEach
    public void initFields() {
        User user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", 55555555555L, 12345, "employeer1@gmail.com", 30.0);
        homepageController.setUser(user);
        nameDisplay = lookup("#navnDisplay").query();
        emailDisplay = lookup("#epostDisplay").query();
        idDisplay = lookup("#idDisplay").query();
    }

    @Test
    public void checkCorrectInfoShown() {
        homepageController = fxmlLoader.getController();
        String name = homepageController.user.getFirstname() + " " + homepageController.user.getLastname();
        Assertions.assertEquals(name, nameDisplay.getText());

    }

    private void writeInLoginFields(String email, String pwd) {
        clickOn(emailField).write(email);
        clickOn(passwordField).write(pwd);
    }


}

