package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainContext;
    public Button btnLogOut;

    public void bookCategoryOnAction(ActionEvent actionEvent) {
    }

    public void bookOnAction(ActionEvent actionEvent) {
    }

    public void memberOnAction(ActionEvent actionEvent) {
    }

    public void borrowingOnAction(ActionEvent actionEvent) {
    }

    public void returnOnAction(ActionEvent actionEvent) {
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
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
}
