package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.EmailSender;
import salarychecker.core.User;

public class HomepageController {

    @FXML private Text navnDisplay;
    @FXML private Text epostDisplay;
    @FXML private Text idDisplay;
    @FXML private Text fDatoDisplay;
    @FXML private Text taxDisplay;
    @FXML private Text hourDisplay;
    @FXML private Text employeDisplay;

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
        taxDisplay.setText(String.valueOf(user.getTaxCount()));
        hourDisplay.setText(String.valueOf(user.getTimesats()));
        employeDisplay.setText(String.valueOf(user.getEmployerEmail()));
        String socialnumber = user.getSocialNumber();
        fDatoDisplay.setText(splitSocialAddDot(socialnumber));
        
    }

    String splitSocialAddDot(String socialnumber){
        String sub = socialnumber.substring(0, 6);
        String newSocial = sub.substring(0,1) +"."+sub.substring(2, 3) + "." +sub.substring(4, 6);
        return newSocial;
    }

    //TODO complete method for sendEmail
    @FXML
    void sendEmail(ActionEvent event) {
        System.out.println("Test");
    }

    //TODO
    @FXML
    void changeProfileSettingsAction(ActionEvent event){
        System.out.println("Test");
    }

    public void setUser(User user) {
        this.user=user;
    }

    public void setAccounts(Accounts accounts) {
        this.existingaccounts = accounts;
    }

}
