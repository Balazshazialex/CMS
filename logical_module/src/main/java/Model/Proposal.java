package Model;

public class Proposal {

    private int id;
    private int conferenceId;
    private int authorId;
    private String name;
    private String listOfAuthors;
    private String metaInfo;
    private String abstractPaper;
    private String fullPaper;
    private String keywords;
    private String topics;
    private boolean request_eval = false;

    public Proposal(int id, int conferenceId, int authorId, String name,
                    String listOfAuthors, String metaInfo, String abstractPaper,
                    String fullPaper, String keywords, String topics, boolean request_eval) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.authorId = authorId;
        this.name = name;
        this.listOfAuthors = listOfAuthors;
        this.metaInfo = metaInfo;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.keywords = keywords;
        this.topics = topics;
        this.request_eval = request_eval;
    }

    public boolean isRequest_eval() {
        return request_eval;
    }

    public void setRequest_eval(boolean request_eval) {
        this.request_eval = request_eval;
    }

    public Proposal(int id, int conferenceId, int authorId, String name, String listOfAuthors, String metaInfo,
                    String abstractPaper, String fullPaper, String keywords, String topics) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.authorId = authorId;
        this.name = name;
        this.listOfAuthors = listOfAuthors;
        this.metaInfo = metaInfo;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.keywords = keywords;
        this.topics = topics;
    }

    public Proposal(int conferenceId, int authorId, String name, String listOfAuthors, String metaInfo,
                    String abstractPaper, String fullPaper, String keywords, String topics) {
        this.conferenceId = conferenceId;
        this.authorId = authorId;
        this.name = name;
        this.listOfAuthors = listOfAuthors;
        this.metaInfo = metaInfo;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.keywords = keywords;
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListOfAuthors() {
        return listOfAuthors;
    }

    public void setListOfAuthors(String listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public void setAbstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
    }

    public String getFullPaper() {
        return fullPaper;
    }

    public void setFullPaper(String fullPaper) {
        this.fullPaper = fullPaper;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", conferenceId=" + conferenceId +
                ", authorId=" + authorId +
                ", name='" + name + '\'' +
                ", listOfAuthors='" + listOfAuthors + '\'' +
                ", metaInfo='" + metaInfo + '\'' +
                ", abstractPaper='" + abstractPaper + '\'' +
                ", fullPaper='" + fullPaper + '\'' +
                ", keywords='" + keywords + '\'' +
                ", topics='" + topics + '\'' +
                '}';
    }
}
