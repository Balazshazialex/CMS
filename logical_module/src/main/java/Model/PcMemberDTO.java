package Model;

public class PcMemberDTO {

    private ConferenceParticipant participant;
    private int proposalsAssigned;

    public PcMemberDTO(ConferenceParticipant participant, int proposalsAssigned) {
        this.participant = participant;
        this.proposalsAssigned = proposalsAssigned;
    }

    public ConferenceParticipant getParticipant() {
        return participant;
    }

    public void setParticipant(ConferenceParticipant participant) {
        this.participant = participant;
    }

    public int getProposalsAssigned() {
        return proposalsAssigned;
    }

    public void setProposalsAssigned(int proposalsAssigned) {
        this.proposalsAssigned = proposalsAssigned;
    }

    public String getName() {
        return this.participant.getName();
    }

    public void setName(String name) {
        this.participant.setName(name);
    }

    public String getUsername() {
        return this.participant.getUsername();
    }

    public void setUsername(String username) {
        this.participant.setUsername(username);
    }

    public String getAffiliation() {
        return this.participant.getAffiliation();
    }

    public void setAffiliation(String affiliation) {
        this.participant.setAffiliation(affiliation);
    }
}
