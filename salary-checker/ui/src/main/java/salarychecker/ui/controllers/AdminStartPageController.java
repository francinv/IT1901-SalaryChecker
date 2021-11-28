package salarychecker.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * This class is a controller for the Admin Scene.
 * It handles user creation and also displays a listview of all the users.
 */
public class AdminStartPageController extends AbstractController {

  @FXML private Text adminName;
  @FXML private Button hideMenuButton;
  @FXML private Button menuUsersButton;
  @FXML private Button menuNewButton;
  @FXML private AnchorPane menuPane;
  @FXML private AnchorPane adminStartPane;
  @FXML private Text pageTitle;



  /**
   * This is method is used to load info about the admin-user that is logged in.
   * The method is protected because the method will be called as the user logs in.
   */
  protected void loadAdminInfo() {
    String name = getDataAccess().getLoggedInUser().getFirstname()
        + " " + getDataAccess().getLoggedInUser().getLastname();
    adminName.setText(name);
    pageTitle.setText("Hjem");
  }

  /**
   * This method hide and show the menu bar.
   *
   * @param event when user clicks "Skjul Meny"/"Vis meny".
   */
  @FXML
  private void hideAction(ActionEvent event) {
    if (menuPane.isVisible()) {
      hideMenuButton.setText("Vis meny");
      menuPane.setVisible(false);
    } else {
      hideMenuButton.setText("Skjul meny");
      menuPane.setVisible(true);
    }
  }

  /**
   * This method switches the exisisting AnchorPane out with Users Overview Scene.
   *
   * @param event when user clicks on "Brukere".
   */
  @FXML
  private void goToUsersAction(ActionEvent event) {
    pageTitle.setText("Brukere");
    setAnchorPane(Controllers.ADMINOVERVIEW, adminStartPane, getDataAccess());
  }

  /**
   * This method switches the exisisting AnchorPane out with Create Users Scene.
   *
   * @param event when user clicks on "Opprett Bruker".
   */
  @FXML
  private void goToNewUserAction(ActionEvent event) {
    pageTitle.setText("Opprett bruker");
    setAnchorPane(Controllers.CREATEUSER, adminStartPane, getDataAccess());
  }



  /**
   * Handles logout. Calls setScene in AbstractController.
   *
   * @param event that happens when user clicks "Logg ut".
   */
  @FXML
  private void logOutAction(ActionEvent event) {
    setScene(Controllers.LOGIN, event, getDataAccess());
  }
}
