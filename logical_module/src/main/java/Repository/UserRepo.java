package Repository;

import Model.ConferenceParticipant;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserRepo {
    private final String url;
    private final String username;
    private final String password;

    public UserRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public List<ConferenceParticipant> findAll(){
        String bobTheBuilder="Select * from ConferenceParticipant";
        ArrayList<ConferenceParticipant> peopleThatFeelSuperior=new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean hasPayedFee = rs.getBoolean("hasPayedFee");
                String cardNumber=rs.getString("cardNumber");
                String affiliation=rs.getString("affiliation");
                String webPage=rs.getString("webPage");
                ConferenceParticipant jean_jacques=new ConferenceParticipant(name,username,password,hasPayedFee,cardNumber,affiliation,webPage);
                peopleThatFeelSuperior.add(jean_jacques);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return peopleThatFeelSuperior;
    }
}
