package salarychecker.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.Calculation;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;

/**
 * This is the class that controls the HomePage Scene. The scene contains different tabs.
 */
public class HomepageController {

  @FXML private Text navnDisplay;
  @FXML private Text epostDisplay;
  @FXML private Text idDisplay;
  @FXML private Text birthdayDisplay;
  @FXML private Text taxDisplay;
  @FXML private Text hourDisplay;
  @FXML private Text employeDisplay;

  /*
  buttons to read and calculate salary
  */
  @FXML private Button readButton;
  @FXML private Button calculateButton;

  @FXML private TextField filenameDisplay;
  @FXML private TextField hoursInput;
  @FXML private TextField amountOfMobile;
  @FXML private TextField recievedSalaryInput;
  @FXML private Label salaryLabel;
  @FXML private Label nettoLabel;
  @FXML private Label salaryDiff;
  @FXML private ComboBox<String> monthDropdown;
  @FXML private TextField calculationYearInput;

  @FXML private TableView<UserSale> salaryTableView;
  @FXML private TableColumn<UserSale, String> tableSaleData;
  @FXML private TableColumn<UserSale, Double> paidColTable;
  @FXML private TableColumn<UserSale, Double> expectedColTable;
  @FXML private TableColumn<UserSale, Double> diffColTable;

  private String url;
  private User user = new User();
  private ArrayList<UserSale> tempdata = user.getUserSaleList();
  private Accounts existingaccounts = new Accounts();

  /**
   * This initialize method calls the update Table-View method.
   * It first checks if the list: tempdata is empty.
   */
  @FXML
  private void initialize() {
    if (!tempdata.isEmpty()) {
      updateTableView();
    }
  }

  /**
   * This method updates the table view.
   * It iterates through tempdata, and adds it to the tableview.
   */
  void updateTableView() {
    salaryTableView.getItems().clear();
    tableSaleData.setCellValueFactory(new PropertyValueFactory<UserSale, String>("salesperiod"));
    paidColTable.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("expected"));
    expectedColTable.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("paid"));
    diffColTable.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("difference"));

    for (UserSale userSale : tempdata) {
      salaryTableView.getItems().add(userSale);
    }
  }

  /**
   * This is the method that loads info to the Profile page.
   * This method is public because LoginController calls it when a user logs in.
   */
  public void loadInfo() {
    navnDisplay.setText(user.getFirstname() + " " + user.getLastname());
    epostDisplay.setText(user.getEmail());
    idDisplay.setText(String.valueOf(user.getEmployeeNumber()));
    taxDisplay.setText(String.valueOf(user.getTaxCount()));
    hourDisplay.setText(String.valueOf(user.getHourRate()));
    employeDisplay.setText(String.valueOf(user.getEmployerEmail()));
    String socialnumber = user.getSocialNumber();
    birthdayDisplay.setText(splitSocialAddDot(socialnumber));

  }

  /**
   * This method splits the social number to a prettier birthdate.
   * We do not want to display the social number to screen.
   *
   * @param socialnumber that needs to be changed to a prettier birthdate.
   * @return a new string.
   */
  public String splitSocialAddDot(String socialnumber) {
    String sub = socialnumber.substring(0, 6);
    return sub.substring(0, 2) + "." + sub.substring(2, 4) + "." + sub.substring(4, 6);
  }

  //TODO complete method for sendEmail
  @FXML
  void sendEmail(ActionEvent event) {
    System.out.println("Test");
  }

  /**
   * This is a method that opens a new scene. It opens the Settings-scene.
   * This method also calls some methods in SettingsController:
   * setUser(), setAccounts(), loadInfo() and also adds an Observer.
   *
   * @param event when user clicks on the button 'Endre Profil'
   */
  @FXML
  private void changeProfileSettingsAction(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Settings.fxml"));
      final Parent root = fxmlLoader.load();
      SettingsController settingsController = fxmlLoader.getController();
      settingsController.setUser((User) user);
      settingsController.setAccounts(existingaccounts);
      settingsController.loadInfo();
      user.addObserver(existingaccounts);
      Scene homepageScene = new Scene(root);
      Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
      window.setScene(homepageScene);
      window.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    double hours = Double.parseDouble(hoursInput.getText());
    int mobileamount = Integer.parseInt(amountOfMobile.getText());
    try {
      calculation.doCalculation(getURL(), hours, mobileamount);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String chosenmonth = monthDropdown.getSelectionModel().getSelectedItem();
    String salesperiod = chosenmonth + " " + calculationYearInput.getText();
    Double expectedCalc = Math.round(calculation.getCalculated() * 10) / 10.0;
    userSale.setExpected(expectedCalc);
    userSale.setPaid(Double.parseDouble(recievedSalaryInput.getText()));
    userSale.setDifference();
    userSale.setSalesperiod(salesperiod);

    String expected = String.valueOf(userSale.getExpected());
    String paid = String.valueOf(userSale.getPaid());
    String diff = String.valueOf(userSale.getDifference());
    salaryLabel.setText("Forventet lønn: " + expected);
    nettoLabel.setText("Utbetalt lønn: " + paid);
    salaryDiff.setText("Differanse: " + diff);

    user.addUserSale(userSale);

    tempdata = user.getUserSaleList();

    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    persistence.setFilePath("Accounts.json");
    try {
      persistence.saveAccounts(existingaccounts);
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
    }
    updateTableView();
  }

  /**
   * This is a method that sets the path when someone has clicked the "Last opp Salgsreport"
   * The method opens a FileChooser dialog, and we get the path and name of chosen file.
   * This will then be used to do the calculation.
   *
   * @param event when user clicks on 'Last opp Salgsrapport'.
   */
  @FXML
  void readCSV(ActionEvent event) {
    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(stage);
    setURL(file.getAbsolutePath());
    filenameDisplay.setText(file.getName());
  }

  /**
   * This method is used to log out a user and send them back to the Login-scene.
   *
   * @param event when user clicks on 'Logg ut'
   */
  @FXML
  private void logOutAction(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
      Parent root = fxmlLoader.load();
      Scene homepageScene = new Scene(root);
      Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
      window.setScene(homepageScene);
      window.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setURL(String url) {
    this.url = url;
  }

  public String getURL() {
    return this.url;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setAccounts(Accounts accounts) {
    this.existingaccounts = accounts;
  }
}
