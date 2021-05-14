package Controllers;

import Model.ConferenceParticipant;
import Repository.UserRepo;

import java.util.List;

public class UserController {
    private UserRepo repo;

    public UserController() {
        this.repo = new UserRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }

    public List<ConferenceParticipant> findAll(){
        return repo.findAll();
    }
    public ConferenceParticipant findOne(int id) {return repo.findOne(id);}
    public ConferenceParticipant findOne(String username, String password) {return repo.findOne(username,password);}
    public void update(ConferenceParticipant participant) {repo.update(participant);}
    public boolean checkCreds(String username, String password, String role){
        return repo.checkCreds(username,password,role);
    }
    public void payFee(Integer id){
        repo.payFee(id);
    }
}
