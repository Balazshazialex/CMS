package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    Button loginButton;
    @FXML
    Button exitButton;

    public void loginButtonClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/LoginForm.fxml"));
        try {
            Parent parent = loader.load();
            LoginForm nextController = loader.getController();
            loginButton.getScene().setRoot(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
