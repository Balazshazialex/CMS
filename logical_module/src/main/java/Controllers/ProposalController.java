package Controllers;

import Model.Proposal;
import Repository.ProposalRepo;

import java.util.List;

public class ProposalController {
    private ProposalRepo repo;

    public ProposalController() {
        this.repo = new ProposalRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }

    public void add(Proposal proposal) { repo.add(proposal); }
    public List<Proposal> findAll() { return repo.findAll(); }
    public List<Proposal> findAllByConference(Integer conferenceId) { return repo.findAllByConference(conferenceId); }
    public Proposal findOne(int id) { return repo.findOne(id); }
    public void update(Proposal proposal) { repo.update(proposal); }
    public void updateeval(int id) { repo.updaterequest(id); }
    public void canceleval(int id) { repo.cancelrequest(id); }
    public Proposal findOneByAuthorIdConferenceId(int authorId, int conferenceId) { return repo.findOneByAuthorIdConferenceId(authorId, conferenceId); }
    public int getNextId(){return repo.getNextId();}
}
