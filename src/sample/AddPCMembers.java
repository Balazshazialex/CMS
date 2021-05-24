package sample;

import Controllers.ConferenceController;
import Controllers.PCMembersController;
import Controllers.UserController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddPCMembers implements Initializable {

    private Conference selectedConference;
    private UserController userController;
    private ConferenceController conferenceController;
    private PCMembersController pcMembersController;
    private List<ConferenceParticipant> participants;
    private List<ConferenceParticipant> selectedParticipants;

    @FXML
    private ListView<String> participantsList;
    @FXML
    private ListView<String> addedPCMembers;
    @FXML
    private Label conferenceInfoLabel;
    @FXML
    private Button backButton;

    public void addPCMembers() {
        this.pcMembersController.addPCMembers(this.selectedConference, this.selectedParticipants);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setup(Conference conference) {
        this.selectedConference = conference;
        this.conferenceInfoLabel.setText(conference.getName() + " starts at: " + conference.getStartDate() + " ends at:" + conference.getEndDate());
        this.userController = new UserController();
        this.conferenceController = new ConferenceController();
        this.pcMembersController = new PCMembersController();

        this.participants = this.userController.findAll();
        this.selectedParticipants = this.pcMembersController.findAllByConference(this.selectedConference);

        this.participantsList.getItems().clear();
        for(ConferenceParticipant participant: this.userController.findAll()) {
            this.participantsList.getItems().add(participant.getName() + " " + participant.getAffiliation());
        }

        for (ConferenceParticipant PCmember : this.selectedParticipants) {
            this.addedPCMembers.getItems().add(PCmember.getName() + " " + PCmember.getAffiliation());
        }
    }

    public void addPCMemberToList(ConferenceParticipant PCmember) {
        this.addedPCMembers.getItems().add(PCmember.getName() + " " + PCmember.getAffiliation());
    }

    public void moveToSelectedList() {
        var selectedIndex = this.participantsList.getSelectionModel().getSelectedIndex();

        if(! this.selectedParticipants.contains(this.participants.get(selectedIndex))) {
            this.selectedParticipants.add(this.participants.get(selectedIndex));
            this.addPCMemberToList(this.participants.get(selectedIndex));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Already added!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void removePCMember() {
        var selectedIndex = this.addedPCMembers.getSelectionModel().getSelectedIndex();
        var PCmember = this.participants.get(selectedIndex);
        this.pcMembersController.removePCMember(this.selectedConference, PCmember);

        this.addedPCMembers.getItems().remove(selectedIndex);
        this.selectedParticipants.remove(PCmember);
    }

    public void back() {
        String nextScreen = "/sample/AfterLoginSteeringCommitteeMember.fxml";
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

}
