package Repository;

import Model.Bid;
import Model.ConferenceParticipant;
import Model.Proposal;

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
        ArrayList<Bid> bids = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(bobTheBuilder);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                int pcMemberId = rs.getInt("pcmemberid");
                int proposalId = rs.getInt("proposalid");
                int bidInfo = rs.getInt("bidinfo");

               Bid bid = new Bid(pcMemberId, proposalId, bidInfo);
                bids.add(bid);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return bids;
    }

    public Bid findOne(Integer PCMemberId, Integer proposalId) {
        Bid bid = null;
        String query = "select * from bid where pcmemberid=? and proposalid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, PCMemberId);
            ps.setInt(2, proposalId);
            var rs = ps.executeQuery();
            rs.next();
            int bidInfo = rs.getInt("bidinfo");
            bid = new Bid(PCMemberId, proposalId, bidInfo);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return bid;
    }

    public void add(Bid bid) {
        if(! this.exists(bid)) {
            String query = "insert into bid(pcmemberid, proposalid, bidinfo) values(?,?,?)";
            try (var connection = DriverManager.getConnection(url, username, password);
                 var ps = connection.prepareStatement(query)) {
                ps.setInt(1, bid.getPCMemberId());
                ps.setInt(2, bid.getProposalId());
                ps.setInt(3, bid.getBidInfo());
                ps.executeUpdate();

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public boolean exists(Bid bid) {
        String query = "select * from bid where pcmemberid=? and proposalid=?";
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {
            ps.setInt(1, bid.getPCMemberId());
            ps.setInt(2, bid.getProposalId());
            var rs = ps.executeQuery();
            if(rs.next()) return true;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public List<Proposal> findAllByConference(Integer conferenceId) {
        String query = "select distinct proposal.* from bid, proposal where proposalid=id and conferenceid=? and bidinfo<>-1";
        ArrayList<Proposal> proposals = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {

            ps.setInt(1, conferenceId);
            var rs = ps.executeQuery();

            while (rs.next()) {
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
                Proposal proposal = new Proposal(id, conferenceId, authorId, name, listOfAuthors, metaInfo,
                        abstractPaper, fullPaper, keywords, topics,bool);
                proposals.add(proposal);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return proposals;
    }

    public List<ConferenceParticipant> findAllParticipantsByProposal(Integer proposalId) {
        String query = "select distinct conferenceparticipant.* from bid, conferenceparticipant " +
                "where id=pcmemberid and proposalid=? and bidinfo<>-1";
        ArrayList<ConferenceParticipant> participants = new ArrayList<>();
        try (var connection = DriverManager.getConnection(url, username, password);
             var ps = connection.prepareStatement(query)) {

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
                ConferenceParticipant participant =new ConferenceParticipant(id,name,username,password,hasPayedFee,cardNumber,affiliation,webPage,role);
                participants.add(participant);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return participants;
    }

}
