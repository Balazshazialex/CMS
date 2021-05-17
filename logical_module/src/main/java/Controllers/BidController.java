package Controllers;

import Model.Bid;
import Model.Proposal;
import Repository.BidRepo;
import Repository.ProposalRepo;

import java.util.List;

public class BidController {
    private BidRepo repo;

    public BidController() {
        this.repo = new BidRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }
    public void add(Bid proposal) { repo.add(proposal); }
    public List<Bid> findAll() { return repo.findAll(); }
    public Bid findOne(int id) { return repo.findOne(id); }
    public int getNextId(){return repo.getNextId();}
}
