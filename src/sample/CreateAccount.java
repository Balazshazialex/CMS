package sample;

import Controllers.UserController;
import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccount implements Initializable {
    private UserController userController;

    @FXML
    private Label selectedRole;
    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private TextField password;

    public void displaySelectedRole(ActionEvent event) {
        this.selectedRole.setText(((MenuItem)event.getSource()).getText());
    }

    public void createAccount() {
        String username = this.username.getText();
        String name = this.name.getText();
        String password = this.password.getText();
        String role = this.selectedRole.getText();
        int id = this.userController.getNextId();
        ConferenceParticipant participant = new ConferenceParticipant(id, name, username, password);
        participant.setRole(role);
        this.userController.add(participant);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
    }
}
