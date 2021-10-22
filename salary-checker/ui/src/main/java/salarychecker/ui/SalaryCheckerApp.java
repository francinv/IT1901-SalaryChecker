package salarychecker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class SalaryCheckerApp extends Application {

    public static Stage stg;

    @Override
    public void start(Stage stage) throws Exception{
        setStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private static void setStage(Stage stage){
        SalaryCheckerApp.stg = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
