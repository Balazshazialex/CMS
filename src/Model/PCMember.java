package Model;

public class PCMember  extends ConferenceParticipant implements IPCMember{
    protected String name;
    protected String username;
    protected String password;
    protected boolean hasPayedFee;
    protected String cardNumber;
    protected String affiliation;
    protected String webPage;

    public PCMember(String name, String username, String password) {
        super(name, username, password);
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

    @Override
    public void uploadPersonalInformation(String name, String affiliation, String emailAdress, String personalWebPage) {
        this.setName(name);
        this.setUsername(emailAdress);
        this.setAffiliation(affiliation);
        this.setWebPage(webPage);
    }

    @Override
    public void evaluatePaper() {

    }

    @Override
    public void bidProposal() {

    }

    @Override
    public void chat() {

    }

    @Override
    public void payRegistrationFee(String cardNumber) {
        this.setCardNumber(cardNumber);
    }
}
