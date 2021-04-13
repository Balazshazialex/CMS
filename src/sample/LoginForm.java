package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginForm {



    @FXML
    private AnchorPane rootPane;

    public LoginForm() {

    }

    public Pane getRootPane() {
        return rootPane ;
    }
}
