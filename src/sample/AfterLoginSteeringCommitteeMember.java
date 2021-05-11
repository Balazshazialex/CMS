package sample;

import Controllers.ConferenceController;
import Model.Conference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ResourceBundle;

public class AfterLoginSteeringCommitteeMember implements Initializable {

    @FXML
    public TextField id;
    @FXML
    public TableView conf_table;
    private ConferenceController conferenceController;

    //    @FXML
//    private ListView<String> conferencesList;
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
    @FXML
    public TextField Phase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conferenceController = new ConferenceController();
        this.addcols();
        this.populateConferencesList();
    }

    public void addConference() {
        int id = this.conferenceController.getNextId();
        String name = this.nameTextField.getText();
        Date startDate = new Date((Date.from(Instant.from(this.startDate.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date endDate = new Date((Date.from(Instant.from(this.endDate.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date callForPapers = new Date((Date.from(Instant.from(this.callForPapers.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date proposalDeadline = new Date((Date.from(Instant.from(this.proposalDeadline.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date fullPaperDeadline = new Date((Date.from(Instant.from(this.fullPaperDeadline.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        int phase = Integer.parseInt(this.Phase.getText());
        Conference conference = new Conference(id, name, startDate, endDate, callForPapers, proposalDeadline, fullPaperDeadline, phase);
        this.conferenceController.add(conference);
        this.populateConferencesList();
    }
    private void addcols(){
        TableColumn id = new TableColumn("ID");
        id.setPrefWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(125.00);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn startDateColumn = new TableColumn("Start date");
        startDateColumn.setPrefWidth(150);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn endDateColumn = new TableColumn("End date");
        endDateColumn.setPrefWidth(150);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn callColumn = new TableColumn("Call for papers");
        callColumn.setPrefWidth(150);
        callColumn.setCellValueFactory(new PropertyValueFactory<>("callForPapers"));

        TableColumn proposalDeadlineColumn = new TableColumn("Proposal deadline");
        proposalDeadlineColumn.setPrefWidth(150);
        proposalDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("proposalDeadline"));

        TableColumn fullDeadlineColumn = new TableColumn("Full paper deadline");
        fullDeadlineColumn.setPrefWidth(150);
        fullDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("fullpaperDeadline"));

        TableColumn phaseColumn = new TableColumn("Phase");
        phaseColumn.setPrefWidth(75.00);
        phaseColumn.setCellValueFactory(new PropertyValueFactory<>("phase"));

        this.conf_table.getColumns().removeAll();
        this.conf_table.getColumns().addAll(id,nameColumn, startDateColumn,endDateColumn,callColumn,
                proposalDeadlineColumn,fullDeadlineColumn,phaseColumn);
    }
    private void populateConferencesList() {

        this.conf_table.getItems().clear();
        for (Conference conference : this.conferenceController.findAll()) {
            this.conf_table.getItems().add(conference);
       }
    }

    public void updateConference(ActionEvent actionEvent) {
        int id = Integer.parseInt(this.id.getText());
        String name = this.nameTextField.getText();
        Date startDate = new Date((Date.from(Instant.from(this.startDate.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date endDate = new Date((Date.from(Instant.from(this.endDate.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date callForPapers = new Date((Date.from(Instant.from(this.callForPapers.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date proposalDeadline = new Date((Date.from(Instant.from(this.proposalDeadline.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        Date fullPaperDeadline = new Date((Date.from(Instant.from(this.fullPaperDeadline.getValue().atStartOfDay(ZoneId.systemDefault())))).getTime());
        int phase = Integer.parseInt(this.Phase.getText());
        Conference conference = new Conference(id, name, startDate, endDate, callForPapers, proposalDeadline, fullPaperDeadline, phase);
        this.conferenceController.update(conference);
        this.populateConferencesList();
    }
}
