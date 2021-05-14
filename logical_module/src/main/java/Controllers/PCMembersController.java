package Controllers;

import Model.PCMember;
import Repository.ConferenceRepo;
import Repository.PCMembersRepo;

public class PCMembersController {
    private PCMembersRepo repo;
    public PCMembersController() {
        this.repo = new PCMembersRepo("jdbc:postgresql://rajje.db.elephantsql.com:5432/xbumsntp","xbumsntp","245Qb6b1kuJd_WE523AVqE3r3aKemTds");
    }
    public boolean exists(Integer idConference, Integer idPCMember){return this.repo.exists(idConference,idPCMember);}
}
