package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.core.UserValidation;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

public class LoginController {

    @FXML private TextField email;
    @FXML private TextField password;
    @FXML private Button logIn;
    @FXML private Text errorDisplay;

    private AbstractUser user;
    private UserValidation userval = new UserValidation();
    private SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();

    @FXML
    void initialize() throws IOException {
        createTestUser();
    }


    @FXML
    void userLogIn(ActionEvent event) throws IOException {
        String usernameField = email.getText();
        String passwordField = password.getText();
        Accounts accounts;
        accounts = SCP.loadAccounts();

        try {
            userval.checkValidEmail(usernameField);
            userval.checkValidPassword(passwordField);
            userval.isNotExistingUser(usernameField, passwordField, accounts);
            userval.isValidLogIn(usernameField, passwordField, accounts);
            user = accounts.getUser(usernameField, passwordField);
            if (user instanceof User) {
                switchtoHomepageScene(event);
            }
            else {
                switchToAdminScene(event);
            }
        }
        catch (IllegalArgumentException e) {
            errorDisplay.setText(e.getMessage());
        }
    }

    private void switchToAdminScene(ActionEvent event) {
        Accounts accounts;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root = fxmlLoader.load();
            AdminController adminController = fxmlLoader.getController();
            adminController.setAdminUser((AdminUser) user);
            accounts = SCP.loadAccounts();
            adminController.setAccounts(accounts);
            adminController.loadInfo();
            adminController.loadListView();
            ((AdminUser) user).addObserver(accounts);
            Scene homepageScene = new Scene(root);
            Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
            window.setScene(homepageScene);
            window.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void switchtoHomepageScene(ActionEvent event) {
        Accounts accounts;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = fxmlLoader.load();
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUser((User) user);
            accounts = SCP.loadAccounts();
            homepageController.setAccounts(accounts);
            homepageController.loadInfo();
            ((User) user).addObserver(accounts);
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
        User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
        AdminUser testuser2 = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");

        Accounts acc = new Accounts();
        acc.addUser(testuser1);
        acc.addUser(testuser2);

        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(acc);
    }

}
