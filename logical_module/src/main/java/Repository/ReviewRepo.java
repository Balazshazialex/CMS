package Repository;

import Model.Review;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepo {
    private final String url;
    private final String username;
    private final String password;

    public ReviewRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<Review> findAll() {
        String bobTheBuilder = "select * from review";
        ArrayList<Review> conferences = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int pid = rs.getInt("pid");
                int cid = rs.getInt("cid");
                String evaluation = rs.getString("evaluation");

                Review jeanJacques = new Review(id, pid, cid, evaluation);
                conferences.add(jeanJacques);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conferences;
    }

    public Review findOne(Integer id) {
        Review conference = null;
        String query = "select * from review where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
            int pid = rs.getInt("pid");
            int cid = rs.getInt("cid");
            var evaluation = rs.getString("evaluation");
            conference = new Review(id, pid, cid, evaluation);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conference;
    }

    public Review findOne_cid_pid(Integer pid, Integer cid) {
        Review conference = null;
        String query = "select * from review where pid=? and cid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, pid);
            ps.setInt(2, cid);
            var rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            var evaluation = rs.getString("evaluation");
            conference = new Review(id, pid, cid, evaluation);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conference;
    }

    public void add(Review conference) {
        String query = "insert into review(pid,evaluation,cid,id) values(?,?,?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, conference.getPid());
            ps.setString(2, conference.getEvaluation());
            ps.setInt(3, conference.getCid());
            ps.setInt(4, conference.getId());
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void update_eval(Integer id,String eval){
        String query = "update review set evaluation=? where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(2, id);
            ps.setString(1, eval);
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public int getNextId() {
        String query = "select max(id)+1 from review";
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
