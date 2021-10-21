package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import salarychecker.core.Accounts;
import salarychecker.core.Calculation;
import salarychecker.core.EmailSender;
import salarychecker.core.User;
import salarychecker.core.UserSale;
import salarychecker.json.SalaryCheckerPersistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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


    @FXML private TextField filenameDisplay;
    @FXML private TextField hoursInput;
    @FXML private TextField amountOfMobile;
    @FXML private TextField recievedSalaryInput;
    @FXML private Label salaryLabel;
    @FXML private Label nettoLabel;
    @FXML private Label salaryDiff;
    @FXML private ComboBox<String> monthDropdown;
    @FXML private TextField calculationYearInput;


    @FXML private TableView<UserSale> salaryTableView;
    @FXML private TableColumn tableSaleData;
    @FXML private TableColumn paidColTable;
    @FXML private TableColumn expectedColTable;
    @FXML private TableColumn diffColTable;

    

    /*
    * Object of email sender class
    * */
    EmailSender emailSender = new EmailSender();


    private String url;


    User user = new User();
    ArrayList<UserSale> tempdata = user.getUserSaleList();
    Accounts existingaccounts = new Accounts();

    @FXML
    private void initialize() {
        if(!tempdata.isEmpty()){
            updateTableView();
        }
    }

    void updateTableView() {
        salaryTableView.getItems().clear();
        tableSaleData.setCellValueFactory(new PropertyValueFactory<UserSale, String>("salesperiod"));
        paidColTable.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("expected"));
        expectedColTable.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("paid"));
        diffColTable.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("difference"));

        for (UserSale uSale : tempdata ){
            salaryTableView.getItems().add(uSale);
        }
    }

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
        String newSocial = sub.substring(0,2) +"."+sub.substring(2, 4) + "." +sub.substring(4, 6);
        return newSocial;
    }



    //TODO complete method for sendEmail
    @FXML
    void sendEmail(ActionEvent event) {
        System.out.println("Test");
    }

    @FXML
    private void changeProfileSettingsAction(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Settings.fxml"));
            Parent root = fxmlLoader.load();
            SettingsController settingsController = fxmlLoader.getController();
            settingsController.setUser((User) user);
            settingsController.setAccounts(existingaccounts);
            settingsController.loadInfo();
            user.addObserver(existingaccounts);
            Scene homepageScene = new Scene(root);
            Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
            window.setScene(homepageScene);
            window.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void calculateSalary(ActionEvent event) {
        UserSale userSale = new UserSale();
        Calculation calculation = new Calculation(user);
        String temp = getURL();
        System.out.println("Printer ut url");
        System.out.println(temp);
        double hours = Double.parseDouble(hoursInput.getText());
        int mobileamount = Integer.parseInt(amountOfMobile.getText());
        try {
            calculation.doCalculation(getURL(), hours, mobileamount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String chosenmonth = monthDropdown.getSelectionModel().getSelectedItem();
        String salesperiod = chosenmonth + " " + calculationYearInput.getText();

        userSale.setExpected(calculation.getCalculated());
        userSale.setPaid(Double.parseDouble(recievedSalaryInput.getText()));
        userSale.setDifference();
        userSale.setSalesperiod(salesperiod);

        String expected = String.valueOf(userSale.getExpected());
        String paid = String.valueOf(userSale.getPaid());
        String diff = String.valueOf(userSale.getDifference());
        salaryLabel.setText("Forventet lønn: " + expected);
        nettoLabel.setText("Utbetalt lønn: " + paid);
        salaryDiff.setText("Differanse: " + diff);

        user.addUserSale(userSale);
        
        tempdata = user.getUserSaleList();
        
        SalaryCheckerPersistence SCP = new SalaryCheckerPersistence();
        SCP.setSaveFile("Accounts.json");
        try {
            SCP.saveAccounts(existingaccounts);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        updateTableView();
    }

    @FXML
    void readCSV(ActionEvent event){
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        setURL(file.getAbsolutePath());
        filenameDisplay.setText(file.getName());
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

    public void setURL(String url){
        this.url = url;
    }
    public String getURL() {
        return this.url;
    }

    public void setUser(User user) {
        this.user=user;
    }

    public void setAccounts(Accounts accounts) {
        this.existingaccounts = accounts;
    }

}
