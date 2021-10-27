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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageControllerTest extends ApplicationTest {


    //HOMEPAGE - VARIABLES
    private Text nameDisplay;
    private Text emailDisplay;
    private Text idDisplay;
    private Text fDatoDisplay;
    private Text taxDisplay;
    private Text hourDisplay;
    private Text employeDisplay;
    private Node calcTab;
    private Node salariesTab;
    private ComboBox<String> monthDropdown;
    private TextField calculationYearInput;
    private TextField hoursInput;
    private TextField recievedSalaryInput;
    private TextField amountOfMobile;
    private Button calculateButton;
    private Label salaryLabel;
    private Label nettoLabel;
    private Label salaryDiff;
    private TableView<UserSale> salaryTableView;
    private Button changeProfileSettingsButton;
    private Button logOutUserButton;



    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    User user;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);

        user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
        createTestUsers();
        HomepageController homepageController = loader.getController();
        homepageController.setUser(user);
        homepageController.setAccounts(persistence.loadAccounts());
        homepageController.loadInfo();
        URL url = getClass().getResource("SalesReport.csv");
        File file = new File(url.getFile());
        homepageController.setURL(file.getAbsolutePath());
        stage.setScene(scene);
        stage.show();

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

    @BeforeEach
    public void initFields() {
        nameDisplay = lookup("#navnDisplay").query();
        emailDisplay = lookup("#epostDisplay").query();
        idDisplay = lookup("#idDisplay").query();
        fDatoDisplay = lookup("#fDatoDisplay").query();
        taxDisplay = lookup("#taxDisplay").query();
        hourDisplay = lookup("#hourDisplay").query();
        employeDisplay = lookup("#employeDisplay").query();
        calcTab = lookup(".tab-pane > .tab-header-area > .headers-region > .tab").nth(1).query();
        salariesTab = lookup(".tab-pane > .tab-header-area > .headers-region > .tab").nth(3).query();
        monthDropdown = lookup("#monthDropdown").query();
        calculationYearInput = lookup("#calculationYearInput").query();
        hoursInput = lookup("#hoursInput").query();
        recievedSalaryInput = lookup("#recievedSalaryInput").query();
        amountOfMobile = lookup("#amountOfMobile").query();
        calculateButton = lookup("#calculateButton").query();
        salaryLabel = lookup("#salaryLabel").query();
        nettoLabel = lookup("#nettoLabel").query();
        salaryDiff = lookup("#salaryDiff").query();
        salaryTableView = lookup("#salaryTableView").query();
        changeProfileSettingsButton = lookup("#changeProfileSettingsButton").query();
        logOutUserButton = lookup("#logOutUserButton").query();
    }

    @Test
    public void checkCorrectInfoShown() {
        String name = user.getFirstname() + " " + user.getLastname();
        String id = Integer.toString(user.getEmployeeNumber());
        String sub = user.getSocialNumber().substring(0, 6);
        String newSocial = sub.substring(0,2) +"."+sub.substring(2, 4) + "." +sub.substring(4, 6);
        String tax = String.valueOf(user.getTaxCount());
        String hour = String.valueOf(user.getHourRate());
        assertEquals(name, nameDisplay.getText());
        assertEquals(user.getEmail(), emailDisplay.getText());
        assertEquals(id, idDisplay.getText());
        assertEquals(newSocial, fDatoDisplay.getText());
        assertEquals(tax, taxDisplay.getText());
        assertEquals(hour, hourDisplay.getText());
        assertEquals(user.getEmployerEmail(), employeDisplay.getText());
    }

    @Test
    public void testCalc(){
        writeCalculation();
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        assertEquals("Forventet lønn: 15169.0", salaryLabel.getText());
        assertEquals("Utbetalt lønn: 10000.0", nettoLabel.getText());
        assertEquals("Differanse: 5169.0", salaryDiff.getText());
    }

    @Test
    public void checkIfCalculatedShown(){
        writeCalculation();
        clickOn(salariesTab);
        UserSale userSale = salaryTableView.getItems().get(0);
        assertEquals("Januar 2021", userSale.getSalesperiod());
        assertEquals(15169.0, userSale.getExpected());
        assertEquals(10000.0, userSale.getPaid());
        assertEquals(5169, userSale.getDifference());
    }

    @Test
    public void openSettings(){
        clickOn(changeProfileSettingsButton);
        Window currentWindow = window(getTopModalStage().getScene());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml")); // load same anchorpane that currentWindow contains
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
    public void logOut(){
        clickOn(logOutUserButton);
        Window currentWindow = window(getTopModalStage().getScene());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml")); // load same anchorpane that currentWindow contains
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

    private void writeCalculation(){
        clickOn(calcTab);
        clickOn(monthDropdown);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn(calculationYearInput).write("2021");
        clickOn(hoursInput).write("100");
        clickOn(recievedSalaryInput).write("10000");
        clickOn(amountOfMobile).write("5");
        clickOn(calculateButton);
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

