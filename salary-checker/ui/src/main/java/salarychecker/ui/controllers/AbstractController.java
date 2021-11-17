package salarychecker.ui.controllers;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.ui.SalaryCheckerApp;


/**
 * This is an abstract class that handles setting scenes and
 * setting AnchorPane. The abstract class is used to minimize copies
 * of methods.
 */
public abstract class AbstractController {

  protected AbstractUser user = new User();
  protected Accounts accounts = new Accounts();


  /**
   * Enum for the controllers. We have defined controllers and fxml-files.
   * The enum also contains get methods for both controller-instance and
   * fxml-string.
   */
  public enum CONTROLLERS {
    LOGIN("LogIn.fxml", new LoginController()),
    ADMIN("AdminStartPage.fxml", new AdminStartPageController()),
    HOME("HomePage.fxml", new HomepageController()),
    PROFILE("Profile.fxml", new ProfileController()),
    SALARIES("Salaries.fxml", new SalariesController()),
    SALARYCALC("SalaryCalculation.fxml", new SalaryCalculationController()),
    SETTINGS("Settings.fxml", new SettingsController()),
    ADMINOVERVIEW("AdminUserOverview.fxml", new AdminUserOverviewController()),
    CREATEUSER("CreateUser.fxml", new CreateUserController());

    private final String fxml;
    private final AbstractController abstractController;

    CONTROLLERS(String fxml, AbstractController abstractController) {
      this.fxml = "/views/" + fxml;
      this.abstractController = abstractController;
    }

    public AbstractController getControllerInstance() {
      return this.abstractController;
    }

    public String getFXMLString() {
      return this.fxml;
    }
  }

  /**
   * Setter method for User. By having this in the AbstractController
   * we do not have to set this every time the scene is changed.
   *
   * @param user that is active.
   */
  public void setUser(AbstractUser user) {
    this.user = user;
  }

  /**
   * Setter method for Accounts. By having this in the AbstractController
   * we do not have to set this every time the scene is changed.
   *
   * @param accounts from persistence.
   */
  public void setAccounts(Accounts accounts) {
    this.accounts = accounts;
  }

  /**
   * Method that set Scene. Based on the event it will switch out with the wanted scene.
   * The method sets controller and location of wanted scene. Further on it also sets
   * both user and accounts. This method is used to log in an user.
   *
   * @param type of wanted scene. Only need to give wanted CONTROLLER type.
   * @param event when user clicks on a button on existing scene.
   * @param user that logs in.
   * @param accounts from persistence.
   */
  public void setScene(CONTROLLERS type, Event event, AbstractUser user, Accounts accounts) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    try {
      AbstractController controller = type.getControllerInstance();
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      System.out.println(type.getFXMLString());
      loader.setLocation(SalaryCheckerApp.class.getResource(type.getFXMLString()));
      controller.setUser(user);
      controller.setAccounts(accounts);
      Parent parent = loader.load();
      if (controller instanceof HomepageController) {
        ((HomepageController) controller).loadInfo();
      } else if (controller instanceof AdminStartPageController) {
        ((AdminStartPageController) controller).loadAdminInfo();
      }
      Scene newScene = new Scene(parent);
      stage.setScene(newScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method that switches out existing AnchorPane with new AnchorPane.
   * Our HomePage.fxml contains Sidebar and Header. To make navigation
   * smoother we have determined to just switch out the content. This method
   * does that by getting the new pane from the given type.
   *
   * @param type of wanted scene. Only need to give wanted CONTROLLER type.
   * @param pane that will be switched out with new AnchorPane.
   * @param user that is logged in.
   * @param accounts from persistence.
   */
  public void setAnchorPane(
      CONTROLLERS type, AnchorPane pane, AbstractUser user, Accounts accounts) {
    try {
      AbstractController controller = type.getControllerInstance();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(AbstractController.class.getResource(type.getFXMLString()));
      loader.setController(controller);
      controller.setUser(user);
      controller.setAccounts(accounts);
      AnchorPane anchorPane = loader.load();
      pane.getChildren().clear();
      pane.getChildren().setAll(anchorPane);
      if (controller instanceof ProfileController) {
        ((ProfileController) controller).loadProfileInfo();
      } else if (controller instanceof SettingsController) {
        ((SettingsController) controller).loadSettingsInfo();
      } else if (controller instanceof SalaryCalculationController) {
        ((SalaryCalculationController) controller).setUserAndAccounts();
      } else if (controller instanceof SalariesController) {
        ((SalariesController) controller).loadTableView();
      } else if (controller instanceof AdminUserOverviewController) {
        ((AdminUserOverviewController) controller).loadTableView();
      } else if (controller instanceof CreateUserController) {
        ((CreateUserController) controller).loadUserAndAccount();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
