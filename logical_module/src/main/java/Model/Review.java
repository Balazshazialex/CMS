package Model;

public class Review {
    private int id;
    private int pid;
    private int cid;
    private String evaluation;
    private String recommendations;
    //pid - proposal id, cid- conf participant id
    public Review(int id, int pid, int cid, String evaluation, String recommendations) {
        this.id = id;
        this.pid = pid;
        this.cid = cid;
        this.evaluation = evaluation;
        this.recommendations = recommendations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}
