package edu.ijse.UIController;

import edu.ijse.dto.IssueBookDto;
import edu.ijse.service.custom.impl.BookServiceImpl;
import edu.ijse.service.custom.impl.IssueBookServiceImpl;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

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

    public TableView<IssueBookDto> issueBookTable;
    public TableColumn<IssueBookDto, String> colIssueId;
    public TableColumn<IssueBookDto, String> colBookId;
    public TableColumn<IssueBookDto, String> colBookDetail;
    public TableColumn<IssueBookDto, String> colMemberId;
    public TableColumn<IssueBookDto, String> colMemberDetail;
    public TableColumn<IssueBookDto, String> colIssueDate;
    public TableColumn<IssueBookDto, String> colDueDate;

    private IssueBookServiceImpl issueBookService = new IssueBookServiceImpl();
    private BookServiceImpl bookService = new BookServiceImpl();
    private MemberServiceImpl memberService = new MemberServiceImpl();
    private ObservableList<IssueBookDto> issueBookList = FXCollections.observableArrayList();

    public void initialize() {
        colIssueId.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookDetail.setCellValueFactory(new PropertyValueFactory<>("bookDetails"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberDetail.setCellValueFactory(new PropertyValueFactory<>("memberDetails"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        loadIssueBook();
        issueBookTable.setItems(issueBookList);
        issueBookTable.setOnMouseClicked(this::selectValue);
    }

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

    private void clearFields(){
        txtIssueId.clear();
        txtBookId.clear();
        txtMemberId.clear();
        txtBookDetails.clear();
        txtMemberDetails.clear();
        txtIssueDate.setValue(null);
        txtDueDate.setValue(null);
    }

    private void loadIssueBook() {
        try{

            ArrayList<IssueBookDto> issueBooks = issueBookService.getAll();
            if(issueBooks != null){
                issueBookList.setAll(issueBooks);
                issueBookTable.setItems(issueBookList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void selectValue(MouseEvent mouseEvent) {
        IssueBookDto selectedIssueBook = issueBookTable.getSelectionModel().getSelectedItem();
        if(selectedIssueBook != null){
            txtIssueId.setText(selectedIssueBook.getIssueId());
            txtBookId.setText(selectedIssueBook.getBookId());
            txtBookDetails.setText(selectedIssueBook.getBookDetails());
            txtMemberId.setText(selectedIssueBook.getMemberId());
            txtMemberDetails.setText(selectedIssueBook.getMemberDetails());
            txtIssueDate.setValue(selectedIssueBook.getIssueDate());
            txtDueDate.setValue(selectedIssueBook.getDueDate());
        }
    }
}
