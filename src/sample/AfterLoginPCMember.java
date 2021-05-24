package sample;

import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginPCMember implements Initializable {
    private ConferenceParticipant c;
    @FXML
    public Button uploadInfoButton;

    @FXML
    public Button evaluatePaperButton;

    @FXML
    public Button bidProposalsButton;

    @FXML
    public Button chatButton;

    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void uploadInfo() {
        String nextScreen = "/sample/UploadInfo.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            UploadInfo scene2Controller = loader.getController();
            scene2Controller.send_message(this.c);
            this.uploadInfoButton.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void logOut() {
        String nextScreen = "/sample/sample.fxml";
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


    public void send_message( ConferenceParticipant conferenceParticipant) {
        this.c = conferenceParticipant;
    }


    public void ReviewPaper(ActionEvent actionEvent) {
        String nextScreen = "/sample/ReviewPaper.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ReviewPaper scene2Controller = loader.getController();
            scene2Controller.send_message(c);
            this.evaluatePaperButton.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void goToBidProposals() {
        String nextScreen = "/sample/ChooseConference_PCMember.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ChooseConference_PCMember scene2Controller = loader.getController();
            scene2Controller.send_message(this.c);
            this.bidProposalsButton.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
