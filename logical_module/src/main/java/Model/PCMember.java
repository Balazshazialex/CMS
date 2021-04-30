package Model;

public class PCMember  extends ConferenceParticipant implements IPCMember{

    public PCMember(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public void uploadPersonalInformation(String name, String affiliation, String emailAddress, String personalWebPage) {
        this.setName(name);
        this.setUsername(emailAddress);
        this.setAffiliation(affiliation);
        this.setWebPage(webPage);
    }

    @Override
    public void evaluatePaper() {

    }

    @Override
    public void bidProposal() {

    }

    @Override
    public void chat() {

    }
}
