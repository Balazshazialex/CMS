package Repository;

import Model.ConferenceParticipant;
import Model.Proposal;
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
                String recommendations = rs.getString("recommendations");

                Review jeanJacques = new Review(id, pid, cid, evaluation, recommendations);
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
            String recommendations = rs.getString("recommendations");
            conference = new Review(id, pid, cid, evaluation, recommendations);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conference;
    }

    public Review findOne_cid_pid(Integer pid, Integer cid) {
        Review review = null;
        String query = "select * from review where pid=? and cid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, pid);
            ps.setInt(2, cid);
            var rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                var evaluation = rs.getString("evaluation");
                String recommendations = rs.getString("recommendations");
                review = new Review(id, pid, cid, evaluation, recommendations);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return review;
    }

    public void add(Review review) {
        String query = "insert into review(pid,evaluation,cid,id,recommendations) values(?,?,?,?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, review.getPid());
            ps.setString(2, review.getEvaluation());
            ps.setInt(3, review.getCid());
            ps.setInt(4, review.getId());
            ps.setString(5, review.getRecommendations());
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

    public List<ConferenceParticipant> findParticipantsAssignedToReview(int proposalId) {
        String bobTheBuilder="select conferenceparticipant.* from assignedforreview, conferenceparticipant " +
                "where proposalid=? and id=assignedforreview.participantid";
        ArrayList<ConferenceParticipant> peopleThatFeelSuperior=new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder)) {

            ps.setInt(1, proposalId);
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
                ConferenceParticipant jeanJacques=new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
                peopleThatFeelSuperior.add(jeanJacques);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return peopleThatFeelSuperior;
    }

    public List<Proposal> findProposalAssignedToReview(int participantId) {
        String bobTheBuilder="select proposal.* from assignedforreview, proposal " +
                "where participantid=? and proposalid=proposal.id";
        ArrayList<Proposal> proposals=new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder)) {

            ps.setInt(1, participantId);
            var rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int conferenceId = rs.getInt("conferenceid");
                int authorId = rs.getInt("authorid");
                String name = rs.getString("name");
                String listOfAuthors = rs.getString("listauthors");
                String metaInfo = rs.getString("metainfo");
                String abstractPaper = rs.getString("abstract");
                String fullPaper = rs.getString("fullpaper");
                String keywords = rs.getString("keywords");
                String topics = rs.getString("topics");
                boolean bool=rs.getBoolean("closer_eval");
                Proposal proposal = new Proposal(id, conferenceId, authorId, name, listOfAuthors, metaInfo,
                        abstractPaper, fullPaper, keywords, topics,bool);
                proposals.add(proposal);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return proposals;
    }

    public void addAssignedReview( int participantId, int proposalId) {
        String query = "insert into assignedforreview(participantid, proposalid) values (?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, participantId);
            ps.setInt(2, proposalId);
            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
