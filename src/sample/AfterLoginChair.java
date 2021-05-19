package sample;

import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginChair extends AfterLoginPCMember implements Initializable {
    private ConferenceParticipant c;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
            this.bidProposalsButton.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
