package sample;

import Controllers.BidController;
import Controllers.ProposalController;
import Model.Bid;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BidProposals implements Initializable {

    private ConferenceParticipant PCMember;
    private Conference conference;
    private BidController bidController;
    private ProposalController proposalController;

    @FXML
    private TableView proposalsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void send_message(ConferenceParticipant conferenceParticipant, Conference conference) {
        this.PCMember = conferenceParticipant;
        this.conference = conference;
        this.bidController = new BidController();
        this.proposalController = new ProposalController();
        this.addCols();
        this.populateProposalsTable();
    }

    private void addCols() {
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(125.00);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn authorsColumn = new TableColumn("Authors");
        authorsColumn.setPrefWidth(150);
        authorsColumn.setCellValueFactory(new PropertyValueFactory<>("listOfAuthors"));

        TableColumn topicsColumn = new TableColumn("Topics");
        topicsColumn.setPrefWidth(150);
        topicsColumn.setCellValueFactory(new PropertyValueFactory<>("topics"));

        TableColumn abstractColumn = new TableColumn("Abstract");
        abstractColumn.setPrefWidth(150);
        abstractColumn.setCellValueFactory(new PropertyValueFactory<>("abstractPaper"));

        TableColumn fullPaperColumn = new TableColumn("Full paper");
        fullPaperColumn.setPrefWidth(150);
        fullPaperColumn.setCellValueFactory(new PropertyValueFactory<>("fullPaper"));

        this.proposalsTable.getColumns().removeAll();
        this.proposalsTable.getColumns().addAll(nameColumn, authorsColumn, topicsColumn, abstractColumn, fullPaperColumn);
    }

    private void populateProposalsTable() {
        this.proposalsTable.getItems().clear();
        for(Proposal proposal: this.proposalController.findAllByConference(this.conference.getId())) {
            this.proposalsTable.getItems().add(proposal);
        }
    }

    public void addToReview() {
        var selectedIndices = this.proposalsTable.getSelectionModel().getSelectedIndices();
        for(var index: selectedIndices) {
            Proposal selectedProposal = (Proposal) this.proposalsTable.getItems().get((Integer) index);
            Bid bid = new Bid(this.PCMember.getId(), selectedProposal.getId(), 1);
            this.bidController.add(bid);
        }
    }

    public void addToCouldReview() {
        var selectedIndices = this.proposalsTable.getSelectionModel().getSelectedIndices();
        for(var index: selectedIndices) {
            Proposal selectedProposal = (Proposal) this.proposalsTable.getItems().get((Integer) index);
            Bid bid = new Bid(this.PCMember.getId(), selectedProposal.getId(), 0);
            this.bidController.add(bid);
        }
    }

    public void addRejectReview() {
        var selectedIndices = this.proposalsTable.getSelectionModel().getSelectedIndices();
        for(var index: selectedIndices) {
            Proposal selectedProposal = (Proposal) this.proposalsTable.getItems().get((Integer) index);
            Bid bid = new Bid(this.PCMember.getId(), selectedProposal.getId(), -1);
            this.bidController.add(bid);
        }
    }

    public void goBack() {
        String nextScreen = "/sample/ChooseConference.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ChooseConference scene2Controller = loader.getController();
            scene2Controller.send_message(this.PCMember);
            this.proposalsTable.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
