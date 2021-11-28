package salarychecker.ui.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import salarychecker.core.AbstractUser;
import salarychecker.core.User;

/**
 * This is a class controls the AdminUserOverview - scene.
 */
public class AdminUserOverviewController extends AbstractController {

  private List<AbstractUser> tempdata = new ArrayList<>();

  @FXML private TableView<User> tableUsers;
  @FXML private TableColumn<User, String> fnameCol;
  @FXML private TableColumn<User, String> lnameCol;
  @FXML private TableColumn<User, String> emailCol;
  @FXML private TableColumn<User, Integer> idCol;
  @FXML private TextField searchField;


  /**
   * This method updates the table view.
   * It iterates through tempdata, and adds it to the tableview.
   */
  protected void updateTableView() {
    tableUsers.getItems().clear();
    fnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    lnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    idCol.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));

    for (AbstractUser user : tempdata) {
      tableUsers.getItems().add((User) user);
    }
  }

  /**
   * This method is used to load the TableView that displays all the users.
   * This method is protected because the tableview will be loaded as the user
   * enters this scene.
   */
  protected void loadTableView() {
    List<AbstractUser> tempuserlist = getDataAccess().readAccountsWithSameEmployer(
        getDataAccess().getLoggedInUser().getEmail());
    tempdata.clear();
    for (AbstractUser u : tempuserlist) {
      User user = new User(
          u.getFirstname(), u.getLastname(), u.getEmail(), ((User) u).getEmployeeNumber());
      tempdata.add(user);
    }
    updateTableView();
  }

  @FXML
  private void searchAction(ActionEvent event) {
    String wantedUser = searchField.getText();
    tempdata = tempdata
        .stream()
        .filter(u -> u.getFirstname()
            .contains(wantedUser)
            || u.getLastname().contains(wantedUser)
            || u.getEmail().contains(wantedUser)
        )
        .collect(Collectors.toList());
    updateTableView();
  }

  /**
   * Method that clears search-field.
   *
   * @param event when user clicks on "Nullstill"
   */
  @FXML
  private void clearAction(ActionEvent event) {
    searchField.clear();;
  }

}
