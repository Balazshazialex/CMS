package Repository;

import Controllers.ConferenceController;
import Model.Conference;
import Model.ConferenceParticipant;
import Model.PCMember;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PCMembersRepo {
    private final String url;
    private final String username;
    private final String password;

    public PCMembersRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public boolean exists(Integer idConference, Integer idPCMember) {
        String query = "select * from pcmembers where participantid=? and conferenceid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, idPCMember);
            ps.setInt(2, idConference);
            var rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
    public List<Conference> get_all_conferences_assigned_to_PCM(Integer idPCMember){
        ArrayList<Conference> all=new ArrayList<>();
        String query = "select * from pcmembers where participantid=?";
        ConferenceController c=new ConferenceController();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, idPCMember);
            var rs = ps.executeQuery();
            while(rs.next()){
                var aconf_id= rs.getInt("conferenceid");
                all.add(c.findOne(aconf_id));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return all;
    }


    public void addPCMembers(Conference conference, List<ConferenceParticipant> participants) {
        String query = "insert into pcmembers values(?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            for(ConferenceParticipant participant : participants) {
                if(! this.exists(conference.getId(), participant.getId())) {
                    ps.setInt(1, participant.getId());
                    ps.setInt(2, conference.getId());
                    ps.executeUpdate();
                }
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<ConferenceParticipant> findAllByConference(Conference conference) {
        String query = "select conferenceparticipant.* from pcmembers, conferenceparticipant where conferenceid = ? and participantid = id";
        List<ConferenceParticipant> participants = new ArrayList<>();

        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, conference.getId());
            var rs = ps.executeQuery();
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
                ConferenceParticipant participant =new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
                participants.add(participant);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return participants;
    }

    public void removePCMember(Conference conference, ConferenceParticipant PCMember) {
        String query = "delete from pcmembers where conferenceid = ? and participantid = ?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {

            ps.setInt(1, conference.getId());
            ps.setInt(2, PCMember.getId());
            ps.executeUpdate();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<ConferenceParticipant> findChairsByConference(Conference conference) {
        String query = "select conferenceparticipant.* from pcmembers, conferenceparticipant " +
                "where conferenceid = ? and participantid = id and chair=true";
        List<ConferenceParticipant> participants = new ArrayList<>();

        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, conference.getId());
            var rs = ps.executeQuery();
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
                ConferenceParticipant participant =new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
                participants.add(participant);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return participants;
    }
}
