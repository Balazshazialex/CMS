package sample;

import Controllers.BidController;
import Controllers.ProposalController;
import Model.Bid;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.Proposal;
import Repository.BidRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import javax.net.ssl.CertPathTrustManagerParameters;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllReviewsProposal implements Initializable {
    @FXML
    public ChoiceBox choicebox;
    @FXML
    public ListView reviewsList;
    private ConferenceParticipant c;
    private Conference conferece;
    private ArrayList<Proposal> proposals;
    private ProposalController proposalController;
    private BidController bidRepo;
    private Proposal proposal=null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proposalController = new ProposalController();
        this.bidRepo=new BidController();
    }

    public void loadReviews(ActionEvent actionEvent) {
        load();
    }
    public void load(){
        String value = (String) this.choicebox.getSelectionModel().getSelectedItem();
        for (var i = 0; i < proposals.size(); i++) {
            if (proposals.get(i).getName().equals(value)) {
                proposal = proposals.get(i);
                break;
            }
        }
        if (proposal!=null){
            this.reviewsList.getItems().clear();
            var pid=proposal.getId();
            var bids=bidRepo.findAll();
            for(int i=0;i<bids.size();i++){
                if(bids.get(i).getPid()==pid)
                {
                    Bid bid=bids.get(i);
                    String stringy="Review #"+bid.getId()+ " by pc member with id "+bid.getCid()+ " result: "+bid.getEvaluation();
                    if(proposal.isRequest_eval()){
                        stringy=stringy+", request eval is true";
                    }
                    this.reviewsList.getItems().add(stringy);

                }
            }
        }
    }

    public void requesteval(ActionEvent actionEvent) {
        if(this.proposal!=null){
            this.proposalController.updateeval(proposal.getId());
            load();
        }
    }

    public void setConference(Conference conference, ConferenceParticipant c) {
        this.c = c;
        this.conferece = conference;
        fill_out_table();
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

}
