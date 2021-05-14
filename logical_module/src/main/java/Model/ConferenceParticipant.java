package Model;
public class ConferenceParticipant {
    protected int id;
    protected String name;
    protected String username;
    protected String password;
    protected boolean hasPayedFee;
    protected String cardNumber;
    protected String affiliation;
    protected String webPage;
    protected String role;

    public ConferenceParticipant(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.hasPayedFee=false;
    }

    public ConferenceParticipant(int id, String name, String username, String password, boolean hasPayedFee, String cardNumber, String affiliation, String webPage, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.hasPayedFee = hasPayedFee;
        this.cardNumber = cardNumber;
        this.affiliation = affiliation;
        this.webPage = webPage;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasPayedFee() {
        return hasPayedFee;
    }

    public void setHasPayedFee(boolean hasPayedFee) {
        this.hasPayedFee = hasPayedFee;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void payRegistrationFee(String cardNumber){

    }

    public void uploadPersonalInformation(String name, String affiliation, String emailAddress, String personalWebPage) {
        this.setName(name);
        this.setUsername(emailAddress);
        this.setAffiliation(affiliation);
        this.setWebPage(personalWebPage);
    }

    @Override
    public String toString() {
        return "ConferenceParticipant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hasPayedFee=" + hasPayedFee +
                ", cardNumber='" + cardNumber + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", webPage='" + webPage + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
