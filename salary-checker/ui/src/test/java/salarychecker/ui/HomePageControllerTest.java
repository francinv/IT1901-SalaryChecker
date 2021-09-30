package salarychecker.ui;

import org.junit.jupiter.api.Assertions;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Text;
import org.testfx.api.FxRobot;

public class HomePagControllerTest extends ApplicationTest {

    //LOGIN - VARIABLES
    private TextField emailField;
    private PasswordField passwordField;
    private Button logInButton;

    //HOMEPAGE - VARIABLES
    private Button changePasswordButton;
    private TextField firstPasswordField;
    private TextField secondPasswordField;
    private javafx.scene.text.Text nameDisplay;
    private Text emailDisplay;
    private Text idDisplay;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
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
    }

    @Test
    public void checkCorrectInfoShown() {
        writeInLoginFields("seran@live.no", "Password123!");
        clickOn(logInButton);
        HomepageController homepageController = fxmlLoader.getController();
        initHomePageFields();
        String name = homepageController.user.getFirstname() + " " + homepageController.user.getLastname();
        Assertions.assertEquals(name, nameDisplay.getText();

    }

    private void writeInLoginFields(String email, String pwd) {
        clickOn(emailField).write(email);
        clickOn(passwordField).write(pwd);
    }

    private void initHomePageFields() {
        nameDisplay = lookup("#navnDisplay").query();
        emailDisplay = lookup("#epostDisplay").query();
        idDisplay = lookup("#idDisplay").query();
    }

}

