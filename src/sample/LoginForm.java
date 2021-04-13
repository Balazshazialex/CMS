package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LoginForm {

    @FXML
    private Pane root;

    public LoginForm() {
        this.root = new AnchorPane();
    }

    public Pane getRootPane() {
        return root ;
    }
}
