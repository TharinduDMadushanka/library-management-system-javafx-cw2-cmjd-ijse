package edu.ijse.UIController;

import edu.ijse.dto.BookDto;
import edu.ijse.dto.IssueBookDto;
import edu.ijse.dto.MemberDto;
import edu.ijse.service.custom.impl.BookServiceImpl;
import edu.ijse.service.custom.impl.IssueBookServiceImpl;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
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
        generateIssueBookId();
        setNewBookId();
        setMemberId();

        issueBookTable.setItems(issueBookList);
        issueBookTable.setOnMouseClicked(this::selectValue);
    }

    public void searchBookOnAction(ActionEvent actionEvent) {
        try {

            String bookId = txtBookId.getText();
            BookDto book = bookService.get(bookId);

            if (book == null) {
                new Alert(Alert.AlertType.ERROR, "Book not found").show();
                return;
            }

            if (!"yes".equalsIgnoreCase(book.getAvailable())) {
                new Alert(Alert.AlertType.INFORMATION, "Book is not available on this time..!").show();
                return;
            }else {
                txtBookDetails.setText(book.getCategoryId()+" | "+book.getTitle());
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchMemberOnAction(ActionEvent actionEvent) throws Exception {

        String memberId = txtMemberId.getText();
        MemberDto member = memberService.get(memberId);

        if (member != null) {
            txtMemberDetails.setText(member.getMemberName());
        }else {
            txtMemberDetails.setText("Member not found..!");
        }

    }

    public void addOnAction(ActionEvent actionEvent) {

        try {

            String bookId = txtBookId.getText();
            String memberId = txtMemberId.getText();

            BookDto book = bookService.get(bookId);
            if (book == null) {
                new Alert(Alert.AlertType.ERROR, "Book not found").show();
                return;
            } else if (!"yes".equalsIgnoreCase(book.getAvailable())) {
                new Alert(Alert.AlertType.INFORMATION, "Book is not available on this time..!").show();
                return;
            }

            MemberDto member = memberService.get(memberId);
            if (member == null) {
                new Alert(Alert.AlertType.ERROR, "Member not found").show();
                return;
            }

            LocalDate issueDate = txtIssueDate.getValue();
            LocalDate dueDate = txtDueDate.getValue();

            IssueBookDto issueBook = new IssueBookDto(
                    txtIssueId.getText(),
                    bookId,
                    txtBookDetails.getText(),
                    memberId,
                    txtMemberDetails.getText(),
                    issueDate,
                    dueDate
            );

            String result = issueBookService.save(issueBook);
            if("Success".equals(result)) {
                issueBookList.add(issueBook);
                issueBookTable.setItems(issueBookList);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Book successfully added").show();
            }

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in issuing book").show();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchOnAction(ActionEvent actionEvent) {

    }

    private void clearFields(){
        generateIssueBookId();
        setNewBookId();
        setMemberId();
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

    private void generateIssueBookId() {
        String issueBookId = "IB-";
        txtIssueId.setText(issueBookId);
    }

    private void setNewBookId() {
        String newBookId = "B-";
        txtBookId.setText(newBookId);
    }

    private void setMemberId() {
        String memberId = "M-";
        txtMemberId.setText(memberId);
    }

}
