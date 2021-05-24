package Controllers;

import Model.Conference;
import Model.ConferenceParticipant;
import Repository.PCMembersRepo;

import java.util.List;

public class PCMembersController {
    private PCMembersRepo repo;
    public PCMembersController() {
        this.repo = new PCMembersRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }
    public boolean exists(Integer idConference, Integer idPCMember){return this.repo.exists(idConference,idPCMember);}
    public List<Conference> get_all_conferences_assigned_to_PCM(Integer idPCMember){
        return repo.get_all_conferences_assigned_to_PCM(idPCMember);
    }

    public void addPCMembers(Conference conference, List<ConferenceParticipant> participants) {this.repo.addPCMembers(conference, participants);}
    public List<ConferenceParticipant> findAllByConference(Conference conference) { return this.repo.findAllByConference(conference);}
    public List<ConferenceParticipant> findChairsByConference(Conference conference) { return this.repo.findChairsByConference(conference);}
    public void removePCMember(Conference conference, ConferenceParticipant PCMember) { this.repo.removePCMember(conference, PCMember);}
}
