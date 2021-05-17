package Controllers;

import Model.Conference;
import Model.ConferenceParticipant;
import Model.PCMember;
import Repository.ConferenceRepo;
import Repository.PCMembersRepo;

import java.util.List;

public class PCMembersController {
    private PCMembersRepo repo;
    public PCMembersController() {
        this.repo = new PCMembersRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }
    public boolean exists(Integer idConference, Integer idPCMember){return this.repo.exists(idConference,idPCMember);}
    public void addPCMembers(Conference conference, List<ConferenceParticipant> participants) {this.repo.addPCMembers(conference, participants);}
    public List<ConferenceParticipant> findAllByConference(Conference conference) { return this.repo.findAllByConference(conference);}
    public void removePCMember(Conference conference, ConferenceParticipant PCMember) { this.repo.removePCMember(conference, PCMember);}
}
