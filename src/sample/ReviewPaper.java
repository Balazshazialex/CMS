package sample;

import Controllers.PCMembersController;
import Model.Conference;
import Model.ConferenceParticipant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewPaper implements Initializable {
    @FXML
    public TableView conf_table;
    @FXML
    public Button show_proposals;
    @FXML
    public Button back;
    private ConferenceParticipant c;
    private PCMembersController pc_controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pc_controller = new PCMembersController();
        addcols();
    }

    public void send_message(ConferenceParticipant conferenceParticipant) {
        this.c = conferenceParticipant;
        populateConferencesList();
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

        this.conf_table.getColumns().removeAll();
        this.conf_table.getColumns().addAll(id, nameColumn, startDateColumn, endDateColumn, callColumn,
                proposalDeadlineColumn, fullDeadlineColumn, phaseColumn);
    }

    private void populateConferencesList() {
        this.conf_table.getItems().clear();
        for (Conference conference : pc_controller.get_all_conferences_assigned_to_PCM(c.getId())) {
            this.conf_table.getItems().add(conference);
        }
    }

    public void show_all_proposals(ActionEvent actionEvent) {
        if(this.c.getRole().equals("PC member")){
            int  index = this.conf_table.getSelectionModel().getSelectedIndex();
            Conference conference= (Conference) this.conf_table.getItems().get(index);
            String nextScreen = "/sample/ShowAllPapersReview.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                //sample.ConferenceController scene2Controller = loader.getController();
                sample.ShowAllPapersReview scene2Controller = loader.getController();
                //scene2Controller.send_message(conference);
                scene2Controller.setConference(conference, this.c);
                this.conf_table.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }
        else{
            int  index = this.conf_table.getSelectionModel().getSelectedIndex();
            Conference conference= (Conference) this.conf_table.getItems().get(index);
            String nextScreen = "/sample/AllReviewsProposal.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                //sample.ConferenceController scene2Controller = loader.getController();
                sample.AllReviewsProposal scene2Controller = loader.getController();
                //scene2Controller.send_message(conference);
                scene2Controller.setConference(conference, this.c);
                this.conf_table.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }

    }

    public void back(ActionEvent actionEvent) {
        if(this.c.getRole().equals("PC member")){
            String nextScreen = "/sample/AfterLoginPCMember.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                AfterLoginPCMember scene2Controller = loader.getController();
                scene2Controller.send_message(this.c);
                this.back.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }
        else{
            String nextScreen = "/sample/AfterLoginChair.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(nextScreen));
            try {
                Parent parent = loader.load();
                AfterLoginChair scene2Controller = loader.getController();
                scene2Controller.send_message(this.c);
                this.back.getScene().setRoot(parent);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }

    }
}
