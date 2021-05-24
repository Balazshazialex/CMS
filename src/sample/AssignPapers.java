package sample;

import Controllers.BidController;
import Controllers.ProposalController;
import Controllers.ReviewController;
import Model.*;
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

public class AssignPapers implements Initializable {

    private BidController bidController;
    private ProposalController proposalController;
    private ConferenceParticipant participant;
    private Conference conference;
    private ReviewController reviewController;
    private Proposal selectedProposal;

    @FXML
    private TableView proposalsTable;
    @FXML
    private TableView participantsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        TableColumn reviewers = new TableColumn("Reviewers");
        reviewers.setPrefWidth(150);
        reviewers.setCellValueFactory(new PropertyValueFactory<>("reviewersAssigned"));

        this.proposalsTable.getColumns().removeAll();
        this.proposalsTable.getColumns().addAll(nameColumn, authorsColumn, topicsColumn, abstractColumn, fullPaperColumn,
                reviewers);

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn affiliation = new TableColumn("Affiliation");
        affiliation.setCellValueFactory(new PropertyValueFactory<>("affiliation"));

        TableColumn<Integer, Integer> proposalsToReview = new TableColumn("Proposals");
        proposalsToReview.setCellValueFactory(new PropertyValueFactory<>("proposalsAssigned"));

        this.participantsTable.getColumns().removeAll();
        this.participantsTable.getColumns().addAll(name, username, affiliation, proposalsToReview);
    }

    private void populateProposalsTable() {
        this.proposalsTable.getItems().clear();
        for(Proposal proposal: this.proposalController.findAllByConference(this.conference.getId())) {
            var participantsAssigned = this.reviewController.findParticipantsAssignedToReview(proposal.getId()).size();
            var dto = new ProposalDTO(proposal, participantsAssigned);
            this.proposalsTable.getItems().add(dto);
        }
    }

    public void send_message(ConferenceParticipant participant, Conference conference) {
        this.participant = participant;
        this.conference = conference;
        this.bidController = new BidController();
        this.proposalController = new ProposalController();
        this.reviewController = new ReviewController();

        this.addCols();
        this.populateProposalsTable();
    }

    public void showPCMembers() {
        var selectedIndex = this.proposalsTable.getSelectionModel().getSelectedIndex();
        ProposalDTO dto = (ProposalDTO) this.proposalsTable.getItems().get(selectedIndex);
        this.selectedProposal = dto.getProposal();
        this.populateParticipantsTable();
    }

    public void addToReview() {
        var selectedIndex = this.participantsTable.getSelectionModel().getSelectedIndex();
        PcMemberDTO participantDto = (PcMemberDTO) this.participantsTable.getItems().get(selectedIndex);
        ConferenceParticipant participant = participantDto.getParticipant();
        this.reviewController.addAssignedReview(participant.getId(), this.selectedProposal.getId());

        this.populateProposalsTable();
        this.populateParticipantsTable();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Added !", ButtonType.OK);
        alert.showAndWait();
    }

    private void populateParticipantsTable() {
        this.participantsTable.getItems().clear();

        for(ConferenceParticipant participant: this.bidController.findAllParticipantsByProposal(this.selectedProposal.getId())) {
            int proposalsAssigned = this.reviewController.findProposalAssignedToReview(participant.getId()).size();
            System.out.println(proposalsAssigned);
            this.participantsTable.getItems().add(new PcMemberDTO(participant, proposalsAssigned));
        }
    }

    public void back() {
        String nextScreen = "/sample/ChooseConference.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ChooseConference scene2Controller = loader.getController();
            scene2Controller.send_message(this.participant);
            this.participantsTable.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
