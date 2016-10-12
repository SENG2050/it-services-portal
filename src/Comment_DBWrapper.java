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
public class Comment_DBWrapper extends DBWrapper {
    private Comment comment;

    public Comment_DBWrapper() {
        this.setTableName("comments");
    }

    //-----------
    // Accessors
    //-----------

    /**
     * getComments()
     * Returns a list of all comments in database
     *
     * @return Comment[]
     */
    public Comment[] getComments() {
        List<Object> data = this.getAll();

        return (Comment[]) data.toArray(new Comment[data.size()]);
    }

    /**
     * getById()
     * Returns the comment by specific id
     *
     * @param id int
     * @return Comment
     */
    public Comment getCommentById(int id) {

        return Comment.class.cast(this.getOneBy("id", id));
    }

    /**
     * getCommentByIssueId()
     * Returns the comments belonging to a specific issue
     *
     * @param id int
     * @return Comment[]
     */
    public Comment[] getCommentByIssueId(int id) {
        List<Object> data = this.getManyBy("issueId", id);

        return (Comment[]) data.toArray(new Comment[data.size()]);
    }

    //-----------
    // Mutators
    //-----------

    /**
     * Create a new comment
     * @param comment
     * @return
     */
    public boolean createNewComment(Comment comment) {
        this.comment = comment;
        return this.insertRow();
    }

    /**
     * Update and existing comment
     * @param comment
     * @return
     */
    public boolean updateComment(Comment comment) {
        this.comment = comment;
        return this.updateRow(comment.getCommentId());
    }

    /**
     * Delete a comment by id
     * @param id
     * @return
     */
    public boolean deleteComment(int id) {
        return this.delete(id);
    }

    /**
     * mapRowToObject()
     * Maps a row to a Issue
     *
     * @param rs ResultSet
     * @return Issue
     */
    protected Comment mapRowToObject(ResultSet rs) {
        try {
            return new Comment(
                    rs.getInt("id"),
                    rs.getInt("issueId"),
                    rs.getString("comment"),
                    rs.getDate("created")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * Maps an Comment to an SQL update statement
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToUpdateValues() {
        Map<String, String> values = new HashMap<>();
        values.put("values", "COMMENT='" + this.comment.getComment() + "'");
        return values;
    }

    /**
     * Maps an Comment to an SQL insert statement
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToInsertValues() {
        Map<String, String> values = new HashMap<>();
        values.put("columns", "issueId, comment, created");
        values.put("values", this.comment.getIssueId() + ", " +
                "'" + this.comment.getComment() + "', " +
                "'" + this.comment.getCreated() + "'");
        return values;
    }
}
