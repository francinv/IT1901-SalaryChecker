package salarychecker.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import salarychecker.core.Accounts;
import salarychecker.core.User;
import salarychecker.core.UserSale;

import java.util.ArrayList;

public class SalariesController extends AbstractController{

  @FXML private TableView<UserSale> salariesTable;
  @FXML private TableColumn<UserSale, String> periodColumn;
  @FXML private TableColumn<UserSale, Double> paidColumn;
  @FXML private TableColumn<UserSale, Double> expectedColumn;
  @FXML private TableColumn<UserSale, Double> differenceColumn;
  @FXML private Text pageTitle;
  @FXML private AnchorPane salariesPane;

  private User user = (User) super.user;
  private Accounts accounts = super.accounts;
  private ArrayList<UserSale> tempdata;

  protected void loadTableView(){
    user = (User) super.user;
    accounts = super.accounts;
    tempdata = user.getUserSaleList();
    if (!tempdata.isEmpty()) {
      updateTableView();
    }
  }

  /**
   * This method updates the table view.
   * It iterates through tempdata, and adds it to the tableview.
   */
  void updateTableView() {
    salariesTable.getItems().clear();
    periodColumn.setCellValueFactory(new PropertyValueFactory<UserSale, String>("salesperiod"));
    paidColumn.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("expected"));
    expectedColumn.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("paid"));
    differenceColumn.setCellValueFactory(new PropertyValueFactory<UserSale, Double>("difference"));

    for (UserSale userSale : tempdata) {
      salariesTable.getItems().add(userSale);
    }
  }

  // @FXML
  // private void goToCalcAction(ActionEvent event){
  //   pageTitle.setText("Utregning av lønn");
  //   setAnchorPane(CONTROLLERS.SALARYCALC, salariesPane, user, accounts);
  // }

  // @FXML
  // private void goToProfileAction(ActionEvent event){
  //   pageTitle.setText("Profil");
  //   setAnchorPane(CONTROLLERS.PROFILE, salariesPane, user, accounts);
  // }

}
