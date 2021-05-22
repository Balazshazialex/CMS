package Controllers;

import Model.Bid;
import Model.Proposal;
import Repository.BidRepo;

import java.util.List;

public class BidController {
    private BidRepo repo;

    public BidController() {
        this.repo = new BidRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp", "xbumsntp", "245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }

    public List<Bid> findAll() {
        return this.repo.findAll();
    }
    public List<Proposal> findAllByConference(Integer conferenceId) {
        return this.repo.findAllByConference(conferenceId);
    }

    public Bid findOne(Integer PCMemberId, Integer proposalId) {
        return this.repo.findOne(PCMemberId, proposalId);
    }

    public void add(Bid bid) {
        this.repo.add(bid);
    }
}
