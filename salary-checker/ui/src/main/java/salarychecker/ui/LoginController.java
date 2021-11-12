package salarychecker.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserValidation;
import salarychecker.json.SalaryCheckerPersistence;

/**
 * Controller class for the Login-scene.
 */
public class LoginController extends AbstractController {

  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private Button createButton;
  @FXML private Text errorDisplay;

  private AbstractUser user = super.user;
  private Accounts accounts;
  private final UserValidation userval = new UserValidation();
  private final SalaryCheckerPersistence persistence = new SalaryCheckerPersistence();

  private SalaryCheckerAccess dataAccess;

  /**
   * We use the initialize method to set saveFile for persistence.
   */
  @FXML
  void initialize() {
    persistence.setFilePath("Accounts.json");
  }

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
    accounts = persistence.loadAccounts();

    try {
      userval.checkValidEmail(usernameField);
      userval.checkValidPassword(passwordField);
      userval.isNotExistingUser(usernameField, passwordField, accounts);
      userval.isValidLogIn(usernameField, passwordField, accounts);
      user = accounts.getUser(usernameField, passwordField);
      if (user instanceof User) {
        setScene(CONTROLLERS.HOME, event, user, accounts);
      } else {
        setScene(CONTROLLERS.ADMIN, event, user, accounts);
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
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no",
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
    AdminUser testuser2 = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com",
        "Vandre333!");

    Accounts acc = new Accounts();
    acc.addUser(testuser1);
    acc.addUser(testuser2);

    persistence.saveAccounts(acc);
    createButton.setText("Test users created!");
    createButton.setTextFill(Paint.valueOf("#008000"));
  }

  protected void setDataAccess(SalaryCheckerAccess dataAccess) {
    this.dataAccess = dataAccess;
  }
}
