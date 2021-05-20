package sample;

import Controllers.BidController;
import Controllers.ProposalController;
import Model.Bid;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.Proposal;
import Repository.ProposalRepo;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class ShowAllPapersReview implements Initializable {

    @FXML
    public ChoiceBox choicebox;
    @FXML
    public TextField name;
    @FXML
    public TextField listOfAuthors;
    @FXML
    public TextField keywords;
    @FXML
    public TextField topics;
    @FXML
    public TextArea abstractPaper;
    @FXML
    public TextArea fullPaper;
    @FXML
    public TextArea metaInfo;
    @FXML
    public TextField review;
    @FXML
    public Button submit_review_button;
    @FXML
    public Button update_review_button;
    @FXML
    public Button show_reviews_button;
    @FXML
    public Button back;

    private ConferenceParticipant c;
    private Conference conferece;
    private ProposalController proposalController;
    private ArrayList<Proposal> proposals;
    private BidController bidController;
    private Proposal p=null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proposalController = new ProposalController();
        this.bidController=new BidController();
    }

    public void setConference(Conference conferen, ConferenceParticipant c) {
        this.conferece = conferen;
        this.c = c;
        fill_out_table();
        hide_add_review();
    }

    private void hide_add_review() {
        if(this.p!=null) {
            Bid bid=bidController.findOne_cidpid(this.p.getId(), this.c.getId());
            if (bid != null) {
                this.submit_review_button.setVisible(false);
                this.review.setText(String.valueOf(bid.getEvaluation()));
                if(this.p.isRequest_eval()){
                    this.update_review_button.setVisible(true);
                }
                this.show_reviews_button.setVisible(true);
            } else {
                this.review.setText("");
                this.submit_review_button.setVisible(true);
                this.update_review_button.setVisible(false);
                this.show_reviews_button.setVisible(false);
            }
        }
    }

    private void fill_out_table() {
        this.proposals = (ArrayList<Proposal>) proposalController.findAll();
        for (var i = 0; i < proposals.size(); i++) {
            if (proposals.get(i).getConferenceId() != this.conferece.getId()) {
                proposals.remove(i);
                i--;

            } else {
                this.choicebox.getItems().add(proposals.get(i).getName());
            }
        }


    }

    public void loadProposal(ActionEvent actionEvent) {
        Proposal proposal = null;
        String value = (String) this.choicebox.getSelectionModel().getSelectedItem();
        for (var i = 0; i < proposals.size(); i++) {
            if (proposals.get(i).getName().equals(value)) {
                proposal = proposals.get(i);
                break;
            }
        }
        if (proposal != null) {

            this.name.setText(proposal.getName());
            this.name.setEditable(false);
            this.listOfAuthors.setText(proposal.getListOfAuthors());
            this.listOfAuthors.setEditable(false);
            this.keywords.setText(proposal.getKeywords());
            this.keywords.setEditable(false);
            this.topics.setText(proposal.getTopics());
            this.topics.setEditable(false);
            this.metaInfo.setText(proposal.getMetaInfo());
            this.metaInfo.setEditable(false);
            this.abstractPaper.setText(proposal.getAbstractPaper());
            this.fullPaper.setText(proposal.getFullPaper());
            this.fullPaper.setEditable(false);
            this.p=proposal;
            if(proposal.isRequest_eval()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "This proposal needs to be re-checked", ButtonType.OK);
                alert.showAndWait();
            }
            hide_add_review();
        }
    }

    public void submitreview(ActionEvent actionEvent) {
        Bid bid=new Bid(this.bidController.getNextId(),this.p.getId(),this.c.getId(),this.review.getText());
        bidController.add(bid);
    }

    public void updatereview(ActionEvent actionEvent) {
        Bid bid=bidController.findOne_cidpid(this.p.getId(), this.c.getId());
        this.bidController.update(bid.getId(),this.review.getText());
        this.proposalController.canceleval(this.p.getId());
        this.update_review_button.setVisible(false);
        this.p.setRequest_eval(false);
        hide_add_review();
    }

    public void showreviews(ActionEvent actionEvent) {
        String nextScreen = "/sample/AllReviews.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            //sample.ConferenceController scene2Controller = loader.getController();
            sample.AllReviews scene2Controller = loader.getController();
            //scene2Controller.send_message(conference);
            scene2Controller.setConference(this.c,this.conferece,this.p);
            this.update_review_button.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void back(ActionEvent actionEvent) {
        String nextScreen = "/sample/ReviewPaper.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            ReviewPaper scene2Controller = loader.getController();
            scene2Controller.send_message(this.c);
            this.back.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
