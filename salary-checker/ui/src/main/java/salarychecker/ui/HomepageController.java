package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.EmailSender;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

public class HomepageController {

    @FXML private Text navnDisplay;
    @FXML private Text epostTitle;
    @FXML private Text epostDisplay;
    @FXML private Text idTitle;
    @FXML private Text idDisplay;
    @FXML private TextField newPassword;
    @FXML private TextField confirmNewPessword;
    @FXML private Button changebutton;

    /*
    * buttons to read and calculate salary*/
    @FXML private Button readButton;
    @FXML private Button calculateButton;
    /*
    label to show the calculated salary
    * */
    @FXML private Label salaryLabel;


    /*
    * Object of email sender class
    * */
    EmailSender emailSender = new EmailSender();



    User user = new User();
    Accounts existingaccounts = new Accounts();


    public void loadInfo() {
        navnDisplay.setText(user.getFirstname()+ " " + user.getLastname());
        epostDisplay.setText(user.getEmail());
        idDisplay.setText(String.valueOf(user.getEmployeeNumber()));
    }

    //TODO complete method for sendEmail
    @FXML
    void sendEmail(ActionEvent event) {
        System.out.println("Test");
    }

    @FXML
    void passwordAction(ActionEvent event) throws IOException {
        String password1 = newPassword.getText();
        String password2 = confirmNewPessword.getText();
        String email = epostDisplay.getText();

        if (password1.equals(password2)){
            user.setPassword(password1);
            changePasswordPersistence(email, password1);
        }
        else {
            throw new IllegalArgumentException("Passwords does not match.");
        }
    }

    private void changePasswordPersistence(String email, String password) throws IOException {
        existingaccounts.updatePassword(email, password);
        SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(existingaccounts);
        newPassword.clear();
        confirmNewPessword.clear();
    }


    public void setUser(User user) {
        this.user=user;
    }

    public void setAccounts(Accounts accounts) {
        this.existingaccounts = accounts;
    }

}
