package Repository;

import Model.Proposal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProposalRepo {
    private final String url;
    private final String username;
    private final String password;

    public ProposalRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void add(Proposal proposal) {
        String query = "insert into " +
                "proposal(conferenceid, authorid, name, listauthors, metainfo, abstract, fullpaper, keywords, topics) " +
                "values(?,?,?,?,?,?,?,?,?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, proposal.getConferenceId());
            ps.setInt(2, proposal.getAuthorId());
            ps.setString(3, proposal.getName());
            ps.setString(4, proposal.getListOfAuthors());
            ps.setString(5, proposal.getMetaInfo());
            ps.setString(6, proposal.getAbstractPaper());
            ps.setString(7, proposal.getFullPaper());
            ps.setString(8, proposal.getKeywords());
            ps.setString(9, proposal.getTopics());

            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Proposal> findAll() {
        String query = "select * from proposal";
        List<Proposal> proposals = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query);
             var rs = ps.executeQuery()) {
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
                int evaluation_result = rs.getInt("evaluation_result");
                Proposal proposal = new Proposal(id, conferenceId, authorId, name, listOfAuthors, metaInfo,
                        abstractPaper, fullPaper, keywords, topics,bool);
                proposal.setEvaluation_result(evaluation_result);
                proposals.add(proposal);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return proposals;
    }


    public Proposal findOne(Integer id) {
        Proposal proposal = null;
        String query = "select * from proposal where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
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
            int evaluation_result = rs.getInt("evaluation_result");
            proposal = new Proposal(id, conferenceId, authorId, name, listOfAuthors, metaInfo,
                    abstractPaper, fullPaper, keywords, topics,bool);
            proposal.setEvaluation_result(evaluation_result);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return proposal;
    }

    public void update(Proposal proposal) {
        String query = "update proposal " +
                "set name=?, listauthors=?, metainfo=?, abstract=?, fullpaper=?, keywords=?, topics=?, evaluation_result=? " +
                "where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {

            ps.setString(1, proposal.getName());
            ps.setString(2, proposal.getListOfAuthors());
            ps.setString(3, proposal.getMetaInfo());
            ps.setString(4, proposal.getAbstractPaper());
            ps.setString(5, proposal.getFullPaper());
            ps.setString(6, proposal.getKeywords());
            ps.setString(7, proposal.getTopics());
            ps.setInt(8, proposal.getId());
            ps.setInt(9, proposal.getEvaluation_result());

            ps.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void updaterequest(int id) {
        String query = "update proposal set closer_eval=true where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void cancelrequest(int id) {
        String query = "update proposal set closer_eval=false where id=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Proposal findOneByAuthorIdConferenceId(Integer authorId, Integer conferenceId) {
        Proposal proposal = null;
        String query = "select * from proposal where authorid=? and conferenceid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, authorId);
            ps.setInt(2, conferenceId);
            var rs = ps.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String listOfAuthors = rs.getString("listauthors");
                String metaInfo = rs.getString("metainfo");
                String abstractPaper = rs.getString("abstract");
                String fullPaper = rs.getString("fullpaper");
                String keywords = rs.getString("keywords");
                String topics = rs.getString("topics");
                boolean bool=rs.getBoolean("closer_eval");
                int evaluation_result = rs.getInt("evaluation_result");
                proposal = new Proposal(id, conferenceId, authorId, name, listOfAuthors, metaInfo,
                        abstractPaper, fullPaper, keywords, topics,bool);
                proposal.setEvaluation_result(evaluation_result);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return proposal;
    }

    public int getNextId() {
        String query = "select max(id)+1 from proposal";
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

    public List<Proposal> findAllByConference(Integer conferenceId) {
        List<Proposal> proposals = new ArrayList<>();
        String query = "select * from proposal where conferenceid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, conferenceId);
            var rs = ps.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String listOfAuthors = rs.getString("listauthors");
                String metaInfo = rs.getString("metainfo");
                String abstractPaper = rs.getString("abstract");
                String fullPaper = rs.getString("fullpaper");
                String keywords = rs.getString("keywords");
                String topics = rs.getString("topics");
                boolean bool=rs.getBoolean("closer_eval");
                int authorId = rs.getInt("authorid");
                int evaluation_result = rs.getInt("evaluation_result");
                Proposal proposal = new Proposal(id, conferenceId, authorId, name, listOfAuthors, metaInfo,
                        abstractPaper, fullPaper, keywords, topics,bool);
                proposal.setEvaluation_result(evaluation_result);
                proposals.add(proposal);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return proposals;
    }
}
