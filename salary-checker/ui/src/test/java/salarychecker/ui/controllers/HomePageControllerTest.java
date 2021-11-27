package salarychecker.ui.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.json.SalaryCheckerPersistence;
import salarychecker.ui.SalaryCheckerApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageControllerTest extends ApplicationTest {

    //HOMEPAGE - VARIABLES
    private Text pageTitle;
    private Button hideMenuButton;
    private Text userNameDisplay;
    private AnchorPane menuNav;
    private Button logOutButton;
    private Button profileButton;
    private Button salaryCalculationButton;
    private Button mySalariesButton;
    private AnchorPane profilePane;
    private AnchorPane calculationPane;
    private AnchorPane salariesPane;


    SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();
    User user;

    @Override
    public void start(final Stage stage) throws Exception {
      FXMLLoader loader = new FXMLLoader();
      HomepageController controller = new HomepageController();
      loader.setController(controller);
      controller.setDataAccess(dataAccess);
      loader.setLocation(SalaryCheckerApp.class.getResource("views/HomePage.fxml"));
      createTestUsers();
      user = (User) dataAccess.userLogin("testhome@live.no", "Password123!");
      final Parent parent = loader.load();
      controller.loadInfo();
      stage.setScene(new Scene(parent));
      stage.show();
    }

    private void createTestUsers() throws IOException {
        try {
            dataAccess.createUser(new User("Test", "User",
                "testhome@live.no", "Password123!", "22030191349",
                12345, "employeer1@gmail.com", 30.0, 130.0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @BeforeEach
    public void initFields() {
        pageTitle = lookup("#pageTitle").query();
        hideMenuButton = lookup("#hideMenuButton").query();
        userNameDisplay = lookup("#userNameDisplay").query();
        menuNav = lookup("#menuNav").query();
        logOutButton = lookup("#logOutButton").query();
        profileButton = lookup("#profileButton").query();
        salaryCalculationButton = lookup("#salaryCalculationButton").query();
        mySalariesButton = lookup("#mySalariesButton").query();
    }

    @Test
    public void checkCorrectInfoShown() {
        String name = user.getFirstname() + " " + user.getLastname();
        assertEquals(name, userNameDisplay.getText());
        assertEquals("Hjem", pageTitle.getText());
    }

    @Test
    public void checkMenuHideShow(){
        assertEquals("Skjul Meny", hideMenuButton.getText());
        assertTrue(menuNav.isVisible());
        clickOn(hideMenuButton);
        assertEquals("Vis Meny", hideMenuButton.getText());
        assertFalse(menuNav.isVisible());
        clickOn(hideMenuButton);
        assertEquals("Skjul Meny", hideMenuButton.getText());
        assertTrue(menuNav.isVisible());
    }

    @Test 
    public void testGoToProfile(){
        clickOn(profileButton);
        profilePane = lookup("#profilePane").query();
        assertEquals("Profil", pageTitle.getText());
        assertTrue(profilePane.isVisible());
    }

    @Test
    public void testGoToCalc(){
        clickOn(salaryCalculationButton);
        calculationPane = lookup("#calculationPane").query();
        assertEquals("Utregning av lønn", pageTitle.getText());
        assertTrue(calculationPane.isVisible());
    }

    @Test
    public void testGoToSal(){
        clickOn(mySalariesButton);
        salariesPane = lookup("#salariesPane").query();
        assertEquals("Mine lønninger", pageTitle.getText());
        assertTrue(salariesPane.isVisible());
    }
    @Test
    public void testlogOut(){
        clickOn(logOutButton);
        Window currentWindow = window(getTopModalStage().getScene());
        try {
            FXMLLoader loader = new FXMLLoader(SalaryCheckerApp.class.getResource("views/LogIn.fxml")); // load same anchorpane that currentWindow contains
            LoginController loginController = new LoginController();
            loader.setController(loginController);
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

