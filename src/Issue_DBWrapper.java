import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Issue_DBWrapper
 * Extends DBWrapper to handle Issue specific requests
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class Issue_DBWrapper extends DBWrapper {
    private Issue issue;

    public Issue_DBWrapper() {
        this.setTableName("issues");
    }

    //-----------
    // Accessors
    //-----------

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
     * getIssuesbyUserId()
     * Returns a list of all issues in database
     * @param userId int
     * @return Issue[]
     */
    public Issue[] getIssuesbyUserId(int userId) {
        List<Object> data = this.getManyBy("userId", userId);

        return (Issue[]) data.toArray(new Issue[data.size()]);
    }

    /**
     * getById()
     * Returns the status with a matching id
     *
     * @param id int
     * @return Issue
     */
    public Issue getIssueById(int id) {
        return Issue.class.cast(this.getOneBy("id", id));
    }

    /**
     * getIssuesByCategory()
     * Returns the issues with a matching category
     *
     * @param category String
     * @return Issue[]
     */
    public Issue[] getIssuesByCategory(int category) {
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

    //-----------
    // Mutators
    //-----------

    /**
     * Create a new issue
     * @param issue
     * @return
     */
    public boolean createNewIssue(Issue issue) {
        this.issue = issue;
        return this.insertRow();
    }

    /**
     * Update and existing issue
     * @param issue
     * @return
     */
    public boolean updateIssue(Issue issue) {
        this.issue = issue;
        return this.updateRow(issue.getIssueId());
    }

    /**
     * Delete an issue by id
     * @param id
     * @return
     */
    public boolean deleteIssue(int id) {
        return this.delete(id);
    }

    /**
     * runQuery()
     * Returns the queried results
     *
     * @return Issue[]
     */
    public Issue[] runQuery() {
        List<Object> data = this.run();

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
                    rs.getString("description"),
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

    /**
     * Maps an Issue to an SQL update statement
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToUpdateValues() {
        Map<String, String> values = new HashMap<>();
         values.put("values", "title='" + this.issue.getTitle() + "', " +
                "description='" + this.issue.getDescription() + "', " +
                "resolution='" + this.issue.getResolution() + "', " +
                "category=" + this.issue.getCategory() + ", " +
                "userId=" + this.issue.getUserId() + ", " +
                "status=" + this.issue.getStatus() + ", " +
                "created='" + this.issue.getCreated() + "', " +
                "resolved='" + this.issue.getResolved() + "'");
        return values;
    }

    /**
     * Maps an Issue to an SQL insert statement
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToInsertValues() {
        Map<String, String> values = new HashMap<>();
        values.put("columns", "title, description, resolution, category, userId, status, created, resolved");
        values.put("values", "'" + this.issue.getTitle() + "', " +
                "'" + this.issue.getDescription() + "', " +
                "'" + this.issue.getResolution() + "', " +
                this.issue.getCategory() + ", " +
                this.issue.getUserId() + ", " +
                this.issue.getStatus() + ", " +
                "'" + this.issue.getCreated() + "', " +
                "'" + this.issue.getResolved() + "'");
        return values;
    }
}
