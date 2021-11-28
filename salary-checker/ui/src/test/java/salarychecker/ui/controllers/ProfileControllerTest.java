package salarychecker.ui.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
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
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

public class ProfileControllerTest extends ApplicationTest{

    private Text navnDisplay;
    private Text epostDisplay;
    private Text idDisplay;
    private Text birthdayDisplay;
    private Text taxDisplay;
    private Text salaryDisplay;
    private Text employerEmailDisplay;
    private Button changeProfileButton;

    SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();
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
      FXMLLoader loader = new FXMLLoader();
      ProfileController controller = new ProfileController();
      loader.setController(controller);
      controller.setDataAccess(dataAccess);
      loader.setLocation(SalaryCheckerApp.class.getResource("views/Profile.fxml"));
      createTestUsers();
      user = (User) dataAccess.userLogin("ola@live.no", "Password123!");
      final Parent parent = loader.load();
      controller.loadProfileInfo();
      stage.setScene(new Scene(parent));
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
            System.out.println(e.getMessage());
        }
    }

}
