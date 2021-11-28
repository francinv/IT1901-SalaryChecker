package salarychecker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import salarychecker.dataaccess.LocalSalaryCheckerAccess;
import salarychecker.ui.controllers.LoginController;

/**
 * JavaFX App.
 */
public class SalaryCheckerApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    LoginController controller = new LoginController();
    controller.setDataAccess(
        new LocalSalaryCheckerAccess()
    );
    loader.setController(controller);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/LogIn.fxml"));
    final Parent parent = loader.load();
    stage.setScene(new Scene(parent));
    stage.show();

  }

  /**
   * Method used for running headless.
   */
  public static void supportHeadless() {
    if (Boolean.getBoolean("headless")) {
      System.setProperty("testfx.robot", "glass");
      System.setProperty("testfx.headless", "true");
      System.setProperty("prism.order", "sw");
      System.setProperty("prism.text", "t2k");
      System.setProperty("java.awt.headless", "true");
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
