package sample;

import Controllers.ConferenceController;
import Controllers.PCMembersController;
import Controllers.ProposalController;
import Controllers.UserController;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.PCMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UploadInfo implements Initializable {
    private ConferenceController conferenceController = new ConferenceController();
    private UserController userController = new UserController();
    private PCMembersController pcMembersController = new PCMembersController();

    @FXML
    private TextField name;

    @FXML
    private TextField affiliation;

    @FXML
    private TextField webpage;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField id;

    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goBack() {
        String nextScreen = "/sample/AfterLoginPCMember.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            this.backButton.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void submit() {
        Integer id = idToInt(this.id.getText());
        String username = this.username.getText();
        String password = this.password.getText();
        String name = this.name.getText();

        if (id != null && isConferenceValid(id) && isPCMemberValid(username, password, id)) {
            // CAST EXCEPTION
            ConferenceParticipant pcMember = userController.findOne(username,password);
            pcMember.uploadPersonalInformation(name,affiliation.getText(),username,webpage.getText());
            userController.update(pcMember);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Operation successful", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public Integer idToInt(String idAsText) {
        try {
            return Integer.parseInt(idAsText);
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid conference ID (must be a number)", ButtonType.OK);
            alert.showAndWait();
            return null;
        }
    }

    public boolean isConferenceValid(Integer id) {
        if(conferenceController.findOne(id) == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Conference does not exist", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean isPCMemberValid(String username, String password, Integer conferenceId) {
        ConferenceParticipant member = userController.findOne(username, password);

        if (member == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Wrong credentials", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else
            if (!pcMembersController.exists(conferenceId, member.getId())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Access denied for the chosen conference", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
            return true;
    }
}
