package Controllers;

import Model.ConferenceParticipant;
import Model.Proposal;
import Model.Review;
import Repository.ReviewRepo;

import java.util.List;

public class ReviewController {
    private ReviewRepo repo;

    public ReviewController() {
        this.repo = new ReviewRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }
    public void addReview(Review proposal) { repo.add(proposal); }
    public void addAssignedReview(int participantId, int proposalId) { repo.addAssignedReview(participantId, proposalId); }
    public List<Review> findAll() { return repo.findAll(); }
    public Review findOne(int id) { return repo.findOne(id); }
    public Review findOne_cidpid(int pid, int cid) { return repo.findOne_cid_pid(pid,cid); }
    public void update(Integer id,String eval){
        repo.update_eval(id,eval);
    }
    public int getNextId(){return repo.getNextId();}
    public List<Proposal> findProposalAssignedToReview(int participantId) { return this.repo.findProposalAssignedToReview(participantId); }
    public List<ConferenceParticipant> findParticipantsAssignedToReview(int proposalId) { return this.repo.findParticipantsAssignedToReview(proposalId); }
}
