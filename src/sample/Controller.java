package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML
    Button loginButton;
    @FXML
    Button exitButton;

    public void loginButtonClick(){
        LoginForm game = new LoginForm();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.getScene().setRoot(game.getRootPane());
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
