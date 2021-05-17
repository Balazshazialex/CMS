package Repository;

import Model.Bid;
import Model.Conference;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidRepo {
    private final String url;
    private final String username;
    private final String password;

    public BidRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<Bid> findAll() {
        String bobTheBuilder = "select * from bid";
        ArrayList<Bid> conferences = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int pid = rs.getInt("pid");
                int cid = rs.getInt("cid");
                int evaluation = rs.getInt("evaluation");

                Bid jeanJacques = new Bid(id, pid, cid, evaluation);
                conferences.add(jeanJacques);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conferences;
    }

    public Bid findOne(Integer id) {
        Bid conference = null;
        String query = "select * from bid where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
            int pid = rs.getInt("pid");
            int cid = rs.getInt("cid");
            int evaluation = rs.getInt("evaluation");
            conference = new Bid(id, pid, cid, evaluation);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conference;
    }

    public Bid findOne_cid_pid(Integer pid, Integer cid) {
        Bid conference = null;
        String query = "select * from bid where pid=? and cid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, pid);
            ps.setInt(2, cid);
            var rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            int evaluation = rs.getInt("evaluation");
            conference = new Bid(id, pid, cid, evaluation);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conference;
    }

    public void add(Bid conference) {
        String query = "insert into bid(pid,evaluation,cid,id) values(?,?,?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, conference.getPid());
            ps.setInt(2, conference.getEvaluation());
            ps.setInt(3, conference.getCid());
            ps.setInt(4, conference.getId());
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public int getNextId() {
        String query = "select max(id)+1 from bid";
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
