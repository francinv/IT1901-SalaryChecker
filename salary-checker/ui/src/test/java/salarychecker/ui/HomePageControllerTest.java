package salarychecker.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;
import salarychecker.ui.AbstractController.CONTROLLERS;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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


    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    User user;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("views/HomePage.fxml"));
        HomepageController homepageController = new HomepageController();
        loader.setController(homepageController);
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        createTestUsers();
        homepageController.setUser(user);
        homepageController.setAccounts(persistence.loadAccounts());
        homepageController.loadInfo();
        stage.setScene(scene);
        stage.show();
    }

    private void createTestUsers() throws IOException {
        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "29059848796", 34567, "employeer2@gmail.com", 23.0, 130.0);

        Accounts accounts = new Accounts();
        accounts.addUser(testuser1);
        accounts.addUser(testuser2);

        persistence.setFilePath("Accounts.json");
        persistence.saveAccounts(accounts);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/LogIn.fxml")); // load same anchorpane that currentWindow contains
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

