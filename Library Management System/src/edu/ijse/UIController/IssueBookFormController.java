package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class IssueBookFormController {
    public AnchorPane issueBookContext;
    public TextField txtIssueId;
    public TextField txtBookId;
    public TextField txtMemberId;
    public TextField txtBookDetails;
    public TextField txtMemberDetails;
    public DatePicker txtIssueDate;
    public DatePicker txtDueDate;
    public TextField txtSearch;
    public TableView issueBookTable;
    public TableColumn colIssueId;
    public TableColumn colBookId;
    public TableColumn colBookDetail;
    public TableColumn colMemberId;
    public TableColumn colMemberDetail;
    public TableColumn colIssueDate;
    public TableColumn colDueDate;

    public void addOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }
}
