package sample;

import Controllers.PCMembersController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseConference implements Initializable {

    private PCMembersController pcMembersController;
    private ConferenceParticipant participant;

    @FXML
    private TableView conferencesTable;
    @FXML
    private Button biddingButton;
    @FXML
    private Button assignPapersButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void addcols() {
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

        this.conferencesTable.getColumns().removeAll();
        this.conferencesTable.getColumns().addAll(id, nameColumn, startDateColumn, endDateColumn, callColumn,
                proposalDeadlineColumn, fullDeadlineColumn, phaseColumn);
    }

    private void populateConferencesList() {
        this.conferencesTable.getItems().clear();
        for (Conference conference : this.pcMembersController.get_all_conferences_assigned_to_PCM(this.participant.getId())) {
            this.conferencesTable.getItems().add(conference);
        }
    }

    public void send_message(ConferenceParticipant conferenceParticipant) {
        this.participant = conferenceParticipant;
        this.pcMembersController = new PCMembersController();
        this.addcols();
        this.populateConferencesList();

        if(this.participant.getRole().equals("PC Member")) {
            this.biddingButton.setVisible(true);
            this.assignPapersButton.setVisible(false);
        } else {
            this.biddingButton.setVisible(false);
            this.assignPapersButton.setVisible(true);
        }
    }

    public void goToBidProposals() {
        var selectedIndex = this.conferencesTable.getSelectionModel().getSelectedIndex();
        Conference conference = (Conference) this.conferencesTable.getItems().get(selectedIndex);

        String nextScreen = "/sample/BidProposals.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            BidProposals scene2Controller = loader.getController();
            scene2Controller.send_message(this.participant, conference);
            this.conferencesTable.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void goToAssignPapers() {
        var selectedIndex = this.conferencesTable.getSelectionModel().getSelectedIndex();
        Conference conference = (Conference) this.conferencesTable.getItems().get(selectedIndex);

        String nextScreen = "/sample/AssignPapers.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            AssignPapers scene2Controller = loader.getController();
            scene2Controller.send_message(this.participant, conference);
            this.conferencesTable.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void goBack() {
        if(this.participant.getRole().equals("PC Member")) {
            String nextScreen = "/sample/AfterLoginPCMember.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                AfterLoginPCMember scene2Controller = loader.getController();
                scene2Controller.send_message(this.participant);
                this.conferencesTable.getScene().setRoot(parent);

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            String nextScreen = "/sample/AfterLoginChair.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                AfterLoginChair scene2Controller = loader.getController();
                scene2Controller.send_message(this.participant);
                this.conferencesTable.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }

    }

}
