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
}