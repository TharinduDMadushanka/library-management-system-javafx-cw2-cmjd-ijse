package edu.ijse.UIController;

import edu.ijse.db.DBConnection;
import edu.ijse.dto.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Hyperlink signupLink;
    public AnchorPane loginContext;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Username or password cannot be empty").showAndWait();
            return;
        }

        Optional<Admin> selectAdmin = DBConnection.userTable.stream()
                .filter(e -> e.getUsername().equals(userName))
                .findFirst();

        if (selectAdmin.isPresent()) {
            if (password.equals(selectAdmin.get().getPassword())) {
                new Alert(Alert.AlertType.INFORMATION, "Login Successful").show();
                setUI("MainForm");
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid User").showAndWait();
        }
    }

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        if (loginContext == null) {
            System.err.println("Login context is null");
            return;
        }
        Stage stage = (Stage) loginContext.getScene().getWindow();
        if (stage == null) {
            System.err.println("Stage is null");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/ijse/view/" + location + ".fxml"));
        if (loader.getLocation() == null) {
            System.err.println("FXML file not found: " + location);
            return;
        }
        stage.setScene(new Scene(loader.load()));
        stage.show();
        stage.centerOnScreen();
    }
}
