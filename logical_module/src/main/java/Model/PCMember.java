package Model;

public class PCMember extends ConferenceParticipant {

    public PCMember(int id, String name, String username, String password, boolean hasPayedFee, String cardNumber, String affiliation, String webPage, String role) {
        super(id, name, username, password, hasPayedFee,cardNumber,affiliation,webPage,role);
    }

    public PCMember(int id, String name, String username, String password) {
        super(id,name,username,password);
    }

    public void evaluatePaper() {

    }

    public void bidProposal() {

    }

    public void chat() {

    }
}
