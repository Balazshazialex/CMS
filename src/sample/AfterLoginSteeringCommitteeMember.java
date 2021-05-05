package sample;

import Controllers.ConferenceController;
import Model.Conference;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ResourceBundle;

public class AfterLoginSteeringCommitteeMember implements Initializable {

    private ConferenceController conferenceController;

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
        for(Conference conference: this.conferenceController.findAll()) {
            this.conferencesList.getItems().add(conference.getName() + " starts at: " + conference.getStartDate() + " ends at:" + conference.getEndDate());
        }
    }
}
