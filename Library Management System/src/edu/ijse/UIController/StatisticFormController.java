package edu.ijse.UIController;

import edu.ijse.dto.ReturnBookDto;
import edu.ijse.service.custom.impl.ReturnBookServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class StatisticFormController {
    public AnchorPane statisticContext;
    public TableView<ReturnBookDto> statisticTable;
    public TableColumn<ReturnBookDto,String> colId;
    public TableColumn<ReturnBookDto,String> colIssueID;
    public TableColumn<ReturnBookDto,String> colBookId;
    public TableColumn<ReturnBookDto,String> colBookDetail;
    public TableColumn<ReturnBookDto,String> colMemberId;
    public TableColumn<ReturnBookDto,String> colMemberDetails;
    public TableColumn<ReturnBookDto,String> colIssueDate;
    public TableColumn<ReturnBookDto,String> colReturnDate;
    public TableColumn<ReturnBookDto,String> colDueDate;
    public TableColumn<ReturnBookDto,String> colFine;


    private ReturnBookServiceImpl returnBookServiceImpl =new ReturnBookServiceImpl();
    private ObservableList<ReturnBookDto> returnBookList = FXCollections.observableArrayList();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colIssueID.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookDetail.setCellValueFactory(new PropertyValueFactory<>("bookDetails"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberDetails.setCellValueFactory(new PropertyValueFactory<>("memberDetails"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("fine"));

        loadReturnBooks();
        statisticTable.setItems(returnBookList);
    }

    private void loadReturnBooks() {
        try {
            ArrayList<ReturnBookDto> returnBooks = returnBookServiceImpl.getAll();
            if (returnBooks != null) {
                returnBookList.setAll(returnBooks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBookOnAction(ActionEvent actionEvent) {
        setContext("ReturnBook");
    }

    public void dashboardOnAction(ActionEvent actionEvent) {
        setContext("Dashboard");
    }

    private void setContext(String location){
        try {
            statisticContext.getChildren().clear();
            statisticContext.getChildren().add(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
