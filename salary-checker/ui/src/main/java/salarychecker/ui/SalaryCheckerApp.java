package salarychecker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import salarychecker.core.User;

import java.net.URL;

/**
 * JavaFX App
 */
public class SalaryCheckerApp extends Application {

    public static Stage stg;

    @Override
    public void start(Stage stage) throws Exception{
        SalaryCheckerApp.stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}