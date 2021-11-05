package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserValidation;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

public class CreateUserController extends AbstractController {

  private AdminUser adminUser;
  private Accounts accounts;
  User creatinguser = new User();

  @FXML private AnchorPane firstLastPane;
  @FXML private AnchorPane empemailPane;
  @FXML private AnchorPane socialPassPane;
  @FXML private AnchorPane wageTaxPane;
  @FXML private Button goBackButton;
  @FXML private TextField nameField;
  @FXML private TextField lastNameField;
  @FXML private TextField wageField;
  @FXML private TextField taxField;
  @FXML private TextField confirmPasswordField;
  @FXML private TextField passwordField;
  @FXML private TextField socialField;
  @FXML private TextField emailField;
  @FXML private TextField employerIdField;
  @FXML private Text errorMessageDisplay;
  @FXML private Button goOnButton;
  @FXML private Button createUserButton;

  protected void loadUserAndAccount(){
    adminUser = (AdminUser) super.user;
    accounts = super.accounts;
    empemailPane.setVisible(false);
    socialPassPane.setVisible(false);
    wageTaxPane.setVisible(false);
    goBackButton.setVisible(false);
    createUserButton.setVisible(false);
  }

  @FXML
  private void goFurtherAction(ActionEvent event) {
    UserValidation userValidation = new UserValidation();
    if (firstLastPane.isVisible()) {
      try {
        String firstname = nameField.getText();
        String lastname = lastNameField.getText();
        creatinguser.setFirstname(firstname);
        creatinguser.setLastname(lastname);
        firstLastPane.setVisible(false);
        setLayout(empemailPane);
        empemailPane.setVisible(true);
        goBackButton.setVisible(true);
        errorMessageDisplay.setText("");
      } catch (IllegalArgumentException e) {
        nameField.clear();
        lastNameField.clear();
        errorMessageDisplay.setText(e.getMessage());
      }
    } else if (empemailPane.isVisible()) {
      try {
        String employeeid = employerIdField.getText();
        int employeeID = 0;
        if (!employeeid.isEmpty()){
          employeeID = Integer.parseInt(employeeid);
        }
        String email = emailField.getText();
        creatinguser.setEmployeeNumber(employeeID);
        creatinguser.setEmail(email);
        empemailPane.setVisible(false);
        setLayout(socialPassPane);
        socialPassPane.setVisible(true);
        errorMessageDisplay.setText("");
      } catch (IllegalArgumentException e) {
        emailField.clear();
        employerIdField.clear();
        errorMessageDisplay.setText(e.getMessage());
      }
    } else if (socialPassPane.isVisible()) {
      try {
        String birth = socialField.getText();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();
        creatinguser.setSocialNumber(birth);
        userValidation.isEqualPassword(password, confirm);
        creatinguser.setPassword(password);
        socialPassPane.setVisible(false);
        setLayout(wageTaxPane);
        wageTaxPane.setVisible(true);
        createUserButton.setVisible(true);
        goOnButton.setVisible(false);
        errorMessageDisplay.setText("");
      } catch (IllegalArgumentException e) {
        socialField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        errorMessageDisplay.setText(e.getMessage());
      }
    }
  }

  @FXML
  private void createUserAction(ActionEvent event) throws IOException {
    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    if (wageTaxPane.isVisible()) {
      try {
        String wage = wageField.getText();
        String tax = taxField.getText();
        double tempwage = 0.0;
        double temptax = 0.0;
        if (! wage.isEmpty()) {
          tempwage = Double.parseDouble(wage);
        }
        if (! tax.isEmpty()) {
          temptax = Double.parseDouble(tax);
        }
        creatinguser.setHourRate(tempwage);
        creatinguser.setTaxCount(temptax);
        adminUser.setAccounts(accounts);
        adminUser.createUser(creatinguser);
        errorMessageDisplay.setFill(Paint.valueOf("#008000"));
        errorMessageDisplay.setText("User created!");
      } catch (IllegalArgumentException e) {
        wageField.clear();
        taxField.clear();
        errorMessageDisplay.setText(e.getMessage());

      }
      persistence.setFilePath("Accounts.json");
      persistence.saveAccounts(accounts);
      creatinguser = new User();
    }
  }

  @FXML
  private void goBackAction(ActionEvent event) {
    if (empemailPane.isVisible()) {
      empemailPane.setVisible(false);
      setLayout(firstLastPane);
      firstLastPane.setVisible(true);
      goBackButton.setVisible(false);
    } else if (socialPassPane.isVisible()) {
      socialPassPane.setVisible(false);
      setLayout(empemailPane);
      empemailPane.setVisible(true);
    } else if (wageTaxPane.isVisible()) {
      wageTaxPane.setVisible(false);
      setLayout(socialPassPane);
      socialPassPane.setVisible(true);
      goOnButton.setVisible(true);
      createUserButton.setVisible(false);
    }
  }


  private void setLayout(AnchorPane pane){
    pane.setLayoutX(40.0);
    pane.setLayoutY(370.0);
  }







  /**
   * This is a method that handles creating User.
   * We use a try-catch, to catch eventual
   *
   * @param event that happens when user clicks on "Opprett bruker".
   * @throws IOException if something goes wrong when reading from file.
   *//*
  @FXML
  private void createUserAction(ActionEvent event) throws IOException {
    String tempemployeeN = createEmployeeNumberField.getText();
    int employeenumber = 0;
    if (!tempemployeeN.isEmpty()) {
      employeenumber = Integer.parseInt(tempemployeeN);
    }
    String socialnumber = createSocialNumberField.getText();
    String temptaxcount = createTaxField.getText();
    double taxcount = 0.0;
    if (!temptaxcount.isEmpty()) {
      taxcount = Double.parseDouble(temptaxcount);
    }
    String temphourwage = createWageField.getText();
    double hourwage = 0.0;
    if (!temphourwage.isEmpty()) {
      hourwage = Double.parseDouble(temphourwage);
    }
    try {
      adminUser.setAccounts(accounts);
      adminUser.createUser(firstname, lastname, email, password,
          socialnumber, employeenumber, adminUser.getEmail(), taxcount, hourwage);
      createFirstNameField.clear();
      createLastNameField.clear();
      createEmailField.clear();
      createPasswordField.clear();
      createEmployeeNumberField.clear();
      createSocialNumberField.clear();
      createTaxField.clear();
      createWageField.clear();
    } catch (IllegalArgumentException e) {
      errorMessageDisplay.setText(e.getMessage());
    }
    persistence.setFilePath("Accounts.json");
    persistence.saveAccounts(accounts);
  }*/
}
