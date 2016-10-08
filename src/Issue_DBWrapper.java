import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Issue_DBWrapper
 * Extends DBWrapper to handle Issue specific requests
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class Issue_DBWrapper extends DBWrapper {
    public Issue_DBWrapper() {
        this.setTableName("issues");
    }

    /**
     * getIssues()
     * Returns a list of all issues in database
     *
     * @return Issue[]
     */
    public Issue[] getIssues() {
        List<Object> data = this.getAll();

        return (Issue[]) data.toArray(new Issue[data.size()]);
    }

    /**
     * getById()
     * Returns the status with a matching id
     *
     * @param id int
     * @return Issue
     */
    public Issue getById(int id) {
        return Issue.class.cast(this.getOneBy("id", id));
    }

    /**
     * getIssuesByCategory()
     * Returns the issues with a matching category
     *
     * @param category String
     * @return Issue[]
     */
    public Issue[] getIssuesByCategory(String category) {
        List<Object> data = this.getManyBy("category", category);

        return (Issue[]) data.toArray(new Issue[data.size()]);
    }

    /**
     * getIssuesByStatus()
     * Returns the issues with a matching status
     *
     * @param status String
     * @return Issue[]
     */
    public Issue[] getIssuesByStatus(String status) {
        List<Object> data = this.getManyBy("status", status);

        return (Issue[]) data.toArray(new Issue[data.size()]);
    }

    /**
     * mapRowToObject()
     * Maps a row to a Issue
     *
     * @param rs ResultSet
     * @return Issue
     */
    protected Issue mapRowToObject(ResultSet rs) {
        try {
            return new Issue(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("descriptions"),
                    rs.getString("resolution"),
                    rs.getInt("category"),
                    rs.getInt("userId"),
                    rs.getInt("status"),
                    rs.getDate("created"),
                    rs.getDate("resolved")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return null;
        }
    }
}
