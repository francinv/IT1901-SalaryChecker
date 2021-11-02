package salarychecker.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

public class ProfileControllerTest extends ApplicationTest{

    private Text navnDisplay;
    private Text epostDisplay;
    private Text idDisplay;
    private Text birthdayDisplay;
    private Text taxDisplay;
    private Text salaryDisplay;
    private Text employerEmailDisplay;
    private Button changeProfileButton;

    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    User user;

    @BeforeEach
    public void initFields(){
        navnDisplay = lookup("#navnDisplay").query();
        epostDisplay = lookup("#epostDisplay").query();
        idDisplay = lookup("#idDisplay").query();
        birthdayDisplay = lookup("#birthdayDisplay").query();
        taxDisplay = lookup("#taxDisplay").query();
        salaryDisplay = lookup("#salaryDisplay").query();
        employerEmailDisplay = lookup("#employerEmailDisplay").query();
        changeProfileButton = lookup("#changeProfileButton").query();
    }

    
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        ProfileController profileController = new ProfileController();
        loader.setController(profileController);
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        createTestUsers();
        profileController.setUser(user);
        profileController.setAccounts(persistence.loadAccounts());
        profileController.loadProfileInfo();
        stage.setScene(scene);
        stage.show();
    }
    
    @Test
    public void correctProfileInfoLoaded(){
        String name = user.getFirstname() + " " + user.getLastname();
        String id = Integer.toString(user.getEmployeeNumber());
        String sub = user.getSocialNumber().substring(0, 6);
        String newSocial = sub.substring(0,2) +"."+sub.substring(2, 4) + "." +sub.substring(4, 6);
        String tax = String.valueOf(user.getTaxCount());
        String hour = String.valueOf(user.getHourRate());
        assertEquals(name, navnDisplay.getText());
        assertEquals(user.getEmail(), epostDisplay.getText());
        assertEquals(id, idDisplay.getText());
        assertEquals(newSocial, birthdayDisplay.getText());
        assertEquals(tax, taxDisplay.getText());
        assertEquals(hour, salaryDisplay.getText());
        assertEquals(user.getEmployerEmail(), employerEmailDisplay.getText());

    }
    @Test
    public void openSettings(){
        clickOn(changeProfileButton);
        AnchorPane settingsPane = lookup("#settingsPane").query();
        assertTrue(settingsPane.isVisible());
    }

    private void createTestUsers() throws IOException {
        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "29059848796", 34567, "employeer2@gmail.com", 23.0, 130.0);

        Accounts accounts = new Accounts();
        accounts.addUser(testuser1);
        accounts.addUser(testuser2);

        persistence.setSaveFile("Accounts.json");
        persistence.saveAccounts(accounts);
    }
}
