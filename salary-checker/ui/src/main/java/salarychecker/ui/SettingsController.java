package salarychecker.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.core.UserValidation;
import salarychecker.json.SalaryCheckerPersistence;

/**
 * This is the class that controls the Settings-scene.
 * We have methods that changes the profile information of a specific user.
 */
public class SettingsController {

  private User user = new User();
  private Accounts accounts = new Accounts();
  private final UserValidation userValidation = new UserValidation();
  private final SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

  //FXML VARIABLES
  @FXML private TextField changeFirstNameField;
  @FXML private TextField changeLastNameField;
  @FXML private TextField changeEmailField;
  @FXML private TextField changeConfirmedEmailField;
  @FXML private TextField changeEmployerField;
  @FXML private TextField changeConfirmedEmployerField;
  @FXML private TextField hourWageField;
  @FXML private TextField changePasswordField;
  @FXML private TextField changeConfirmedPasswordField;
  @FXML private TextField changeTaxBracketField;
  @FXML private TextField changeEmployeeNumberField;
  @FXML private Text successMessageDisplay;
  @FXML private Text errorTextDisplay;

  /**
   * This method is a setter for the user.
   * We do this to have control of what user wants to change their information.
   *
   * @param user that needs to be set for the settings scene
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * This method is a setter for the accounts.
   *
   * @param accounts that needs to be set for the settings scene.
   */
  public void setAccounts(Accounts accounts) {
    this.accounts = accounts;
  }

  /**
   * Method that is called from HomepageController.
   * We use this method to load existing user information as a prompt text to Text Fields.
   */
  public void loadInfo() {
    changeFirstNameField.setPromptText(user.getFirstname());
    changeLastNameField.setPromptText(user.getLastname());
    changeEmailField.setPromptText(user.getEmail());
    changeConfirmedEmailField.setPromptText(user.getEmail());
    changeEmployerField.setPromptText(user.getEmployerEmail());
    changeConfirmedEmployerField.setPromptText(user.getEmployerEmail());
    hourWageField.setPromptText(String.valueOf(user.getHourRate()));
    changeTaxBracketField.setPromptText(String.valueOf(user.getTaxCount()));
    changeEmployeeNumberField.setPromptText(String.valueOf(user.getEmployeeNumber()));
  }

  /**
   * This is a method that saves the information that is changed.
   * If the field is empty nothing happens to that variable.
   * The form uses uservalidation to validate, if something is written.
   *
   * @param event when user clicks on 'Lagre endringer'
   */
  @FXML
  public void saveChangesAction(ActionEvent event) {
    try {
      if (!(changeFirstNameField.getText().equals("")
          && changeLastNameField.getText().equals(""))) {
        user.setFirstname(changeFirstNameField.getText());
        user.setLastname(changeLastNameField.getText());
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(changeFirstNameField);
        clearFields(changeLastNameField);
      }

      if (!(changeEmailField.getText().equals("")
          && changeConfirmedEmailField.getText().equals(""))) {
        userValidation.isEqualEmail(changeEmailField.getText(),
            changeConfirmedEmailField.getText());
        user.setEmail(changeEmailField.getText());
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(changeEmailField);
        clearFields(changeConfirmedEmailField);
      }

      if (!(changeEmployerField.getText().equals("")
          && changeConfirmedEmployerField.getText().equals(""))) {
        userValidation.isEqualEmail(changeEmployerField.getText(),
            changeConfirmedEmployerField.getText());
        user.setEmployerEmail(changeEmployerField.getText());
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(changeEmployerField);
        clearFields(changeConfirmedEmployerField);
      }

      if (!(hourWageField.getText().equals(""))) {
        user.setHourRate(Double.parseDouble(hourWageField.getText()));
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(hourWageField);
      }

      if (!(changePasswordField.getText().equals("")
          && changeConfirmedPasswordField.getText().equals(""))) {
        userValidation.isEqualPassword(changePasswordField.getText(),
            changeConfirmedPasswordField.getText());
        user.setPassword(changePasswordField.getText());
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(changePasswordField);
        clearFields(changeConfirmedPasswordField);
      }

      if (!(changeTaxBracketField.getText().equals(""))) {
        user.setTaxCount(Double.parseDouble(changeTaxBracketField.getText()));
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(changeTaxBracketField);
      }

      if (!(changeEmployeeNumberField.getText().equals(""))) {
        user.setEmployeeNumber(Integer.parseInt(changeEmployeeNumberField.getText()));
        errorTextDisplay.setText(null);
        successMessageDisplay.setText("Changes successfully saved.");
        clearFields(changeEmployeeNumberField);
      }
      persistence.setSaveFile("Accounts.json");
      persistence.saveAccounts(accounts);
      loadInfo();
    } catch (IllegalArgumentException e) {
      errorTextDisplay.setText(e.getMessage());
      successMessageDisplay.setText(null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Helper method to clear fields.
   *
   * @param wantedField the field that needs to be cleared.
   */
  void clearFields(TextField wantedField) {
    wantedField.clear();
  }

  /**
   * Method that closes the scene and goes back to HomePage.fxml with updated user information.
   *
   * @param event when clicked on 'Lukk'
   */
  @FXML
  public void closeButtonAction(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
      Parent root = fxmlLoader.load();
      HomepageController homepageController = fxmlLoader.getController();
      homepageController.setUser((User) user);
      homepageController.setAccounts(accounts);
      homepageController.loadInfo();
      Scene homepageScene = new Scene(root);
      Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
      window.setScene(homepageScene);
      window.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}