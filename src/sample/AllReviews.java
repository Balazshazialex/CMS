package sample;

import Controllers.ReviewController;
import Model.Review;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.Proposal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllReviews implements Initializable {
    @FXML
    public Text heading_text;
    @FXML
    public ListView reviewsList;
    @FXML
    public Button back;
    private Conference conferene;
    private ConferenceParticipant c;
    private Proposal p;
    private ReviewController reviewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.reviewController = new ReviewController();
    }

    public void setConference(ConferenceParticipant c, Conference conferece, Proposal p) {
        this.c = c;
        this.conferene = conferece;
        this.p = p;
        this.heading_text.setText("All reviews for the proposal: " + this.p.getName());
        load();
    }

    private void load() {
        this.reviewsList.getItems().clear();
        var pid = this.p.getId();
        var bids = reviewController.findAll();
        for (Review value : bids) {
            if (value.getPid() == pid) {
                String stringy = "Review #" + value.getId() + " by pc member with id " + value.getCid() + " result: " + value.getEvaluation();
                if (this.p.isRequest_eval()) {
                    stringy = stringy + ", request eval is true";
                }
                this.reviewsList.getItems().add(stringy);

            }
        }
    }

    public void back(ActionEvent actionEvent) {
        String nextScreen = "/sample/ShowAllPapersReview.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ShowAllPapersReview scene2Controller = loader.getController();
            scene2Controller.setConference(this.conferene,this.c);
            this.back.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}

