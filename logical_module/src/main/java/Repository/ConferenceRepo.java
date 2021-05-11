package Repository;

import Model.Conference;
import Model.ConferenceParticipant;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class ConferenceRepo {
    private final String url;
    private final String username;
    private final String password;

    public ConferenceRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<Conference> findAll() {
        String bobTheBuilder = "select * from conference";
        ArrayList<Conference> conferences = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date startdate = rs.getDate("startdate");
                Date enddate = rs.getDate("enddate");
                Date callforpapers = rs.getDate("callforpapers");
                Date proposaldeadline = rs.getDate("proposaldeadline");
                Date fullpaperdeadline = rs.getDate("fullpaperdeadline");
                int phase=rs.getInt("phase");
                Conference jeanJacques = new Conference(id, name, startdate, enddate, callforpapers, proposaldeadline, fullpaperdeadline,phase);
                conferences.add(jeanJacques);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conferences;
    }

    public Conference findOne(Integer id) {
        Conference conference = null;
        String query = "select * from conference where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            Date startdate = rs.getDate("startdate");
            Date enddate = rs.getDate("enddate");
            Date callforpapers = rs.getDate("callforpapers");
            Date proposaldeadline = rs.getDate("proposaldeadline");
            Date fullpaperdeadline = rs.getDate("fullpaperdeadlien");
            int phase=rs.getInt("phase");
            conference =new Conference(id, name, startdate, enddate, callforpapers, proposaldeadline, fullpaperdeadline,phase);
            conference.setId(id);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conference;
    }

    public void update(Conference participant) {
        String query = "update conference set name=?, startdate=?, enddate=?, callforpapers=?, proposaldeadline=?,fullpaperdeadline=?, phase=? where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setString(1, participant.getName());
            ps.setDate(2, (java.sql.Date) participant.getStartDate());
            ps.setDate(3, (java.sql.Date) participant.getEndDate());
            ps.setDate(4, (java.sql.Date) participant.getCallForPapers());
            ps.setDate(5, (java.sql.Date) participant.getProposalDeadline());
            ps.setDate(6, (java.sql.Date) participant.getFullpaperDeadline());
            ps.setInt(7,participant.getPhase());
            ps.setInt(8, participant.getId());
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void add(Conference conference){
        String query = "insert into conference values(?,?,?,?,?,?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, conference.getId());
            ps.setString(2, conference.getName());
            ps.setDate(3, (java.sql.Date) conference.getStartDate());
            ps.setDate(4, (java.sql.Date) conference.getEndDate());
            ps.setDate(5, (java.sql.Date) conference.getCallForPapers());
            ps.setDate(6, (java.sql.Date) conference.getProposalDeadline());
            ps.setDate(7, (java.sql.Date) conference.getFullpaperDeadline());
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void del(Integer id){
        String query = "delete from conference where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
    public int getNextId() {
        String query = "select max(id)+1 from conference";
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
