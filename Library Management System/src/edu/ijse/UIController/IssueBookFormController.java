package edu.ijse.UIController;

import edu.ijse.dto.BookDto;
import edu.ijse.dto.IssueBookDto;
import edu.ijse.dto.MemberDto;
import edu.ijse.dto.ReturnBookDto;
import edu.ijse.service.custom.impl.BookServiceImpl;
import edu.ijse.service.custom.impl.IssueBookServiceImpl;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import edu.ijse.service.custom.impl.ReturnBookServiceImpl;
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
    public TextField txtStatus;

    private IssueBookServiceImpl issueBookService = new IssueBookServiceImpl();
    private BookServiceImpl bookService = new BookServiceImpl();
    private MemberServiceImpl memberService = new MemberServiceImpl();
    private ObservableList<IssueBookDto> issueBookList = FXCollections.observableArrayList();
    private ReturnBookServiceImpl returnBookService = new ReturnBookServiceImpl();

    public void initialize() throws Exception {
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
        //statusOnAction();

        issueBookTable.setItems(issueBookList);
        issueBookTable.setOnMouseClicked(this::selectValue);
    }

    public void searchBookOnAction(ActionEvent actionEvent) {
        try {

            String bookId = txtBookId.getText();
            BookDto book = bookService.get(bookId);

            if (book == null) {
                new Alert(Alert.AlertType.WARNING, "Book not found").show();
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

    public void issueBookOnAction(ActionEvent actionEvent) {

        if(!txtMemberDetails.getText().equalsIgnoreCase("Member not found..!")) {
            try {

                if (isValidField()){

                    String bookId = txtBookId.getText();
                    String memberId = txtMemberId.getText();

                    LocalDate issueDate = txtIssueDate.getValue();
                    LocalDate dueDate = txtDueDate.getValue();

                    IssueBookDto issueBook = new IssueBookDto(
                            txtIssueId.getText(),
                            txtBookId.getText(),
                            txtBookDetails.getText(),
                            memberId,
                            txtMemberDetails.getText(),
                            issueDate,
                            dueDate
                    );

                    String result = issueBookService.save(issueBook);
                    if("Success".equals(result)) {

                        BookDto book = bookService.get(bookId);
                        if(book != null && "yes".equalsIgnoreCase(book.getAvailable())) {
                            book.setAvailable("no");
                            bookService.update(book);
                        }

                        issueBookList.add(issueBook);
                        issueBookTable.setItems(issueBookList);
                        clearFields();
                        new Alert(Alert.AlertType.INFORMATION, "Book issued successfully..!").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Failed to add book..!").show();
                    }

                }

            }catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.WARNING, "Error in issuing book").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please enter valid details").show();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {

        if(!txtMemberDetails.getText().equalsIgnoreCase("Member not found..!")) {
            try {

                if (isValidField()){

                    String bookId = txtBookId.getText();
                    String memberId = txtMemberId.getText();

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

                    String result = issueBookService.update(issueBook);
                    if("Success".equals(result)) {
                        loadIssueBook();
                        clearFields();
                        new Alert(Alert.AlertType.INFORMATION, "Book successfully updated..!").show();
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Failed to update book..!").show();
                    }

                }

            }catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error in update issue book..!").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Please enter valid details").show();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {

        String issueId = txtIssueId.getText();
        String bookId = txtBookId.getText();
        try {

            String result = issueBookService.delete(issueId);
            if("Success".equals(result)) {
                BookDto book = bookService.get(bookId);
                if(book != null && "no".equalsIgnoreCase(book.getAvailable())) {
                    book.setAvailable("yes");
                    bookService.update(book);
                }
                loadIssueBook();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Book successfully deleted").show();
            }

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to delete book..!").show();
        }

    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchOnAction(ActionEvent actionEvent) {

        try {

            String issueId = txtSearch.getText();

            if (!issueId.isEmpty()) {
                IssueBookDto issueBook = issueBookService.get(issueId);
                if (issueBook != null) {
                    issueBookList.clear();
                    issueBookList.add(issueBook);
                    issueBookTable.setItems(issueBookList);
                }else {
                    new Alert(Alert.AlertType.WARNING, "No issue book found with ID "+issueId).show();
                    loadIssueBook();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Please enter Issue book ID to search").show();
                loadIssueBook();
            }

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Issuing book search Failed..!").show();
        }

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

    private boolean isValidField(){

        if(txtIssueId.getText().trim().equalsIgnoreCase("IB-")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid issue ID number..!").show();
            return false;
        }else if(txtBookId.getText().trim().equalsIgnoreCase("B-")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid book ID number..!").show();
            return false;
        }else if(txtMemberId.getText().trim().equalsIgnoreCase("M-")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid book member ID number..!").show();
            return false;
        } else if (txtIssueDate.getValue()==null || txtDueDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid date..!").show();
            return false;
        } else if (txtBookDetails.getText().isEmpty() || txtMemberDetails.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please search book or member existing..!").show();
            return false;
        }
        return true;
    }

//    public void statusOnAction() throws Exception {
//
//        String issueBookId = txtIssueId.getText();
//
//        IssueBookDto issueBook = issueBookService.get(issueBookId);
//        ReturnBookDto returnBook = returnBookService.get(issueBookId);
//
//        if (issueBook != null && returnBook != null) {
//
//            if (issueBook.getIssueId().equals(returnBook.getIssueId())) {
//                txtStatus.setText("returned.!");
//            }else {
//                txtStatus.setText("not returned.!");
//            }
//        }
//    }
}
