import javax.naming.Context;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import javax.naming.InitialContext;

/**
 * PortalBean.java
 *
 * Main portal bean to encapsulate session and provide view data
 */
public class PortalBean {
    private User user;
    private DataSource dataSource;
    private Connection connection;


    public PortalBean() {
        this.user = null;
    }

    /**
     * getUser
     * getter function
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * setUser
     * setter function
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public User getUserLogin(String email) {
        try {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, email);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("role"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                return user;
            }
        } catch (Exception ex) {
            System.out.println("Error:> "+ex.getMessage());
        }
        return null;
    }

    public Issue getIssue(int issueId) {
        try {
            String query = "SELECT * FROM issues WHERE issueID = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1, issueId);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Issue issue = new Issue(
                        rs.getInt("issueId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("resolution"),
                        rs.getInt("category"),
                        rs.getInt("userId"),
                        rs.getInt("status"),
                        rs.getDate("created"),
                        rs.getDate("resolved")
                );
                return issue;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public List<Issue> getIssueList(int category) {
        List<Issue> list = new LinkedList<>();
        try {
            String query = "SELECT * FROM issues WHERE category = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1, category);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Issue issue = new Issue(
                        rs.getInt("issueId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("resolution"),
                        rs.getInt("category"),
                        rs.getInt("userId"),
                        rs.getInt("status"),
                        rs.getDate("created"),
                        rs.getDate("resolved")
                );
                list.add(issue);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public void saveIssue(Issue issue) {
        try {
            String query = "INSERT INTO issues VALUES ?, ?, ?, ?, ?, ?, ?, ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, issue.getTitle());
            s.setString(2, issue.getDescription());
            s.setString(3, issue.getResolution());
            s.setInt(4, issue.getCategory());
            s.setInt(5, issue.getUserId());
            s.setInt(6, issue.getStatus());
            s.setDate(7, new java.sql.Date(issue.getCreated().getTime()));
            s.setDate(8, new java.sql.Date(issue.getResolved().getTime()));
            s.executeUpdate();
        }catch (Exception ex) {

        }
    }

    public void updateIssue(Issue issue) {
        try {
            String query = "UPDATE issues SET resolution = ?, status = ?, resolved = ? WHERE userId = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, issue.getResolution());
            s.setInt(2, issue.getStatus());
            s.setDate(3, new java.sql.Date(issue.getResolved().getTime()));
            s.setInt(4, issue.getUserId());
            s.executeUpdate();
        }catch (Exception ex) {

        }
    }

    /**
     * getComments
     * gets all comments attached to given issue
     * @param issueId
     * @return
     */
    public List<Comment> getComments(int issueId) {
        List<Comment> list = new LinkedList<>();
        try {
            String query = "SELECT * FROM comments WHERE issueID = ?";
            PreparedStatement s =
                    connection.prepareStatement(query);
            s.setInt(1, issueId);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("commentId"),
                        rs.getInt("issueId"),
                        rs.getString("comment"),
                        rs.getDate("created")
                );
                list.add(comment);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    /**
     * postComment
     * saves given comment in DB
     * @param comment
     */
    public void postComment(Comment comment) {
        try {
            String query = "INSERT INTO comments VALUES ?, ?, ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1, comment.getIssueId());
            s.setString(2, comment.getComment());
            s.setDate(3, new java.sql.Date(comment.getCreated().getTime()));
            s.executeUpdate();
        }catch (Exception ex) {

        }
    }

    public void openDBConnection() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/seng2050a3_java");
            this.connection = dataSource.getConnection();
        } catch (Exception ex) {
            // Handle ex here
            System.out.println("Error:> "+ex.getMessage());
        }
    }

    public void closeDBConnection() {
        try {
            this.connection.close();
        } catch (Exception ex) {
            // Handle ex here
        }
    }
}
