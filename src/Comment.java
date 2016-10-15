import java.util.Date;

/**
 * Comment
 * Comments left on an issue
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class Comment {
    private int commentId;
    private int issueId;
    private int userId;
    private boolean isPublic;
    private String comment;
    private Date created;

    private User_DBWrapper user_dbWrapper;

    public Comment() {
        this(0, 0, 0, true, "", new Date(1/1/00));
    }

    public Comment(int commentId, int issueId, int userId, boolean isPublic, String comment, Date created) {
        this.commentId = commentId;
        this.issueId = issueId;
        this.userId = userId;
        this.isPublic = isPublic;
        this.comment = comment;
        this.created = created;

        this.user_dbWrapper = new User_DBWrapper();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    /**
     * getUser
     * Returns the user model attached to this comment
     * @return
     */
    public User getUser() {
        this.user_dbWrapper.reset();
        return this.user_dbWrapper.getById(this.userId);
    }
}
