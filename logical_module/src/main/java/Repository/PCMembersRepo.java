package Repository;

import Model.Conference;
import Model.PCMember;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
