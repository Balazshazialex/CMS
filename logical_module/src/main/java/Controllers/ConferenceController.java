package Controllers;

import Model.Conference;
import Model.ConferenceParticipant;
import Repository.ConferenceRepo;

import java.util.List;

public class ConferenceController {
    private ConferenceRepo repo;

    public ConferenceController() {
        this.repo = new ConferenceRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }


    public List<Conference> findAll(){
        return repo.findAll();
    }
    public Conference findOne(int id) {return repo.findOne(id);}
    public void update(Conference participant) {repo.update(participant);}
    public void add(Conference conference){
        repo.add(conference);
    }
    public void remove(Integer id){
        repo.del(id);
    }
    public int getNextId(){return repo.getNextId();}
}
