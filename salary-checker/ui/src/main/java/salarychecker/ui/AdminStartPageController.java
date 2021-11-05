package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;

/**
 * This class is a controller for the Admin Scene.
 * It handles user creation and also displays a listview of all the users.
 */
public class AdminStartPageController extends AbstractController {

  private AdminUser adminUser;
  private Accounts accounts;

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
    adminUser = (AdminUser) super.user;
    accounts = super.accounts;
    String name = adminUser.getFirstname() + " " + adminUser.getLastname();
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
    if (menuPane.isVisible()){
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
    setAnchorPane(CONTROLLERS.ADMINOVERVIEW, adminStartPane, adminUser, accounts);
  }

  /**
   * This method switches the exisisting AnchorPane out with Create Users Scene.
   *
   * @param event when user clicks on "Opprett Bruker".
   */
  @FXML
  private void goToNewUAction(ActionEvent event){
    pageTitle.setText("Opprett bruker");
    setAnchorPane(CONTROLLERS.CREATEUSER, adminStartPane, adminUser, accounts);
  }



  /**
   * Handles logout. Calls setScene in AbstractController.
   *
   * @param event that happens when user clicks "Logg ut".
   */
  @FXML
  private void logOutAction(ActionEvent event) {
    setScene(CONTROLLERS.LOGIN, event, null, null);
  }
}
