package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public AnchorPane dashboardContext;
    public Label txtDateTime;

    public void initialize(URL location, ResourceBundle resources) {
        // Set the current date and time
        updateDateTime();
    }

    public void bookCategoryOnAction(ActionEvent actionEvent)throws IOException {
        setUI("BookCategoryForm");
    }

    public void bookOnAction(ActionEvent actionEvent) {
        setUI("BooksForm");
    }

    public void memberOnAction(ActionEvent actionEvent) {
        setUI("MemberForm");
    }

    public void issueBookOnAction(ActionEvent actionEvent) {
        setUI("IssueBookForm");
    }

    public void returnBookOnAction(ActionEvent actionEvent) {
        setUI("ReturnBook");
    }

    public void statsOnAction(ActionEvent actionEvent) {
        setUI("StatisticForm");
    }

    private void setUI(String location){
        try {
            dashboardContext.getChildren().clear();
            dashboardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void updateDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm \tyyyy-MM-dd ");
        String formattedDateTime = now.format(formatter);
        // Set the formatted date and time to the label
        txtDateTime.setText(formattedDateTime);
    }

}
