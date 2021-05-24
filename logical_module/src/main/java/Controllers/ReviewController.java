package Controllers;

import Model.Review;
import Repository.ReviewRepo;

import java.util.List;

public class ReviewController {
    private ReviewRepo repo;

    public ReviewController() {
        this.repo = new ReviewRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }
    public void add(Review proposal) { repo.add(proposal); }
    public List<Review> findAll() { return repo.findAll(); }
    public Review findOne(int id) { return repo.findOne(id); }
    public Review findOne_cidpid(int pid, int cid) { return repo.findOne_cid_pid(pid,cid); }
    public void update(Integer id,String eval){
        repo.update_eval(id,eval);
    }
    public int getNextId(){return repo.getNextId();}
}
