package edu.ijse.UIController;

import edu.ijse.dto.IssueBookDto;
import edu.ijse.dto.MemberDto;
import edu.ijse.service.custom.impl.IssueBookServiceImpl;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    private IssueBookServiceImpl issueBookService = new IssueBookServiceImpl();
    private MemberServiceImpl memberService = new MemberServiceImpl();

    public void searchIssueBookOnAction(ActionEvent actionEvent) throws Exception {

        try {

            String issueBookId = txtIssueId.getText();
            IssueBookDto issueBook = issueBookService.get(issueBookId);

            if (issueBook == null) {
                new Alert(Alert.AlertType.INFORMATION,"Issue Book not found..!");
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
                new Alert(Alert.AlertType.INFORMATION,"Member not found..!");
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
    }
}
