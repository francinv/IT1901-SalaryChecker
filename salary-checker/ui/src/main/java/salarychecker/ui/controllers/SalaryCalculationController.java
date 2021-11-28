package salarychecker.ui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.core.UserSale;

/**
 * This is the class for controller that handles the SalaryCalculation-scene.
 */
public class SalaryCalculationController extends AbstractController {

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
  @FXML private Text errorText;
  @FXML private AnchorPane calculationPane;


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
  private void calculateSalary(ActionEvent event) throws IOException {
    try {
      User temp = (User) dataAccess.getLoggedInUser();
      double hours = Double.parseDouble(hoursField.getText());
      int mobileamount = Integer.parseInt(mobileField.getText());
      String chosenmonth = monthDropdown.getSelectionModel().getSelectedItem();
      String salesperiod = chosenmonth + " " + yearField.getText();
      double paid = Double.parseDouble(paidField.getText());
      Calculation calculation = new Calculation(salesperiod, hours, mobileamount, paid);
      getDataAccess().calculateSale(calculation, temp.getEmail());

      UserSale userSale = getDataAccess().getUserSale(salesperiod, temp.getEmail());
      String expected = String.valueOf(userSale.getExpected());
      String displayPaid = String.valueOf(userSale.getPaid()).toString();
      String diff = String.valueOf(userSale.getDifference());
      expectedText.setText(expected);
      paidText.setText(displayPaid);
      differenceText.setText(diff);
    } catch (Exception e) {
      errorText.setText("Something went wrong");
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
  private void uploadAction(ActionEvent event) throws IOException, 
      InterruptedException, URISyntaxException {
    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(stage);
    if (file.exists()) {
      dataAccess.uploadFile(file);
      fileField.setText(file.getName());
    }
  }

  public void setFile(File salaryCsvFile)
      throws InterruptedException, IOException, URISyntaxException {
    getDataAccess().uploadFile(salaryCsvFile);
  }


}
