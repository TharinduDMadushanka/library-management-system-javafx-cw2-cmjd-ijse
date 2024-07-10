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

public class SignupFormController {
    public AnchorPane signupContext;
    public TextField txtName;
    public PasswordField txtPassword;
    public Button btnSignup;
    public Hyperlink loginLink;
    public TextField txtEmail;
    public TextField txtUsername;

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        String name = txtName.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String username = txtUsername.getText();

        DBConnection.userTable.add(
                new Admin(name,password,email,username)
        );
        new Alert(Alert.AlertType.INFORMATION,"Your Account has been successfully registered!").showAndWait();
        setUI("LoginForm");

    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) signupContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
