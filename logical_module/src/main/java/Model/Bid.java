package Model;

public class Bid {
    private int PCMemberId;
    private int proposalId;
    private int bidInfo; // -1 -> rejected, 0 -> could review, 1->pleased to review

    public Bid(int PCMemberId, int proposalId, int bidInfo) {
        this.PCMemberId = PCMemberId;
        this.proposalId = proposalId;
        this.bidInfo = bidInfo;
    }

    public int getPCMemberId() {
        return PCMemberId;
    }

    public void setPCMemberId(int PCMemberId) {
        this.PCMemberId = PCMemberId;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getBidInfo() {
        return bidInfo;
    }

    public void setBidInfo(int bidInfo) {
        this.bidInfo = bidInfo;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "PCMemberId=" + PCMemberId +
                ", proposalId=" + proposalId +
                ", bidInfo=" + bidInfo +
                '}';
    }
}
