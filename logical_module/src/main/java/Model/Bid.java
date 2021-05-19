package Model;

public class Bid {
    private int id;
    private int pid;
    private int cid;
    private String evaluation;
    //pid - proposal id, cid- conf participant id
    public Bid(int id, int pid, int cid, String evaluation) {
        this.id = id;
        this.pid = pid;
        this.cid = cid;
        this.evaluation = evaluation;
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
}
