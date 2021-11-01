package salarychecker.ui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;

import java.io.IOException;

public abstract class AbstractController {

  protected AbstractUser user = new User();
  protected Accounts accounts = new Accounts();

  public enum CONTROLLERS {
    LOGIN("LogIn.fxml", new LoginController()),
    ADMIN("Admin.fxml", new AdminController()),
    HOME("HomePage.fxml", new HomepageController()),
    PROFILE("Profile.fxml", new ProfileController()),
    SALARIES("Salaries.fxml", new SalariesController()),
    SALARYCALC("SalaryCalculation.fxml", new SalaryCalculationController()),
    SETTINGS("Settings.fxml", new SettingsController());

    private final String fxml;
    private final AbstractController abstractController;

    CONTROLLERS(String fxml, AbstractController abstractController) {
      this.fxml = fxml;
      this.abstractController = abstractController;
    }

    public AbstractController getControllerInstance() {
      return this.abstractController;
    }

    public String getFXMLString() {
      return this.fxml;
    }
  }

  public void setUser(AbstractUser user){
    this.user = user;
  }

  public void setAccounts(Accounts accounts){
    this.accounts = accounts;
  }

  public void setScene(CONTROLLERS type, Event event, AbstractUser user, Accounts accounts) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    try {
      AbstractController controller = type.getControllerInstance();
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      loader.setLocation(AbstractController.class.getResource(type.getFXMLString()));
      controller.setUser(user);
      controller.setAccounts(accounts);
      Parent parent = loader.load();
      if (controller instanceof HomepageController) {
        ((HomepageController) controller).loadInfo();
      }
      else if (controller instanceof  AdminController){
        ((AdminController) controller).loadInfo();
      }
      Scene newScene = new Scene(parent);
      stage.setScene(newScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setAnchorPane(CONTROLLERS type, AnchorPane pane, AbstractUser user, Accounts accounts){
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
      if (controller instanceof ProfileController){
        ((ProfileController) controller).loadProfileInfo();
      }
      else if (controller instanceof SettingsController){
        ((SettingsController) controller).loadSettingsInfo();
      }
      else if (controller instanceof SalaryCalculationController){
        ((SalaryCalculationController) controller).setUserAndAccounts();
      }
      else if (controller instanceof SalariesController){
        ((SalariesController) controller).loadTableView();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
