package Model;
public abstract class ConferenceParticipant {
    protected String name;
    protected String username;
    protected String password;
    protected boolean hasPayedFee;
    protected String cardNumber;
    protected String affiliation;
    protected String webPage;

    public ConferenceParticipant(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.hasPayedFee=false;
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

    public void payRegistrationFee(String cardNumber){

    }

}
