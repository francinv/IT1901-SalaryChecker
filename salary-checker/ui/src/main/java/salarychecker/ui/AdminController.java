package salarychecker.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import salarychecker.core.AbstractUser;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController {

    AdminUser adminUser = new AdminUser();
    Accounts accounts = new Accounts();
    SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
    ObservableList<String> nameOfUsers = FXCollections.observableArrayList();

    @FXML private Text adminName;
    @FXML private TextField createFirstNameField;
    @FXML private TextField createLastNameField;
    @FXML private TextField createEmailField;
    @FXML private TextField createPasswordField;
    @FXML private TextField createEmployeeNumberField;
    @FXML private TextField createSocialNumberField;
    @FXML private TextField createTaxField;
    @FXML private TextField createWageField;
    @FXML private Text errorMessageDisplay;
    @FXML private ListView<String> userList;

    @FXML
    private void initialize(){
        loadListView();
    }

    private void loadListView() {
        List<AbstractUser> tempuserlist = new ArrayList<AbstractUser>();
        tempuserlist = accounts.getAccounts().stream().filter(u-> u instanceof User).collect(Collectors.toList());
        for (AbstractUser u : tempuserlist) {
            String name = u.getFirstname() + " " + u.getLastname();
            nameOfUsers.add(name);
        }
        userList.setItems(nameOfUsers);
    }

    public void loadInfo(){
        String name = adminUser.getFirstname() + " " + adminUser.getLastname();
        adminName.setText(name);
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    @FXML
    private void createUserAction(ActionEvent event) throws IOException {
        if (createFirstNameField.getText().equals("") || createLastNameField.getText().equals("") || createEmailField.getText().equals("") || createPasswordField.getText().equals("") || createEmployeeNumberField.getText().equals("") || createSocialNumberField.getText().equals("") || createTaxField.getText().equals("") || createWageField.getText().equals("")){
            errorMessageDisplay.setText("All fields must be filled out.");
        }
        String firstname = createFirstNameField.getText();
        String lastname = createLastNameField.getText();
        String email = createEmailField.getText();
        String password = createPasswordField.getText();
        int employeenumber = Integer.parseInt(createEmployeeNumberField.getText());
        String socialnumber = createSocialNumberField.getText();
        Double taxcount = Double.valueOf(createTaxField.getText());
        Double hourwage = Double.valueOf(createWageField.getText());

        try {
            adminUser.setAccounts(accounts);
            adminUser.createUser(firstname, lastname, email, password, socialnumber, employeenumber, adminUser.getEmail(), taxcount, hourwage);
        }
        catch (IllegalArgumentException e){
            errorMessageDisplay.setText(e.getMessage());
        }
        createFirstNameField.clear();
        createLastNameField.clear();
        createEmailField.clear();
        createPasswordField.clear();
        createEmployeeNumberField.clear();
        createSocialNumberField.clear();
        createTaxField.clear();
        createWageField.clear();
        loadListView();
        SCP.setSaveFile("Accounts.json");
        SCP.saveAccounts(accounts);

    }

    @FXML
    private void logOutAction(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            Parent root = fxmlLoader.load();
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
