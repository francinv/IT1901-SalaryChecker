package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SalaryCalculationController extends AbstractController{

  @FXML private Button calculateButton;

  @FXML private TextArea fileField;
  @FXML private TextField hoursField;
  @FXML private TextField mobileField;
  @FXML private TextField paidField;
  @FXML private Text expectedText;
  @FXML private Text paidText;
  @FXML private Text differenceText;
  @FXML private ComboBox<String> monthDropdown;
  @FXML private TextField yearField;
  @FXML private Text pageTitle;
  @FXML private AnchorPane calculationPane;

  private User user;
  private Accounts accounts;
  private String url;

  protected void setUserAndAccounts(){
    user = (User) super.user;
    accounts = super.accounts;
  }
  /**
   * This method is used to check the salary.
   * The method use this classes from the core-module:
   * Calculation, UserSale, User, SalaryCheckerPersistence and Accounts.
   * This method also calls a method that sets the path to wanted salesreport.
   * The method will get all the information written in the fields and do a calculation
   * of what the expected salary is. Lastly it will display it, in addition to what is
   * actually paid and the difference.
   *
   * @param event when user clicks on 'Beregn lønn'
   * @throws IOException Signals that an I/O exception of some sort has occurred.
   *                     This class is the general class of exceptions produced by failed
   *                     or interrupted I/O operations.
   */
  @FXML
  void calculateSalary(ActionEvent event) throws IOException {
    UserSale userSale = new UserSale();
    Calculation calculation = new Calculation(user);
    double hours = Double.parseDouble(hoursField.getText());
    int mobileamount = Integer.parseInt(mobileField.getText());
    try {
      calculation.doCalculation(getURL(), hours, mobileamount);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String chosenmonth = monthDropdown.getSelectionModel().getSelectedItem();
    String salesperiod = chosenmonth + " " + yearField.getText();
    Double expectedCalc = Math.round(calculation.getCalculated() * 10) / 10.0;
    userSale.setExpected(expectedCalc);
    userSale.setPaid(Double.parseDouble(paidField.getText()));
    userSale.setDifference();
    userSale.setSalesperiod(salesperiod);

    String expected = String.valueOf(userSale.getExpected());
    String paid = String.valueOf(userSale.getPaid());
    String diff = String.valueOf(userSale.getDifference());
    expectedText.setText(expected);
    paidText.setText(paid);
    differenceText.setText(diff);

    user.addUserSale(userSale);

    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    persistence.setSaveFile("Accounts.json");
    try {
      persistence.saveAccounts(accounts);
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This is a method that sets the path when someone has clicked the "Last opp Salgsreport"
   * The method opens a FileChooser dialog, and we get the path and name of chosen file.
   * This will then be used to do the calculation.
   *
   * @param event when user clicks on 'Last opp Salgsrapport'.
   */
  @FXML
  void uploadAction(ActionEvent event) {
    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(stage);
    setURL(file.getAbsolutePath());
    fileField.setText(file.getName());
  }

  public void setURL(String url) {
    this.url = url;
  }

  public String getURL() {
    return this.url;
  }

  // @FXML
  // private void goToProfileAction(ActionEvent event){
  //   pageTitle.setText("Profil");
  //   setAnchorPane(CONTROLLERS.PROFILE, calculationPane, user, accounts);
  // }

  // @FXML
  // private void goToSalAction(ActionEvent event){
  //   pageTitle.setText("Mine lønninger");
  //   setAnchorPane(CONTROLLERS.SALARIES, calculationPane, user, accounts);
  // }
}
