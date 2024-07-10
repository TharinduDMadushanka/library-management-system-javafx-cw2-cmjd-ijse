package edu.ijse.UIController;

import edu.ijse.db.DBConnection;
import edu.ijse.dto.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Hyperlink signupLink;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        Optional<Admin> selectAdmin = DBConnection.userTable.stream().filter(e -> e.getUsername().equals(userName)).findFirst();

        if (selectAdmin.isPresent()) {
            if(password.equals(selectAdmin.get().getPassword())) {
                new Alert(Alert.AlertType.INFORMATION, "Login Successful").show();
                setUI("MainForm");
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").showAndWait();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid User").showAndWait();
        }

    }

    public void signupOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
