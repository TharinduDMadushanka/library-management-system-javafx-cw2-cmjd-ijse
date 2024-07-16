package edu.ijse.entity;

import java.time.LocalDate;

public class ReturnBookEntity {

    private String returnId;
    private String issueId;
    private String bookId;
    private String bookDetails;
    private String memberId;
    private String memberDetails;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String fine;

    public ReturnBookEntity() {
    }

    public ReturnBookEntity(String returnId, String issueId, String bookId, String bookDetails, String memberId, String memberDetails, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, String fine) {
        this.returnId = returnId;
        this.issueId = issueId;
        this.bookId = bookId;
        this.bookDetails = bookDetails;
        this.memberId = memberId;
        this.memberDetails = memberDetails;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(String memberDetails) {
        this.memberDetails = memberDetails;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "ReturnBookEntity{" +
                "returnId=" + returnId +
                ", issueId='" + issueId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookDetails='" + bookDetails + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberDetails='" + memberDetails + '\'' +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fine='" + fine + '\'' +
                '}';
    }
}
