package sample;

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
import java.util.*;
import java.util.stream.Collectors;

public class Reevaluation implements Initializable {

    private ConferenceParticipant participant;
    private Conference conference;
    private ReviewController reviewController;
    private ProposalController proposalController;

    @FXML
    private TableView proposalsToReevaluate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send_message(ConferenceParticipant participant, Conference conference) {
        this.participant = participant;
        this.conference = conference;
        this.reviewController = new ReviewController();
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

        this.proposalsToReevaluate.getColumns().removeAll();
        this.proposalsToReevaluate.getColumns().addAll(nameColumn, authorsColumn, topicsColumn, abstractColumn,
                fullPaperColumn);
    }

    public void populateProposalsTable() {
        var proposalsReviewed = this.reviewController.findReviewedProposals(this.conference.getId());
        var allProposals = proposalsReviewed.stream().map(ProposalReviewDTO::getProposal).collect(Collectors.toSet());

        var proposalAndReviews = new HashMap<Proposal, List<Review>>();

        for(var proposal: allProposals) {
            for(var dto: proposalsReviewed) {
                if(dto.getProposal().getId() == proposal.getId()) {

                    if(! proposalAndReviews.containsKey(proposal)) {
                        var reviews = new ArrayList<Review>();
                        reviews.add(dto.getReview());
                        proposalAndReviews.put(proposal, reviews);

                    } else {
                        var reviews = proposalAndReviews.get(proposal);
                        reviews.add(dto.getReview());
                        proposalAndReviews.put(proposal, reviews);
                    }
                }
            }
        }

        List<Proposal> proposalsToReevaluate = new ArrayList<>();
        for(var keyProposal: proposalAndReviews.keySet()) {
            var reviews = proposalAndReviews.get(keyProposal);
            var accepts = 0;
            var rejects = 0;

            for(var review: reviews) {
                if(review.getEvaluation().contains("accept")) accepts++;
                if(review.getEvaluation().contains("reject")) rejects++;
            }
            if(accepts == reviews.size()) {
                keyProposal.setEvaluation_result(1);
                this.proposalController.update(keyProposal);
            } else if(rejects == reviews.size()) {
                keyProposal.setEvaluation_result(-1);
                this.proposalController.update(keyProposal);
            } else {
                proposalsToReevaluate.add(keyProposal);
            }
        }

        for(var proposal: proposalsToReevaluate){
            this.proposalsToReevaluate.getItems().clear();
            this.proposalsToReevaluate.getItems().add(proposal);
        }
    }

    public void accept() {
        var selectedIndex = this.proposalsToReevaluate.getSelectionModel().getSelectedIndex();
        Proposal proposal = (Proposal) this.proposalsToReevaluate.getItems().get(selectedIndex);
        proposal.setEvaluation_result(1);
        this.proposalController.update(proposal);
    }

    public void reject() {
        var selectedIndex = this.proposalsToReevaluate.getSelectionModel().getSelectedIndex();
        Proposal proposal = (Proposal) this.proposalsToReevaluate.getItems().get(selectedIndex);
        proposal.setEvaluation_result(-1);
        this.proposalController.update(proposal);
    }

    public void requestCloserEvaluation() {
        var selectedIndex = this.proposalsToReevaluate.getSelectionModel().getSelectedIndex();
        Proposal proposal = (Proposal) this.proposalsToReevaluate.getItems().get(selectedIndex);
        proposal.setRequest_eval(true);
        this.proposalController.update(proposal);
    }

    public void back() {
        String nextScreen = "/sample/ChooseConference.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ChooseConference scene2Controller = loader.getController();
            scene2Controller.send_message(this.participant);
            this.proposalsToReevaluate.getScene().setRoot(parent);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
