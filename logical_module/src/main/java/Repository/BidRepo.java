package Repository;

import Model.Bid;
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
}
