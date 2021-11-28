package salarychecker.ui.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.core.UserValidation;

/**
 * Controller class for the Login-scene.
 */
public class LoginController extends AbstractController {

  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private Button createButton;
  @FXML private Text errorDisplay;

  /**
   * This is the method that handles the log-in.
   * It first loads the accounts that are saved in "Accounts.json".
   * It checks if the input is valid, then checks if the User exists.
   * If something is wrong it will display it in the UI.
   *
   * @param event when clicked on 'Logg inn'
   * @throws IOException if something goes wrong when reading from file.
   */
  @FXML
  void userLogIn(ActionEvent event) throws IOException {

    String usernameField = email.getText();
    String passwordField = password.getText();
    try {
      UserValidation.checkValidEmail(usernameField);
      UserValidation.checkValidPassword(passwordField);
      UserValidation.isNotExistingUser(usernameField,
          passwordField, getDataAccess().readAccounts());
      UserValidation.isValidLogIn(usernameField, passwordField, getDataAccess().readAccounts());
      AbstractUser user = dataAccess.userLogin(usernameField, passwordField);
      if (user instanceof User) {
        setScene(Controllers.HOME, event, getDataAccess());
      } else if (user instanceof AdminUser) {
        setScene(Controllers.ADMIN, event, getDataAccess());
      }
    } catch (IllegalArgumentException e) {
      errorDisplay.setText(e.getMessage());
    }
  }

  /**
   * Method that creates two test users if the user wants to.
   * This is only for testing purposes.
   *
   * @param event when user clicks "Opprett testbrukere"
   * @throws IOException if something goes wrong when saving users to file.
   */
  @FXML
  private void createUsersAction(ActionEvent event) throws IOException {
    try {
      User user = new User("Ola", "Nordmann",
          "ola@live.no", "Password123!", "22030191349",
          12345, "boss@mail.com", 30.0, 130.0);
      UserSale testsale1 = new UserSale("August 2021", 15643.0, 10000.0);
      user.addUserSale(testsale1);
      UserSale testsale2 = new UserSale("September 2021", 13000.0, 8000.0);
      user.addUserSale(testsale2);
      dataAccess.createUser(user);
      dataAccess.createAdminUser(new AdminUser("Kari", "Nordmann",
          "boss@mail.com", "Password123!"));
      createButton.setText("Test users created!");
    } catch (Exception e) {
      createButton.setText(e.getMessage());
    }
  }
}
