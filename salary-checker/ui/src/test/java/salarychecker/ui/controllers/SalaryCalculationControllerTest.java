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
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryCalculationControllerTest extends ApplicationTest {

  private ComboBox<String> monthDropdown;
  private TextField yearField;
  private TextField hoursField;
  private TextField mobileField;
  private TextField paidField;
  private Button calculateButton;
  private Text expectedText;
  private Text paidText;
  private Text differenceText;

  User user;

  private SalaryCheckerAccess dataAccess = new LocalSalaryCheckerAccess();

  @Override
  public void start(final Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    SalaryCalculationController controller = new SalaryCalculationController();
    loader.setController(controller);
    controller.setDataAccess(dataAccess);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/SalaryCalculation.fxml"));
    createTestUsers();
    user = (User) dataAccess.userLogin("ola@live.no", "Password123!");
    URL url = getClass().getResource("SalesReport.csv");
    File file = new File(url.getFile());
    controller.setFile(file);
    final Parent parent = loader.load();
    stage.setScene(new Scene(parent));
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
    assertEquals("14980.0", expectedText.getText());
    assertEquals("10000.0", paidText.getText());
    assertEquals("4980.0", differenceText.getText());
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
      e.getMessage();
    }
  }
}
