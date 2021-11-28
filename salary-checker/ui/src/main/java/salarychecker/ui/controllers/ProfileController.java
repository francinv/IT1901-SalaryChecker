package salarychecker.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import salarychecker.core.User;

/**
 * This is the class that handles controller for the Profile-scene.
 */
public class ProfileController extends AbstractController {

  @FXML private Text navnDisplay;
  @FXML private Text epostDisplay;
  @FXML private Text idDisplay;
  @FXML private Text birthdayDisplay;
  @FXML private Text taxDisplay;
  @FXML private Text salaryDisplay;
  @FXML private Text employerEmailDisplay;
  @FXML private AnchorPane profilePane;
  @FXML private Text pageTitle;

  /**
   * This is the method that loads info to the Profile page.
   * This method is protected because AbstractController calls it when a user goes
   * to this scene.
   */
  protected void loadProfileInfo() {
    User user = (User) getDataAccess().getLoggedInUser();
    navnDisplay.setText(user.getFirstname() + " " + user.getLastname());
    epostDisplay.setText(user.getEmail());
    idDisplay.setText(String.valueOf(user.getEmployeeNumber()));
    taxDisplay.setText(String.valueOf(user.getTaxCount()));
    salaryDisplay.setText(String.valueOf(user.getHourRate()));
    employerEmailDisplay.setText(String.valueOf(user.getEmployerEmail()));
    String socialnumber = user.getSocialNumber();
    birthdayDisplay.setText(splitSocialAddDot(socialnumber));
  }

  /**
   * This method splits the social number to a prettier birthdate.
   * We do not want to display the social number to screen.
   *
   * @param socialnumber that needs to be changed to a prettier birthdate.
   * @return a new string.
   */
  public String splitSocialAddDot(String socialnumber) {
    String sub = socialnumber.substring(0, 6);
    return sub.substring(0, 2) + "." + sub.substring(2, 4) + "." + sub.substring(4, 6);
  }

  /**
   * This is a method that opens a new scene. It opens the Settings-scene.
   *
   * @param event when user clicks on the button 'Endre Profil'
   */
  @FXML
  private void changeProfileAction(ActionEvent event) {
    setAnchorPane(Controllers.SETTINGS, profilePane, getDataAccess());
  }
}