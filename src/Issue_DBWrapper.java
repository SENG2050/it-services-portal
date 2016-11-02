import org.apache.commons.lang.StringEscapeUtils;

import java.sql.ResultSet;
import java.util.*;

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
     *
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
     *
     * @param issue
     * @return
     */
    public boolean createNewIssue(Issue issue) {
        this.issue = issue;
        return this.insertRow();
    }

    /**
     * Update and existing issue
     *
     * @param issue
     * @return
     */
    public boolean updateIssue(Issue issue) {
        this.issue = issue;
        return this.updateRow(issue.getIssueId());
    }

    /**
     * Delete an issue by id
     *
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
                    rs.getTimestamp("created"),
                    rs.getTimestamp("resolved")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return null;
        }
    }

    /**
     * Maps an Issue to an SQL update statement
     *
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToUpdateValues() {
        StringJoiner joiner = new StringJoiner(", ");

        if (this.issue.getTitle() == null) {
            joiner.add("title = NULL");
        } else {
            joiner.add("title = '" + StringEscapeUtils.escapeSql(this.issue.getTitle()) + "'");
        }

        if (this.issue.getDescription() == null) {
            joiner.add("description = NULL");
        } else {
            joiner.add("description = '" + StringEscapeUtils.escapeSql(this.issue.getDescription()) + "'");
        }

        if (this.issue.getResolution() == null) {
            joiner.add("resolution = NULL");
        } else {
            joiner.add("resolution = '" + StringEscapeUtils.escapeSql(this.issue.getResolution()) + "'");
        }

        joiner.add("category = '" + this.issue.getCategoryId() + "'");
        joiner.add("userId = '" + this.issue.getUserId() + "'");
        joiner.add("status = '" + this.issue.getStatusId() + "'");

        if (this.issue.getCreated() == null) {
            joiner.add("created = NULL");
        } else {
            joiner.add("created = '" + StringEscapeUtils.escapeSql(this.issue.getCreated().toString()) + "'");
        }

        if (this.issue.getResolved() == null) {
            joiner.add("resolved = NULL");
        } else {
            joiner.add("resolved = '" + StringEscapeUtils.escapeSql(this.issue.getResolved().toString()) + "'");
        }

        Map<String, String> values = new HashMap<>();
        values.put("values", joiner.toString());
        return values;
    }

    /**
     * Maps an Issue to an SQL insert statement
     * This is where a new issue is created
     *
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToInsertValues() {
        StringJoiner joiner = new StringJoiner(", ");

        if (this.issue.getTitle() == null) {
            joiner.add("NULL");
        } else {
            joiner.add("'" + StringEscapeUtils.escapeSql(this.issue.getTitle()) + "'");
        }

        if (this.issue.getDescription() == null) {
            joiner.add("NULL");
        } else {
            joiner.add("'" + StringEscapeUtils.escapeSql(this.issue.getDescription()) + "'");
        }

        if (this.issue.getResolution() == null) {
            joiner.add("NULL");
        } else {
            joiner.add("'" + StringEscapeUtils.escapeSql(this.issue.getResolution()) + "'");
        }

        joiner.add("'" + this.issue.getCategoryId() + "'");
        joiner.add("'" + this.issue.getUserId() + "'");
        joiner.add("'" + this.issue.getStatusId() + "'");

        if (this.issue.getCreated() == null) {
            joiner.add("NULL");
        } else {
            joiner.add("'" + StringEscapeUtils.escapeSql(this.issue.getCreated().toString()) + "'");
        }

        if (this.issue.getResolved() == null) {
            joiner.add("NULL");
        } else {
            joiner.add("'" + StringEscapeUtils.escapeSql(this.issue.getResolved().toString()) + "'");
        }

        Map<String, String> values = new HashMap<>();
        values.put("columns", "title, description, resolution, category, userId, status, created, resolved");
        values.put("values", joiner.toString());
        return values;
    }


//    @Override
//    protected Map<String, String> mapObjectToInsertValues() {
//        Map<String, String> values = new HashMap<>();
//        values.put("columns", "title, description, resolution, category, userId, status, created, resolved");
//        values.put("values", "'" + this.issue.getTitle() + "', " +
//                "'" + this.issue.getDescription() + "', " +
//                "'" + this.issue.getResolution() + "', " +
//                this.issue.getCategory() + ", " +
//                this.issue.getUserId() + ", " +
//                this.issue.getStatus() + ", " +
//                "'" + this.issue.getCreated() + "', " +
//                "'" + this.issue.getResolved() + "'");
//        return values;
//    }
}
