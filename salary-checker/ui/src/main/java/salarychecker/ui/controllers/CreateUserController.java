package salarychecker.ui.controllers;

import java.io.IOException;
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
import salarychecker.dataaccess.SalaryCheckerAccess;
import salarychecker.json.SalaryCheckerPersistence;

/**
 * This is the class for controlling CreateUser scene.
 */
public class CreateUserController extends AbstractController {

  private AdminUser adminUser;
  private Accounts accounts;
  private SalaryCheckerAccess dataAccess;

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

  /**
   * This is the method that both loads users and sets the first visible scene.
   * The method is protected because it will be called from AbstractController.
   */
  protected void loadUserAndAccount() {
    adminUser = (AdminUser) super.user;
    accounts = super.accounts;
    dataAccess = super.dataAccess;
    empemailPane.setVisible(false);
    socialPassPane.setVisible(false);
    wageTaxPane.setVisible(false);
    goBackButton.setVisible(false);
    createUserButton.setVisible(false);
  }

  /**
   * This is the method that sets new AnchorPane when user clicks on "->".
   * The method checks what AnchorPane is visible and validates using
   * UserValidation(). If somethings is throw this will be displayed in
   * 'errorMessageDisplay'. After validation is run it sets new AnchorPane.
   *
   * @param event when user clicks on '->'.
   */
  @FXML
  private void goFurtherAction(ActionEvent event) {
    if (firstLastPane.isVisible()) {
      try {
        UserValidation.checkValidFirstname(nameField.getText());
        firstname = nameField.getText();
        UserValidation.checkValidLastname(lastNameField.getText());
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
        if (!employeeid.isEmpty()) {
          UserValidation.checkValidEmployeeNumber(Integer.parseInt(employeeid));
          employeeNumber = Integer.parseInt(employeeid);
        } else {
          UserValidation.checkValidEmployeeNumber(employeeNumber);
        }
        UserValidation.checkValidEmail(emailField.getText());
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
        UserValidation.checkValidSocialNumber(socialField.getText());
        socialNumber = socialField.getText();
        String temppassword = passwordField.getText();
        String confirm = confirmPasswordField.getText();
        UserValidation.isEqualPassword(temppassword, confirm);
        UserValidation.checkValidPassword(temppassword);
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

  /**
   * This is the method that creates the user and adds it to "Accounts.json"
   * It checks if the last AnchorPane is visible and takes these arguments and validates
   * them before creating User. The method use both UserValidation() and AdminUser().
   *
   * @param event when user clicks on "Opprett bruker".
   * @throws IOException if something goes wrong when saving to Accounts.json.
   */
  @FXML
  private void createUserAction(ActionEvent event) throws IOException {
    SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();
    if (wageTaxPane.isVisible()) {
      try {
        String tempwage = wageField.getText();
        wage = 0.0;
        if (! tempwage.isEmpty()) {
          UserValidation.checkValidHourRate(Double.parseDouble(wageField.getText()));
          wage = Double.parseDouble(tempwage);
        } else {
          UserValidation.checkValidHourRate(wage);
        }
        String temptax = taxField.getText();
        tax = 0.0;
        if (! temptax.isEmpty()) {
          UserValidation.checkValidTaxCount(Double.parseDouble(taxField.getText()));
          tax = Double.parseDouble(temptax);
        } else {
          UserValidation.checkValidTaxCount(tax);
        }
        User user = new User(firstname, lastname, email, password,
            socialNumber, employeeNumber, adminUser.getEmail(), tax, wage);
        dataAccess.createUser(user);
        errorMessageDisplay.setFill(Paint.valueOf("#008000"));
        errorMessageDisplay.setText("User created!");
      } catch (IllegalArgumentException e) {
        errorMessageDisplay.setText(e.getMessage());
      }
    }
  }

  /**
   * This is the method for handling "<-". It checks what AnchorPane is visible and
   * sets new AnchorPane based on this.
   *
   * @param event when user clicks on "<-".
   */
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


  /**
   * This is a helper method, for setting layout of AnchorPane that will be shown.
   *
   * @param pane that needs to change layout.
   */
  private void setLayout(AnchorPane pane) {
    pane.setLayoutX(40.0);
    pane.setLayoutY(370.0);
  }

}
