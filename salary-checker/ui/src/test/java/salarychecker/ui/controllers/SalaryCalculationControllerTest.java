package salarychecker.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;
import salarychecker.ui.controllers.SalaryCalculationController;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryCalculationControllerTest extends ApplicationTest {

  /*private ComboBox<String> monthDropdown;
  private TextField yearField;
  private TextField hoursField;
  private TextField mobileField;
  private TextField paidField;
  private Button calculateButton;
  private Text expectedText;
  private Text paidText;
  private Text differenceText;

  User user;

  SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("views/SalaryCalculation.fxml"));
    SalaryCalculationController salaryCalculationController = new SalaryCalculationController();
    loader.setController(salaryCalculationController);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);

    user = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
    createTestUsers();
    Accounts accounts = persistence.loadAccounts();
    salaryCalculationController.setUser(user);
    salaryCalculationController.setAccounts(accounts);
    salaryCalculationController.setUserAndAccounts();
    URL url = getClass().getResource("SalesReport.csv");
    File file = new File(url.getFile());
    salaryCalculationController.setURL(file.getAbsolutePath());
    stage.setScene(scene);
    stage.show();
  }

  @BeforeEach
  public void initFields(){
    monthDropdown = lookup("#monthDropdown").query();
    yearField = lookup("#yearField").query();
    hoursField = lookup("#hoursField").query();
    mobileField = lookup("#mobileField").query();
    paidField = lookup("#paidField").query();
    calculateButton = lookup("#calculateButton").query();
    expectedText = lookup("#expectedText").query();
    paidText = lookup("#paidText").query();
    differenceText = lookup("#differenceText").query();
  }
  @Test
  public void testCalc(){
    writeCalculation();
    assertEquals("15169.0", expectedText.getText());
    assertEquals("10000.0", paidText.getText());
    assertEquals("5169.0", differenceText.getText());
  }

  private void writeCalculation(){
    clickOn(monthDropdown);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    clickOn(yearField).write("2021");
    clickOn(hoursField).write("100");
    clickOn(paidField).write("10000");
    clickOn(mobileField).write("5");
    clickOn(calculateButton);
  }



  private void createTestUsers() throws IOException {
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130.0);
    User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "29059848796", 34567, "employeer2@gmail.com", 23.0, 130.0);

    Accounts acc = new Accounts();
    acc.addUser(testuser1);
    acc.addUser(testuser2);

    persistence.setFilePath("Accounts.json");
    persistence.saveAccounts(acc);
  }*/

}
