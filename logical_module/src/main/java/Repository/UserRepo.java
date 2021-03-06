package Repository;

import Model.ConferenceParticipant;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String bobTheBuilder="select * from conferenceparticipant";
        ArrayList<ConferenceParticipant> peopleThatFeelSuperior=new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean hasPayedFee = rs.getBoolean("haspayedfee");
                String cardNumber=rs.getString("cardnumber");
                String affiliation=rs.getString("affiliation");
                String webPage=rs.getString("webpage");
                String role = rs.getString("role");
                ConferenceParticipant jeanJacques=new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
                peopleThatFeelSuperior.add(jeanJacques);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return peopleThatFeelSuperior;
    }

    public ConferenceParticipant findOne(Integer id) {
        ConferenceParticipant participant = null;
        String query = "select * from conferenceparticipant where id=?";
        try(var connection = DriverManager.getConnection(url, username, password);
            var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            String username = rs.getString("username");
            String password = rs.getString("password");
            boolean hasPayedFee = rs.getBoolean("haspayedfee");
            String cardNumber=rs.getString("cardnumber");
            String affiliation=rs.getString("affiliation");
            String webPage=rs.getString("webpage");
            String role = rs.getString("role");
            participant =new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
            participant.setId(id);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return participant;
    }

    public void update(ConferenceParticipant participant) {
        String query = "update conferenceparticipant set name=?, username=?, password=?, haspayedfee=?, cardnumber=?, affiliation=?, webpage=?, role=? where id=?";
        try(var connection = DriverManager.getConnection(url, username, password);
            var ps = connection.prepareStatement(query)) {

            ps.setString(1, participant.getName());
            ps.setString(2, participant.getUsername());
            ps.setString(3, participant.getPassword());
            ps.setBoolean(4, participant.isHasPayedFee());
            ps.setString(5, participant.getCardNumber());
            ps.setString(6, participant.getAffiliation());
            ps.setString(7, participant.getWebPage());
            ps.setInt(9, participant.getId());
            ps.setString(8, participant.getRole());

            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void payFee(Integer id){
        String query = "update conferenceparticipant set  haspayedfee=true where id=?";
        try(var connection = DriverManager.getConnection(url, username, password);
            var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
    public boolean checkCreds(String username, String password, String role){
        ArrayList<ConferenceParticipant> p= (ArrayList<ConferenceParticipant>) this.findAll();
        for (ConferenceParticipant x:p) {
            if(x.getUsername().equals(username) && x.getPassword().equals(password) && x.getRole().equals(role)) return true;
        }
        return false;
    }

    public ConferenceParticipant findOne(String username1, String password1) {
        ConferenceParticipant participant = null;
        String query = "select * from conferenceparticipant where username=? and password=?";
        try(var connection = DriverManager.getConnection(url, username, password);
            var ps = connection.prepareStatement(query)) {
            ps.setString(1, username1);
            ps.setString(2, password1);
            var rs = ps.executeQuery();
            rs.next();
            Integer id=rs.getInt("id");
            String name = rs.getString("name");
            String username = rs.getString("username");
            String password = rs.getString("password");
            boolean hasPayedFee = rs.getBoolean("haspayedfee");
            String cardNumber=rs.getString("cardnumber");
            String affiliation=rs.getString("affiliation");
            String webPage=rs.getString("webpage");
            String role = rs.getString("role");
            participant =new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return participant;
    }

    public void add(ConferenceParticipant participant) {
        String query = "insert into conferenceparticipant(id,name, username, password, haspayedfee, role) " +
                "values(?,?,?,?,?,?)";
        try(var connection = DriverManager.getConnection(url, username, password);
            var ps = connection.prepareStatement(query)) {

            ps.setInt(1, participant.getId());
            ps.setString(2, participant.getName());
            ps.setString(3, participant.getUsername());
            ps.setString(4, participant.getPassword());
            ps.setBoolean(5, participant.isHasPayedFee());
            ps.setString(6, participant.getRole());

            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public int getNextId() {
        String query = "select max(id)+1 from conferenceparticipant";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query);
             var result = ps.executeQuery()) {
            result.next();
            return result.getInt(1);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return 1;
    }

}
