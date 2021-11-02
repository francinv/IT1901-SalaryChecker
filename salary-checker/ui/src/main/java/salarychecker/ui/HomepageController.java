package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.User;

/**
 * This is the class that controls the HomePage Scene. The scene contains different tabs.
 */
public class HomepageController extends AbstractController {

  @FXML private Text pageTitle;
  @FXML private Text userNameDisplay;
  @FXML private AnchorPane menuNav;
  @FXML private AnchorPane startPane;
  @FXML private Button hideMenuButton;

  private User user;
  private Accounts accounts;

  @FXML
  protected void loadInfo(){
    user = (User) super.user;
    accounts = super.accounts;
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
    setScene(CONTROLLERS.LOGIN, event, null, null);
  }

  @FXML
  private void hideMenuAction(ActionEvent event){
    if (menuNav.isVisible()){
      menuNav.setVisible(false);
      hideMenuButton.setText("Vis Meny");
    } else{
      menuNav.setVisible(true);
      hideMenuButton.setText("Skjul Meny");
    }
  }

  @FXML
  private void goToProfileAction(ActionEvent event){
    pageTitle.setText("Profil");
    setAnchorPane(CONTROLLERS.PROFILE, startPane, user, accounts);
  }

  @FXML
  private void goToCalcAction(ActionEvent event){
    pageTitle.setText("Utregning av lønn");
    setAnchorPane(CONTROLLERS.SALARYCALC, startPane, user, accounts);
  }

  @FXML
  private void goToSalAction(ActionEvent event){
    pageTitle.setText("Mine lønninger");
    setAnchorPane(CONTROLLERS.SALARIES, startPane, user, accounts);
  }


}
