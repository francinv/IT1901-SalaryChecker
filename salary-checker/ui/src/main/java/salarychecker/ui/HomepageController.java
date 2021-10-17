package salarychecker.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
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

import java.io.File;
import java.io.FileNotFoundException;
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


    String url = null; 


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

    //TODO
    @FXML
    void changeProfileSettingsAction(ActionEvent event){
        System.out.println("Test");
    }

    @FXML
    void calculateSalary(ActionEvent event) {
        UserSale userSale = new UserSale();
        Calculation calculation = new Calculation(user);

        double hours = Double.parseDouble(hoursInput.getText());
        int mobileamount = Integer.parseInt(amountOfMobile.getText());
        try {
            calculation.doCalculation(url, hours, mobileamount);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String chosenmonth = monthDropdown.getSelectionModel().getSelectedItem();
        String salesperiod = chosenmonth + " " + calculationYearInput.getText();

        userSale.setExpected(calculation.getCalculated());
        userSale.setPaid(Double.parseDouble(recievedSalaryInput.getText()));
        userSale.setDifference();
        userSale.setSalesperiod(salesperiod);

        salaryLabel.setText(String.valueOf(userSale.getExpected()));
        nettoLabel.setText(String.valueOf(userSale.getPaid()));
        salaryDiff.setText(String.valueOf(userSale.getDifference()));

        user.addUserSale(userSale);
        
        tempdata = user.getUserSaleList();
        updateTableView();
    }

    @FXML
    void readCSV(ActionEvent event){
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        url = file.getAbsolutePath();
        filenameDisplay.setText(file.getName());


    }

    public void setUser(User user) {
        this.user=user;
    }

    public void setAccounts(Accounts accounts) {
        this.existingaccounts = accounts;
    }

}
