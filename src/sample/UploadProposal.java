package sample;

import Controllers.ProposalController;
import Model.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UploadProposal implements Initializable {

    private ProposalController proposalController;
    int authorId = 1;
    int conferenceId = 1;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proposalController = new ProposalController();
        Proposal proposal = proposalController.findOneByAuthorIdConferenceId(authorId, conferenceId);
        if(proposal != null) this.populateTextFields(proposal);
        else this.updateButton.setVisible(false);
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
        String name = this.name.getText();
        String abstractPaper = this.abstractPaper.getText();
        String fullPaper = this.fullPaper.getText();
        String keywords = this.keywords.getText();
        String topics = this.topics.getText();
        String listOfAuthors = this.listOfAuthors.getText();
        String metaInfo = this.metaInfo.getText();

        Proposal proposal = new Proposal(conferenceId, authorId, name, listOfAuthors, metaInfo, abstractPaper,
                fullPaper, keywords, topics);
        this.proposalController.add(proposal);
        this.populateTextFields(proposal);
    }

    public void updateProposal() {
        String name = this.name.getText();
        String abstractPaper = this.abstractPaper.getText();
        String fullPaper = this.fullPaper.getText();
        String keywords = this.keywords.getText();
        String topics = this.topics.getText();
        String listOfAuthors = this.listOfAuthors.getText();
        String metaInfo = this.metaInfo.getText();

        Proposal proposal = new Proposal(conferenceId, authorId, name, listOfAuthors, metaInfo, abstractPaper,
                fullPaper, keywords, topics);
        this.proposalController.update(proposal);
        this.populateTextFields(proposal);
    }
}
