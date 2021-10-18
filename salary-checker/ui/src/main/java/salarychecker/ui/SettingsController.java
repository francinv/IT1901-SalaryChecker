package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.core.UserValidation;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;

public class SettingsController {

    User user = new User();
    Accounts accounts = new Accounts();
    UserValidation userValidation = new UserValidation();
    SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();

    //FXML VARIABLES
    @FXML private TextField changeFirstNameField;
    @FXML private TextField changeLastNameField;
    @FXML private TextField changeEmailField;
    @FXML private TextField changeConfirmedEmailField;
    @FXML private TextField changeEmployerField;
    @FXML private TextField changeConfirmedEmployerField;
    @FXML private TextField hourWageField;
    @FXML private TextField changePasswordField;
    @FXML private TextField changeConfirmedPasswordField;
    @FXML private TextField changeTaxBracketField;
    @FXML private TextField changeEmployeeNumberField;
    @FXML private Text successMessageDisplay;
    @FXML private Text errorTextDisplay;


    public void setUser(User user){
        this.user = user;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }


    public void loadInfo() {
        changeFirstNameField.setPromptText(user.getFirstname());
        changeLastNameField.setPromptText(user.getLastname());
        changeEmailField.setPromptText(user.getEmail());
        changeConfirmedEmailField.setPromptText(user.getEmail());
        changeEmployerField.setPromptText(user.getEmployerEmail());
        changeConfirmedEmployerField.setPromptText(user.getEmployerEmail());
        hourWageField.setPromptText(String.valueOf(user.getTimesats()));
        changeTaxBracketField.setPromptText(String.valueOf(user.getTaxCount()));
        changeEmployeeNumberField.setPromptText(String.valueOf(user.getEmployeeNumber()));
    }

    @FXML
    public void saveChangesAction(ActionEvent event){
        try{
            if (!(changeFirstNameField.getText().equals("") && changeLastNameField.getText().equals(""))){
                user.setFirstname(changeFirstNameField.getText());
                user.setLastname(changeLastNameField.getText());
                successMessageDisplay.setText("Changes successfully saved.");
            }

            if(!(changeEmailField.getText().equals("") && changeConfirmedEmailField.getText().equals(""))){
                userValidation.isEqualEmail(changeEmailField.getText(), changeConfirmedEmailField.getText());
                user.setEmail(changeEmailField.getText());
                successMessageDisplay.setText("Changes successfully saved.");
            }

            if(!(changeEmployerField.getText().equals("") && changeConfirmedEmployerField.getText().equals(""))){
                userValidation.isEqualEmail(changeEmployerField.getText(), changeConfirmedEmployerField.getText());
                user.setEmployerEmail(changeEmployerField.getText());
                successMessageDisplay.setText("Changes successfully saved.");
            }

            if(!(hourWageField.getText().equals(""))){
                user.setTimesats(Double.valueOf(hourWageField.getText()));
                successMessageDisplay.setText("Changes successfully saved.");
            }

            if(!(changePasswordField.getText().equals("") && changeConfirmedPasswordField.getText().equals(""))){
                userValidation.isEqualPassword(changePasswordField.getText(), changeConfirmedPasswordField.getText());
                user.setPassword(changePasswordField.getText());
                successMessageDisplay.setText("Changes successfully saved.");
            }

            if(!(changeTaxBracketField.getText().equals(""))){
                user.setTaxCount(Double.valueOf(changeTaxBracketField.getText()));
                successMessageDisplay.setText("Changes successfully saved.");
            }

            if(!(changeEmployeeNumberField.getText().equals(""))){
                user.setEmployeeNumber(Integer.valueOf(changeEmployeeNumberField.getText()));
                successMessageDisplay.setText("Changes successfully saved.");
            }

            SCP.setSaveFile("Accounts.json");
            SCP.saveAccounts(accounts);
        } catch (IllegalArgumentException e){
            errorTextDisplay.setText(e.getMessage());
            successMessageDisplay.setText(null);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeButtonAction(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = fxmlLoader.load();
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUser((User) user);
            homepageController.setAccounts(accounts);
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

}
