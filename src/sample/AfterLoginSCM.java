package sample;

import Controllers.ConferenceController;
import Model.Conference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AfterLoginSCM implements Initializable {

    private ConferenceController conferenceController;
    private List<Conference> conferences;

    @FXML
    private ListView<String> conferencesList;
    @FXML
    private Button addConferenceButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private DatePicker callForPapers;
    @FXML
    private DatePicker proposalDeadline;
    @FXML
    private DatePicker fullPaperDeadline;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conferenceController = new ConferenceController();
        this.populateConferencesList();
    }

    public void addConference() {
        int id = this.conferenceController.getNextId();
        String name = this.nameTextField.getText();
        Date startDate = new Date ((Date.from(Instant.from(this.startDate.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date endDate = new Date((Date.from(Instant.from(this.endDate.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date callForPapers = new Date((Date.from(Instant.from(this.callForPapers.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date proposalDeadline = new Date((Date.from(Instant.from(this.proposalDeadline.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date fullPaperDeadline = new Date((Date.from(Instant.from(this.fullPaperDeadline.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());

        Conference conference = new Conference(id, name, startDate, endDate, callForPapers, proposalDeadline, fullPaperDeadline);
        this.conferenceController.add(conference);
        this.populateConferencesList();
    }

    private void populateConferencesList() {
        this.conferencesList.getItems().clear();
        this.conferences = this.conferenceController.findAll();
        for(Conference conference: this.conferenceController.findAll()) {
            this.conferencesList.getItems().add(conference.getName() + " starts at: " + conference.getStartDate() + " ends at:" + conference.getEndDate());
        }
    }

    public void addPCMembersToConference() {
        var selectedConference = this.conferences.get(this.conferencesList.getSelectionModel().getSelectedIndex());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/AddPCMembers.fxml"));
            Parent parent = loader.load();
            AddPCMembers controller = loader.getController();
            controller.setSelectedConference(selectedConference);
            this.conferencesList.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
