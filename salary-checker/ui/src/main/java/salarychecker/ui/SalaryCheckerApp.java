package salarychecker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import salarychecker.ui.controllers.LoginController;
import salarychecker.ui.controllers.SalaryCheckerAppController;

/**
 * JavaFX App.
 */
public class SalaryCheckerApp extends Application {

  public static Stage stg;

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    SalaryCheckerAppController controller = new SalaryCheckerAppController();
    loader.setController(controller);
    loader.setLocation(SalaryCheckerApp.class.getResource("views/SalaryCheckerApp.fxml"));
    final Parent parent = loader.load();
    controller.setLoginController(loader.getController());
    stage.setScene(new Scene(parent));
    stage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
