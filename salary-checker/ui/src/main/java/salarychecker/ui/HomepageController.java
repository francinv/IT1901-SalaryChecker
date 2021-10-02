package salarychecker.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    Alert a = new Alert(Alert.AlertType.NONE);

    /*
    * Object of CSV Reader
    * */
    CSVReader csvReader = new CSVReader();


    User user = new User();
    Accounts existingaccounts = new Accounts();


    public void loadInfo() {
        navnDisplay.setText(user.getFirstname()+ " " + user.getLastname());
        epostDisplay.setText(user.getEmail());
        idDisplay.setText(String.valueOf(user.getEmployeeNumber()));
    }

    /*
    * calling the method to read and print the csv data
    * */
    @FXML
    private void readCSV(){
        csvReader.readCSV();
        csvReader.printCSV();
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
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Passwords does not match");
            a.show();
            throw new IllegalArgumentException("Passwords does not match.");
        }
    }

    private void changePasswordPersistence(String email, String password) throws IOException {
        existingaccounts.updatePassword(email, password);
        SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(existingaccounts);
        Success();
        newPassword.clear();
        confirmNewPessword.clear();
    }

    private void Success() {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("Password changed!");
        a.show();
    }

    public void setUser(User user) {
        this.user=user;
    }

    public void setAccounts(Accounts accounts) {
        this.existingaccounts = accounts;
    }

    public void setRecord(String f1, String f2, String f3, String f4,
        String f5, String f6, String f7, String f8) {
        
        this.f1 = new SimpleStringProperty(f1);
        this.f2 = new SimpleStringProperty(f2);
        this.f3 = new SimpleStringProperty(f3);
        this.f4 = new SimpleStringProperty(f4);
        this.f5 = new SimpleStringProperty(f5);
        this.f6 = new SimpleStringProperty(f6);
        this.f7 = new SimpleStringProperty(f7);
        this.f8 = new SimpleStringProperty(f8);
    }
}
