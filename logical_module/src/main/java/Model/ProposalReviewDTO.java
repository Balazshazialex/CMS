package Model;

public class ProposalReviewDTO {

    private Proposal proposal;
    private Review review;

    public ProposalReviewDTO(Proposal proposal, Review review) {
        this.proposal = proposal;
        this.review = review;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
