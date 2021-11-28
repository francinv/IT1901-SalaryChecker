package salarychecker.ui.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.stage.Window;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminStartPageControllerTest extends ApplicationTest {

    AdminUser adminUser;
    SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();

    private static Text adminName;
    private static Button logOutButton;
    private Button menuUsersButton;
    private Button menuNewButton;
    private Button hideMenuButton;
    private AnchorPane userOverviewPane;
    private AnchorPane createUserPane;
    private Text pageTitle;
    private AnchorPane menuPane;

    @Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        AdminStartPageController controller = new AdminStartPageController();
        loader.setController(controller);
        controller.setDataAccess(dataAccess);
        loader.setLocation(SalaryCheckerApp.class.getResource("views/AdminStartPage.fxml"));
        createTestUsers();
        adminUser = (AdminUser) dataAccess.userLogin("boss@mail.com", "Vandre333!");
        final Parent parent = loader.load();
        controller.loadAdminInfo();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeEach
    public void initFields(){
        adminName = lookup("#adminName").query();
        logOutButton = lookup("#logOutButton").query();
        hideMenuButton = lookup("#hideMenuButton").query();
        menuUsersButton = lookup("#menuUsersButton").query();
        menuNewButton = lookup("#menuNewButton").query();
        pageTitle = lookup("#pageTitle").query();
        menuPane = lookup("#menuPane").query();
    }

    @Test
    public void correctNameLoaded(){
        String expected = adminUser.getFirstname() + " " + adminUser.getLastname();
        String notexpected = "Seran Shanmugathas";
        assertEquals(expected, adminName.getText());
        assertNotEquals(notexpected, adminName.getText());
        assertEquals("Hjem", pageTitle.getText());
    }

    @Test
    public void testLogOut(){
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

    @Test
    public void testGoToUsersOverview() {
      clickOn(menuUsersButton);
      userOverviewPane = lookup("#userOverviewPane").query();
      assertEquals("Brukere", pageTitle.getText());
      assertTrue(userOverviewPane.isVisible());
    }

    @Test
    public void testGoToCreateUser() {
        clickOn(menuNewButton);
        createUserPane = lookup("#createUserPane").query();
        assertEquals("Opprett bruker", pageTitle.getText());
        assertTrue(createUserPane.isVisible());
    }

    @Test
    public void checkMenuHideShow(){
        assertEquals("Skjul meny", hideMenuButton.getText());
        assertTrue(menuPane.isVisible());
        clickOn(hideMenuButton);
        assertEquals("Vis meny", hideMenuButton.getText());
        assertFalse(menuPane.isVisible());
        clickOn(hideMenuButton);
        assertEquals("Skjul meny", hideMenuButton.getText());
        assertTrue(menuPane.isVisible());
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
