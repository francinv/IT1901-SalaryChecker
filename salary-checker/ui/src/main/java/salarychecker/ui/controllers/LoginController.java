package salarychecker.ui.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
import salarychecker.ui.LocalSalaryCheckerAccess;
import salarychecker.ui.RemoteSalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerAccess;

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

  private SalaryCheckerAccess dataAccess;
  private SalaryCheckerConfig config;

    /**
     * Initializes the SalaryCheckerAccess by checking salarychecker.properties. 
     * If the key for remote access is true, the app wil run with RemoteSalaryCheckerAccess, 
     * otherwise LocalSalaryCheckerAccess.
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    void initialize() throws IOException, URISyntaxException {
        this.config = new SalaryCheckerConfig();
        
        if (config.getProperty("remoteAccess").equals("true")) { 

            setDataAccess(
                    new RemoteSalaryCheckerAccess(
                            new URI(config.getProperty("serverURI"))
                    ));

            System.out.println("Using remote endpoint @ " + config.getProperty("serverURI")); 

        } else {
            setDataAccess(new LocalSalaryCheckerAccess());
        }
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
    accounts = dataAccess.readAccounts();

    try {
      userval.checkValidEmail(usernameField);
      userval.checkValidPassword(passwordField);
      userval.isNotExistingUser(usernameField, passwordField, accounts);
      userval.isValidLogIn(usernameField, passwordField, accounts);
      user = dataAccess.userLogin(usernameField, passwordField);
      if (user instanceof User) {
        setScene(CONTROLLERS.HOME, event, user, accounts);
      } else {
        setScene(CONTROLLERS.ADMIN, event, user, accounts);
      }
    } catch (IllegalArgumentException e) {
      errorDisplay.setText(e.getMessage());
    // } catch (Exception e) {
    //   errorDisplay.setText(Errors.NOT_REGISTERED.getMessage());
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
    dataAccess.createUser(new User("Seran", "Shanmugathas", "seran@live.no",
        "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130));
    dataAccess.createUser(new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com",
        "Vandre333!"));

    createButton.setText("Test users created!");
    createButton.setTextFill(Paint.valueOf("#008000"));
  }

  protected void setDataAccess(SalaryCheckerAccess dataAccess) {
    this.dataAccess = dataAccess;
  }
}
