package sample;

import Controllers.UserController;
import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
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

        if(!username.contains("@") || !username.contains(".")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username should be an e-mail address !", ButtonType.OK);
            alert.showAndWait();
        } else if(name.length() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Name must have at least 3 characters !", ButtonType.OK);
            alert.showAndWait();
        } else if(password.length() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must have at least 3 characters !", ButtonType.OK);
            alert.showAndWait();
        } else {
            int id = this.userController.getNextId();
            ConferenceParticipant participant = new ConferenceParticipant(id, name, username, password);
            participant.setRole(role);
            this.userController.add(participant);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Account created !", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
    }

    public void goToLogin() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/sample.fxml"));
        try {
            Parent parent = loader.load();
            this.selectedRole.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
