package Model;

public class ProposalDTO {
    private Proposal proposal;
    private int reviewersAssigned;

    public ProposalDTO(Proposal proposal, int reviewersAssigned) {
        this.proposal = proposal;
        this.reviewersAssigned = reviewersAssigned;
    }

    public String getName() {
        return proposal.getName();
    }

    public void setName(String name) {
        this.proposal.setName(name);
    }

    public String getListOfAuthors() {
        return proposal.getListOfAuthors();
    }

    public void setListOfAuthors(String listOfAuthors) {
        this.proposal.setListOfAuthors(listOfAuthors);
    }

    public String getAbstractPaper() {
        return proposal.getAbstractPaper();
    }

    public void setAbstractPaper(String abstractPaper) {
        this.proposal.setAbstractPaper(abstractPaper);
    }

    public String getFullPaper() {
        return proposal.getFullPaper();
    }

    public void setFullPaper(String fullPaper) {
        this.proposal.setFullPaper(fullPaper);
    }

    public String getTopics() {
        return proposal.getTopics();
    }

    public void setTopics(String topics) {
        this.proposal.setTopics(topics);
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public int getReviewersAssigned() {
        return reviewersAssigned;
    }

    public void setReviewersAssigned(int reviewersAssigned) {
        this.reviewersAssigned = reviewersAssigned;
    }
}
