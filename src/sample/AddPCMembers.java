package sample;

import Controllers.ConferenceController;
import Controllers.UserController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddPCMembers implements Initializable {

    private Conference selectedConference;
    private UserController userController;
    private ConferenceController conferenceController;
    private List<ConferenceParticipant> participants;
    private List<ConferenceParticipant> selectedParticipants = new ArrayList<>();
    private URL location;
    private ResourceBundle resourceBundle;

    @FXML
    private ListView<String> participantsList;
    @FXML
    private ListView<String> addedPCMembers;
    @FXML
    private Label conferenceInfoLabel;

    public void addPCMembers() {
        this.conferenceController.addPCMembers(this.selectedConference, this.selectedParticipants);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userController = new UserController();
        this.conferenceController = new ConferenceController();
        this.participantsList.getItems().clear();
        this.participants = this.userController.findAll();
        for(ConferenceParticipant participant: this.userController.findAll()) {
            this.participantsList.getItems().add(participant.getName() + " " + participant.getAffiliation());
        }
    }

    public void setSelectedConference(Conference conference) {
        this.selectedConference = conference;
        this.conferenceInfoLabel.setText(conference.getName() + " starts at: " + conference.getStartDate() + " ends at:" + conference.getEndDate());
        this.initialize(this.location, this.resourceBundle);
    }

    public void populateAddedPCMembersList(ConferenceParticipant PCmember) {
        this.addedPCMembers.getItems().add(PCmember.getName() + " " + PCmember.getAffiliation());
    }

    public void moveToSelectedList() {
        var selectedIndex = this.participantsList.getSelectionModel().getSelectedIndex();
        if(! this.selectedParticipants.contains(this.participants.get(selectedIndex))) {
            this.selectedParticipants.add(this.participants.get(selectedIndex));
            this.populateAddedPCMembersList(this.participants.get(selectedIndex));
        }
    }
}