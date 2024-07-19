package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainContext;
    public Button btnLogOut;
    public Label txtTitle;

    public void initialize() {
        setContext("Dashboard");
        //setTitle();
    }

    public void bookCategoryOnAction(ActionEvent actionEvent) {
        setContext("BookCategoryForm");
    }

    public void bookOnAction(ActionEvent actionEvent) {
        setContext("BooksForm");
    }

    public void memberOnAction(ActionEvent actionEvent) {
        setContext("MemberForm");
    }

    public void issueBookOnAction(ActionEvent actionEvent) {
        setContext("IssueBookForm");
    }

    public void returnBookOnAction(ActionEvent actionEvent) {
        setContext("ReturnBook");
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    public void statisticOnAction(ActionEvent actionEvent) {
        setContext("StatisticForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage)btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();

    }

    private void setContext(String location){
        try {
            mainContext.getChildren().clear();
            mainContext.getChildren().add(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    private void setTitle(String title){
//
//        String titleName = "Welcome"+title;
//        txtTitle.setText(titleName);
//    }
}
