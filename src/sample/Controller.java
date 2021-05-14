package sample;

import Controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
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
    @FXML
    Label loginRoleLabel;
    @FXML
    MenuItem steeringCommitteeRole;
    @FXML
    MenuItem PCmemberRole;
    @FXML
    MenuItem ChairRole;
    @FXML
    MenuItem ListenerRole;
    @FXML
    MenuItem AuthorRole;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
    }

    public void loginButtonClick() {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        String role = this.loginRoleLabel.getText();
        if(this.userController.checkCreds(username, password,role)) {
            String nextScreen = "/sample/LoginForm.fxml";
            if(role.equals("Steering committee member")) {
                nextScreen = "/sample/AfterLoginSteeringCommitteeMember.fxml";
            } else if(role.equals("PC member")) {
                nextScreen = "/sample/AfterLoginPCMember.fxml";
            } else if(role.equals("Author")) {
                nextScreen = "/sample/AfterLoginAuthor.fxml";
            } else if(role.equals("Chair/ co-chair")) {
                nextScreen = "/sample/AfterLoginChair.fxml";
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                if(role.equals("Author")){
                    AfterLoginAuthor scene2Controller = loader.getController();
                    var message=this.userController.findOne(username,password);
                    scene2Controller.send_message(message);
                }

                loginButton.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid username, password or chosen role !", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void displaySelectedRole(ActionEvent event) {
        this.loginRoleLabel.setText(((MenuItem)event.getSource()).getText());
        this.loginRoleLabel.setTextAlignment(TextAlignment.CENTER);
    }

}
