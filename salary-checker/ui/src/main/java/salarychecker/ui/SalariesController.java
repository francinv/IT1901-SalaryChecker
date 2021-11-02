package salarychecker.ui;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import salarychecker.core.User;
import salarychecker.core.UserSale;

/**
 * This is a class for the controller that handles
 * the Salaries scene.
 */
public class SalariesController extends AbstractController {

  @FXML private TableView<UserSale> salariesTable;
  @FXML private TableColumn<UserSale, String> periodColumn;
  @FXML private TableColumn<UserSale, Double> paidColumn;
  @FXML private TableColumn<UserSale, Double> expectedColumn;
  @FXML private TableColumn<UserSale, Double> differenceColumn;
  @FXML private Text pageTitle;
  @FXML private AnchorPane salariesPane;

  private User user;
  private ArrayList<UserSale> tempdata;

  /**
   * Method for loading TableView with UserSale.
   * This method is protected because it will be called when
   * scene is loaded.
   */
  protected void loadTableView() {
    user = (User) super.user;
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
    periodColumn.setCellValueFactory(new PropertyValueFactory<>("salesperiod"));
    paidColumn.setCellValueFactory(new PropertyValueFactory<>("expected"));
    expectedColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));
    differenceColumn.setCellValueFactory(new PropertyValueFactory<>("difference"));

    for (UserSale userSale : tempdata) {
      salariesTable.getItems().add(userSale);
    }
  }


}
