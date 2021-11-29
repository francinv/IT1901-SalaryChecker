package salarychecker.ui;

import java.nio.file.Path;
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
    final FXMLLoader loader = new FXMLLoader();
    Path.of(System.getProperty("user.home"), "/.salarychecker/Accounts.json").toFile().delete();
    Path.of(System.getProperty("user.home"), 
        "/.salarychecker/SalarycheckerKeystore.jks").toFile().delete();
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
