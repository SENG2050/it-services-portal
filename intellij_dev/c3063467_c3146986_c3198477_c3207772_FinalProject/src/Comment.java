import java.util.Date;

/**
 * Created by b8ne on 25/09/2016.
 */
public class Comment {
    private int commentId;
    private int issueId;
    private String comment;
    private Date created;

    public Comment() {
        this(0, 0, "", new Date(1/1/00));
    }

    public Comment(int commentId, int issueId, String comment, Date created) {
        this.commentId = commentId;
        this.issueId = issueId;
        this.comment = comment;
        this.created = created;
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
}
