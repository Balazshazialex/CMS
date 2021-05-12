package sample;

import Controllers.ProposalController;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.Proposal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ConferenceController implements Initializable {
    @FXML
    public Text phase_name;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField lofaut;
    @FXML
    public TextArea metaInfo;
    @FXML
    public TextField topics;
    @FXML
    public TextArea abstractpaper;
    @FXML
    public TextField keywords;
    @FXML
    public TextArea fullpaper;
    @FXML
    public Button submit;
    @FXML
    public Button update;

    private ProposalController proposalController;
    private Conference conf;
    private ConferenceParticipant participant;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proposalController=new ProposalController();
    }

    private void check_if_proposal_exists() {
        if(proposalController.findOneByAuthorIdConferenceId(this.participant.getId(),this.conf.getId())==null){
            this.submit.setVisible(true);
            this.update.setVisible(false);
        }
        else{
            this.update.setVisible(true);
            this.submit.setVisible(false);
            fill_proposal_data();
        }
    }

    private void fill_proposal_data() {
        Proposal p=proposalController.findOneByAuthorIdConferenceId(this.participant.getId(),this.conf.getId());
        this.nameTextField.setText(p.getName());
        this.lofaut.setText(p.getListOfAuthors());
        this.metaInfo.setText(p.getMetaInfo());
        this.topics.setText(p.getTopics());
        this.keywords.setText(p.getKeywords());
        this.fullpaper.setText(p.getFullPaper());
        this.abstractpaper.setText(p.getAbstractPaper());
    }

    public void create_proposal(ActionEvent actionEvent){
       var name=this.nameTextField.getText();
       var list_of_authors=this.lofaut.getText();
       var meta=this.metaInfo.getText();
       var topics=this.topics.getText();
       var keywords=this.keywords.getText();
       var fullpaper=this.fullpaper.getText();
       var abstractpaper=this.abstractpaper.getText();
       Proposal p=new Proposal(this.proposalController.getNextId(),this.conf.getId(),this.participant.getId(),name,list_of_authors,meta,abstractpaper,fullpaper,keywords,topics);
       this.proposalController.add(p);
       check_if_proposal_exists();
    }

    public void update_proposal(ActionEvent actionEvent){
        System.out.println("haha");

    }
    private void set_text_title() {
        this.phase_name.setText("Conference: " + this.conf.getName() + " is in phase " + this.conf.getPhase());
        this.phase_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    }

    public void send_message(Conference c, ConferenceParticipant conferenceParticipant) {
        this.conf = c;
        this.participant = conferenceParticipant;
        set_text_title();
        check_if_proposal_exists();
    }
}