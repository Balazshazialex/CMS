package sample;

import Controllers.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    UserController userController;

    @FXML
    Button loginButton;
    @FXML
    Button exitButton;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
    }

    public void loginButtonClick() {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        if(this.userController.check_creds(username, password)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/LoginForm.fxml"));
            try {
                Parent parent = loader.load();
                loginButton.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid username or password !", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
