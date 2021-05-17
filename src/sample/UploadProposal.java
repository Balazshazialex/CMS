package sample;

import Controllers.ProposalController;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class UploadProposal implements Initializable {

    private ProposalController proposalController;
    private Conference conference;
    private ConferenceParticipant  author;

    @FXML
    private TextField name;

    @FXML
    private TextField listOfAuthors;

    @FXML
    private TextField keywords;

    @FXML
    private TextField topics;

    @FXML
    private TextArea metaInfo;

    @FXML
    private TextArea abstractPaper;

    @FXML
    private TextArea fullPaper;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void populateTextFields(Proposal proposal) {
        this.name.setText(proposal.getName());
        this.abstractPaper.setText(proposal.getAbstractPaper());
        this.fullPaper.setText(proposal.getFullPaper());
        this.keywords.setText(proposal.getKeywords());
        this.topics.setText(proposal.getTopics());
        this.listOfAuthors.setText(proposal.getListOfAuthors());
        this.metaInfo.setText(proposal.getMetaInfo());

        this.addButton.setVisible(false);
    }

    public void addProposal() {
        Date dateNow = new Date();

        if(dateNow.before(this.conference.getCallForPapers())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Call for papers not started yet", ButtonType.OK);
            alert.showAndWait();
        }
        else if(dateNow.after(this.conference.getProposalDeadline())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Past deadline for uploading", ButtonType.OK);
            alert.showAndWait();

        } else {
            String name = this.name.getText();
            String abstractPaper = this.abstractPaper.getText();
            String fullPaper = this.fullPaper.getText();
            String keywords = this.keywords.getText();
            String topics = this.topics.getText();
            String listOfAuthors = this.listOfAuthors.getText();
            String metaInfo = this.metaInfo.getText();

            Proposal proposal = new Proposal(conference.getId(), this.author.getId(), name, listOfAuthors, metaInfo, abstractPaper,
                    fullPaper, keywords, topics);
            this.proposalController.add(proposal);
            this.populateTextFields(proposal);
        }
  }

    public void updateProposal() {
        Date dateNow = new Date();
        if(dateNow.after(this.conference.getProposalDeadline())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Past deadline for uploading", ButtonType.OK);
            alert.showAndWait();

        } else if(! this.fullPaper.getText().equals("") && dateNow.after(this.conference.getFullpaperDeadline())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Past deadline for full paper", ButtonType.OK);
            alert.showAndWait();
        }
        String name = this.name.getText();
        String abstractPaper = this.abstractPaper.getText();
        String fullPaper = this.fullPaper.getText();
        String keywords = this.keywords.getText();
        String topics = this.topics.getText();
        String listOfAuthors = this.listOfAuthors.getText();
        String metaInfo = this.metaInfo.getText();

        Proposal proposal = new Proposal(conference.getId(), this.author.getId(), name, listOfAuthors, metaInfo, abstractPaper,
                fullPaper, keywords, topics);
        this.proposalController.update(proposal);
        this.populateTextFields(proposal);
    }

    public void setConference(Conference conference, ConferenceParticipant author) {
        this.conference = conference;
        this.author = author;
        System.out.println(conference);
        this.proposalController = new ProposalController();
        Proposal proposal = proposalController.findOneByAuthorIdConferenceId(this.author.getId(), this.conference.getId());


        if(proposal != null) this.populateTextFields(proposal);
        else this.updateButton.setVisible(false);
    }

    public void goBack() {
        String nextScreen = "/sample/AfterLoginAuthor.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nextScreen));
        try {
            Parent parent = loader.load();
            AfterLoginAuthor scene2Controller = loader.getController();
            scene2Controller.send_message(this.author);
            this.backButton.getScene().setRoot(parent);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
