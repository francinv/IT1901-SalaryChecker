package salarychecker.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.Assertions;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.EncryptDecrypt;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageControllerTest extends ApplicationTest {


    //HOMEPAGE - VARIABLES
    private Button changePasswordButton;
    private TextField firstPasswordField;
    private TextField secondPasswordField;
    private Text nameDisplay;
    private Text emailDisplay;
    private Text idDisplay;
    SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
    User user;
    EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "55555555555", 12345, "employeer1@gmail.com", 30.0, 130.0);
        createTestUsers();
        HomepageController homepageController = loader.getController();
        homepageController.setUser(user);
        homepageController.setAccounts(SCP.loadAccounts());
        homepageController.loadInfo();

        stage.setScene(scene);
        stage.show();

    }

    private void createTestUsers() throws IOException {
        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "55555555555", 12345, "employeer1@gmail.com", 30.0, 130.0);
        User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "222222222222", 34567, "employeer2@gmail.com", 23.0, 130.0);

        Accounts accounts = new Accounts();
        accounts.addUser(testuser1);
        accounts.addUser(testuser2);

        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(accounts);

    }

    @BeforeEach
    public void initFields() {
        changePasswordButton = lookup("#changebutton").query();
        firstPasswordField = lookup("#newPassword").query();
        secondPasswordField = lookup("#confirmNewPessword").query();
        nameDisplay = lookup("#navnDisplay").query();
        emailDisplay = lookup("#epostDisplay").query();
        idDisplay = lookup("#idDisplay").query();

    }

    @Test
    public void checkCorrectInfoShown() {
        String name = user.getFirstname() + " " + user.getLastname();
        String id = Integer.toString(user.getEmployeeNumber());
        Assertions.assertEquals(name, nameDisplay.getText());
        Assertions.assertEquals(user.getEmail(), emailDisplay.getText());
        Assertions.assertEquals(id, idDisplay.getText());
    }

    @Test
    public void testNewPassword() throws IOException {
        String newPassword = "Seran123!";
        writeInPasswordFields("Seran123!", "Seran123!");
        clickOn(changePasswordButton);
        SCP.setSaveFile("Accounts.json");
        Accounts account = SCP.loadAccounts();
        AbstractUser user = account.getUser(emailDisplay.getText(), newPassword);
        
        try {
            String password = encryptDecrypt.decrypt(user.getPassword(), user.getFirstname());
            Assertions.assertEquals(newPassword, password);
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        alertDialogPopsUp("Password changed!");
    }

    @Test
    public void testInvalidPassword() {
        writeInPasswordFields("Seran123!", "Test123!");
        clickOn(changePasswordButton);
        alertDialogPopsUp("Passwords does not match");
        Assertions.assertThrows(RuntimeException.class, () -> {
            writeInPasswordFields("Seran123!", "Test123!");
            clickOn(changePasswordButton);
        });

    }


    private void writeInPasswordFields(String pwdfirst, String pwdsecond) {
        clickOn(firstPasswordField).write(pwdfirst);
        clickOn(secondPasswordField).write(pwdsecond);
    }

    private void alertDialogPopsUp(final String expectedContent) {
        final Stage actualAlertDialog = getTopModalStage();
        Assertions.assertNotNull(actualAlertDialog);

        final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
        assertEquals(expectedContent, dialogPane.getContentText());
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

