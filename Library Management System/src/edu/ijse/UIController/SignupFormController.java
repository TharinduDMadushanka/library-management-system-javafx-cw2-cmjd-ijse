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
import java.util.regex.Pattern;

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

        boolean checkEmail = isValidEmail(txtEmail.getText());

        if (!name.matches("^[^0-9]*$")){ // String validation
            new Alert(Alert.AlertType.WARNING, "Name can't be a number..!", ButtonType.OK).show();
            return;
        }else if (!checkEmail){
            new Alert(Alert.AlertType.WARNING,"Please enter a valid email address.",ButtonType.OK).show();
            return;
        }


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || username.isEmpty()){
           new Alert(Alert.AlertType.WARNING, "Please fill all the fields", ButtonType.OK).show();
        }else {
            DBConnection.userTable.add(
                    new Admin(name,password,email,username)
            );
            new Alert(Alert.AlertType.INFORMATION,"Your Account has been successfully registered!").showAndWait();
            setUI("LoginForm");
        }

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

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).matches();
    }
}
