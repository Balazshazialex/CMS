package Repository;

import Controllers.ConferenceController;
import Model.Conference;
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
}
