package edu.ijse.UIController;

import edu.ijse.db.DBConnection;
import edu.ijse.dto.BookDto;
import edu.ijse.dto.IssueBookDto;
import edu.ijse.dto.MemberDto;
import edu.ijse.dto.ReturnBookDto;
import edu.ijse.service.custom.impl.BookServiceImpl;
import edu.ijse.service.custom.impl.IssueBookServiceImpl;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import edu.ijse.service.custom.impl.ReturnBookServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ReturnBookFormController {
    public AnchorPane returnBookContext;
    public TextField txtIssueDate;
    public TextField txtDueDate;
    public TextField txtMemId;
    public TextField txtName;
    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtMobile;
    public TextField txtGender;
    public TextField txtDob;
    public TextField txtIssueId;
    public TextField txtBookDetails;
    public TextField txtBookId;
    public TextField txtMemberId;
    public TextField txtMemberDetails;
    public DatePicker txtReturnDate;
    public TextField txtFine;
    public TextField returnId;
    public TextField txtReturnId;

    private IssueBookServiceImpl issueBookService = new IssueBookServiceImpl();
    private MemberServiceImpl memberService = new MemberServiceImpl();
    private ReturnBookServiceImpl returnBookService = new ReturnBookServiceImpl();
    private BookServiceImpl bookService = new BookServiceImpl();

    private static int lastReturnId = 0;

    public void initialize(){

        generateIssueBookId();

    }

    public void searchIssueBookOnAction(ActionEvent actionEvent) throws Exception {

        try {

            String issueBookId = txtIssueId.getText();
            IssueBookDto issueBook = issueBookService.get(issueBookId);

            if (issueBook == null) {
                new Alert(Alert.AlertType.WARNING,"Please Enter correct Issue Book ID..!").show();
                return;
            }

            txtBookId.setText(issueBook.getBookId());
            txtBookDetails.setText(issueBook.getBookDetails());
            txtMemberId.setText(issueBook.getMemberId());
            txtMemberDetails.setText(issueBook.getMemberDetails());
            txtIssueDate.setText(issueBook.getIssueDate().toString());
            txtDueDate.setText(issueBook.getDueDate().toString());
            txtMemId.setText(issueBook.getMemberId());

        }catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void searchMemberOnAction(ActionEvent actionEvent) {

        try {

            String memberId = txtMemId.getText();
            MemberDto member = memberService.get(memberId);

            if (member == null) {
                new Alert(Alert.AlertType.WARNING,"Member not found..!").show();
                return;
            }

            txtName.setText(member.getMemberName());
            txtAge.setText(String.valueOf(member.getAge()));
            txtAddress.setText(member.getAddress());
            txtEmail.setText(member.getEmail());
            txtMobile.setText(member.getMobile());
            txtDob.setText(member.getDob().toString());
            txtGender.setText(member.getGender());

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void returnBookOnAction(ActionEvent actionEvent) {

        if (txtIssueId.getText().trim().equalsIgnoreCase("IB-")){
            new Alert(Alert.AlertType.WARNING,"Please enter correct Issue ID number..!").show();
            return;
        } else if (txtReturnId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Please enter correct return ID number..!").show();
            return;
        } else if (txtReturnDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING,"Please enter return date..!").show();
        }

        try {
            LocalDate issueDate = LocalDate.parse(txtIssueDate.getText());
            LocalDate dueDate = LocalDate.parse(txtDueDate.getText());
            LocalDate returnDate = txtReturnDate.getValue();
            String bookId = txtBookId.getText();

            long daysBetween = ChronoUnit.DAYS.between(dueDate, returnDate);
            double fine = 0;

            if (daysBetween>0){
                fine = daysBetween*10;
                txtFine.setText(fine + " $");
            }else {
                txtFine.setText("-");
            }

            String returnId = txtReturnId.getText(); // Assuming txtReturnId is the TextField for returnId
            if (returnId == null || returnId.trim().isEmpty()) {
                returnId = generateNewReturnId(); // Generate a new returnId if it's not provided
            }

            ReturnBookDto returnBook = new ReturnBookDto(
                    returnId,
                    txtIssueId.getText(),
                    txtBookId.getText(),
                    txtBookDetails.getText(),
                    txtMemberId.getText(),
                    txtMemberDetails.getText(),
                    issueDate,
                    dueDate,
                    returnDate,
                    txtFine.getText()
            );

            String result = returnBookService.returnBook(returnBook);
            if (result != null) {

                BookDto book = bookService.get(bookId);
                if(book != null && "no".equalsIgnoreCase(book.getAvailable())) {
                    book.setAvailable("yes");
                    bookService.update(book);
                }

                if (fine>0){
                    new Alert(Alert.AlertType.WARNING,"You extended the return date.You have pay "+fine+" $").showAndWait();
                }

                new Alert(Alert.AlertType.INFORMATION, "Book returned successfully..!").showAndWait();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING,"Failed to return book..!").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Error in returning book..!").show();
        }
    }
    private void clearFields() {
        txtIssueId.clear();
        txtBookId.clear();
        txtBookDetails.clear();
        txtMemberId.clear();
        txtMemberDetails.clear();
        txtIssueDate.clear();
        txtDueDate.clear();
        txtReturnDate.setValue(null);
        txtFine.clear();
        generateIssueBookId();
    }
    private String generateNewReturnId() {
        // Logic to generate a new returnId
        // This could be based on a sequence, UUID, or any other logic you prefer
        return UUID.randomUUID().toString(); // Example using UUID
    }

    private void generateIssueBookId() {
        String issueBookId = "IB-";
        txtIssueId.setText(issueBookId);
    }

}
