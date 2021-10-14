package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.core.UserValidation;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

public class LoginController {

    @FXML private TextField email;
    @FXML private TextField password;
    @FXML private Button logIn;

    SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
    public User user;
    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    void initialize() throws IOException {
        createTestUser();
    }


    @FXML
    void userLogIn(ActionEvent event) throws IOException {
        String usernameField = email.getText();
        String passwordField = password.getText();

        UserValidation userval = new UserValidation();

        if (userval.isValidEmail(usernameField) && userval.isValidPassword(passwordField)){
            Accounts accounts = SCP.loadAccounts();
            boolean valid = accounts.checkValidUserLogin(usernameField, passwordField);
            if (valid){
                user = accounts.getUser(usernameField, passwordField);
                System.out.println(user.getFirstname());
                success();
                switchScene(event);
            }
            else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("No user of this kind registered.");
                a.show();
                throw new IllegalStateException("No user of this kind registered.");
            }
        } else {
            pwdemailNValid();
            throw new IllegalArgumentException("Password or e-mail is not valid.");


        }
    }

    private void pwdemailNValid() {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Password or e-mail is not valid.");
        a.show();
    }

    private void success() {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("You are logged in!");
        a.showAndWait();
    }


    private void switchScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = fxmlLoader.load();
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUser(user);
            homepageController.setAccounts(SCP.loadAccounts());
            homepageController.loadInfo();
            Scene homepageScene = new Scene(root);
            Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
            window.setScene(homepageScene);
            window.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createTestUser() throws IOException {
        System.out.println("Creating two test users to show functionality...");

        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "55555555555", 12345, "employeer1@gmail.com", 30.0, 130.0);
        User testuser2 = new User("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!", "222222222222", 34567, "employeer2@gmail.com", 23.0, 130.);

        Accounts accounts = new Accounts();
        accounts.addUser(testuser1);
        accounts.addUser(testuser2);

        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(accounts);

        Accounts accounts2 = SCP.loadAccounts();
        System.out.println("The test users that were added: " + accounts2.getAccounts());
    }

}
