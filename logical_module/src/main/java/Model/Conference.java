package Model;


import java.sql.Date;

public class Conference {
    protected int id;
    protected String name;
    protected Date startDate;
    protected Date endDate;
    protected Date callForPapers;
    protected Date proposalDeadline;
    protected Date fullpaperDeadline;
    protected int phase;

    public Conference(int id, String name, Date startDate, Date endDate, Date callForPapers, Date proposalDeadline, Date fullpaperDeadline,int phase) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.callForPapers = callForPapers;
        this.proposalDeadline = proposalDeadline;
        this.fullpaperDeadline = fullpaperDeadline;
        this.phase=phase;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", callForPapers=" + callForPapers +
                ", proposalDeadline=" + proposalDeadline +
                ", fullpaperDeadline=" + fullpaperDeadline +
                '}';
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCallForPapers() {
        return callForPapers;
    }

    public void setCallForPapers(Date callForPapers) {
        this.callForPapers = callForPapers;
    }

    public Date getProposalDeadline() {
        return proposalDeadline;
    }

    public void setProposalDeadline(Date proposalDeadline) {
        this.proposalDeadline = proposalDeadline;
    }

    public Date getFullpaperDeadline() {
        return fullpaperDeadline;
    }

    public void setFullpaperDeadline(Date fullpaperDeadline) {
        this.fullpaperDeadline = fullpaperDeadline;
    }
}
