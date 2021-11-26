package salarychecker.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.dataaccess.SalaryCheckerAccess;

/**
 * This is the class that controls the HomePage Scene.
 */
public class HomepageController extends AbstractController {

  @FXML private Text pageTitle;
  @FXML private Text userNameDisplay;
  @FXML private AnchorPane menuNav;
  @FXML private AnchorPane startPane;
  @FXML private Button hideMenuButton;

  private User user;
  private Accounts accounts;
  private SalaryCheckerAccess dataAccess;

  /**
   * This is a method that loads the user info.
   * The method is protected because it will be
   * called outside this class.
   */
  @FXML
  protected void loadInfo() {
    user = (User) super.user;
    accounts = super.accounts;
    dataAccess = super.dataAccess;
    pageTitle.setText("Hjem");
    userNameDisplay.setText(user.getFirstname() + " " + user.getLastname());
  }

  /**
   * This method is used to log out a user and send them back to the Login-scene.
   *
   * @param event when user clicks on 'Logg ut'
   */
  @FXML
  private void logOutAction(ActionEvent event) {
    setScene(Controllers.LOGIN, event, null, null, null);
  }

  /**
   * This method is used to hide/show menu on left-side.
   *
   * @param event when user clicks on "Skjul meny"/"Vis meny"
   */
  @FXML
  private void hideMenuAction(ActionEvent event) {
    if (menuNav.isVisible()) {
      menuNav.setVisible(false);
      hideMenuButton.setText("Vis Meny");
    } else {
      menuNav.setVisible(true);
      hideMenuButton.setText("Skjul Meny");
    }
  }

  /**
   * Method that sends the user to Profile scene.
   *
   * @param event when user clicks on "Profil" in menu.
   */
  @FXML
  private void goToProfileAction(ActionEvent event) {
    pageTitle.setText("Profil");
    setAnchorPane(Controllers.PROFILE, startPane, user, accounts, dataAccess);
  }

  /**
   * Method that sends the user to Calculation scene.
   *
   * @param event when user clicks on "Lønnsutregninger".
   */
  @FXML
  private void goToCalcAction(ActionEvent event) {
    pageTitle.setText("Utregning av lønn");
    setAnchorPane(Controllers.SALARYCALC, startPane, user, accounts, dataAccess);
  }

  /**
   * Method that sends the user to Salaries scene.
   *
   * @param event when user clicks on "Mine lønninger"
   */
  @FXML
  private void goToSalAction(ActionEvent event) {
    pageTitle.setText("Mine lønninger");
    setAnchorPane(Controllers.SALARIES, startPane, user, accounts, dataAccess);
  }


}
