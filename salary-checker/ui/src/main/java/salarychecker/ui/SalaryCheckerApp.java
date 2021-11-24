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

  public static Stage stg;

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

  public static void main(String[] args) {
    launch(args);
  }
}
