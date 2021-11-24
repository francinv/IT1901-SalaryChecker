package salarychecker.ui;

import java.net.URI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import salarychecker.dataacess.RemoteSalaryCheckerAccess;
import salarychecker.ui.controllers.LoginController;
import salarychecker.ui.controllers.SalaryCheckerConfig;

/**
 * JavaFX App.
 */
public class SalaryCheckerRemoteApp extends Application {

  protected static Stage stg;
  private SalaryCheckerConfig config;

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    LoginController controller = new LoginController();
    controller.setDataAccess(
        new RemoteSalaryCheckerAccess(
                new URI(config.getProperty("serverURI"))
        )
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

