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
  User creatinguser;

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

  private String firstname;
  private String lastname;
  private double wage;
  private double tax;
  private String password;
  private int employeeNumber;
  private String email;
  private String socialNumber;

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
        firstname = nameField.getText();
        lastname = lastNameField.getText();
        firstLastPane.setVisible(false);
        setLayout(empemailPane);
        empemailPane.setVisible(true);
        goBackButton.setVisible(true);
        errorMessageDisplay.setText("");
      } catch (IllegalArgumentException e) {
        errorMessageDisplay.setText(e.getMessage());
      }
    } else if (empemailPane.isVisible()) {
      try {
        String employeeid = employerIdField.getText();
        employeeNumber = 0;
        if (!employeeid.isEmpty()){
          employeeNumber = Integer.parseInt(employeeid);
        }
        email = emailField.getText();
        empemailPane.setVisible(false);
        setLayout(socialPassPane);
        socialPassPane.setVisible(true);
        errorMessageDisplay.setText("");
      } catch (IllegalArgumentException e) {
        errorMessageDisplay.setText(e.getMessage());
      }
    } else if (socialPassPane.isVisible()) {
      try {
        socialNumber = socialField.getText();
        String temppassword = passwordField.getText();
        String confirm = confirmPasswordField.getText();
        userValidation.isEqualPassword(temppassword, confirm);
        password = temppassword;
        socialPassPane.setVisible(false);
        setLayout(wageTaxPane);
        wageTaxPane.setVisible(true);
        createUserButton.setVisible(true);
        goOnButton.setVisible(false);
        errorMessageDisplay.setText("");
      } catch (IllegalArgumentException e) {
        errorMessageDisplay.setText(e.getMessage());
      }
    }
  }

  @FXML
  private void createUserAction(ActionEvent event) throws IOException {
    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    if (wageTaxPane.isVisible()) {
      try {
        String tempwage = wageField.getText();
        String temptax = taxField.getText();
        double wage = 0.0;
        double tax = 0.0;
        if (! tempwage.isEmpty()) {
          wage = Double.parseDouble(tempwage);
        }
        if (! temptax.isEmpty()) {
          tax = Double.parseDouble(temptax);
        }
        adminUser.setAccounts(accounts);
        adminUser.createUser(firstname, lastname, email, password,
        socialNumber, employeeNumber, adminUser.getEmail(), tax, wage);
        errorMessageDisplay.setFill(Paint.valueOf("#008000"));
        errorMessageDisplay.setText("User created!");
      } catch (IllegalArgumentException e) {
        errorMessageDisplay.setText(e.getMessage());
      }
      persistence.setFilePath("Accounts.json");
      persistence.saveAccounts(accounts);
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

}
