import java.util.Date;

/**
 * Issue
 * Holds all data for an issue
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class Issue {
    private int issueId;
    private String title;
    private String description;
    private String resolution;
    private int category;
    private int userId;
    private int status;
    private Date created;
    private Date resolved;

    private Comment_DBWrapper comment_wrapper;
    private Category_DBWrapper category_wrapper;
    private Status_DBWrapper status_dbWrapper;
    private User_DBWrapper user_dbWrapper;

    public Issue() {

    }

    public Issue(int issueId, String title, String description, String resolution, int category, int userId, int status, Date created, Date resolved) {
        this.issueId = issueId;
        this.title = title;
        this.description = description;
        this.resolution = resolution;
        this.category = category;
        this.userId = userId;
        this.status = status;
        this.created = created;
        this.resolved = resolved;

        this.comment_wrapper = new Comment_DBWrapper();
        this.category_wrapper = new Category_DBWrapper();
        this.status_dbWrapper = new Status_DBWrapper();
        this.user_dbWrapper = new User_DBWrapper();
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatusId(){return status;}

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    /**
     * getComments
     * Returns all comments attached to this issue
     * @return
     */
    public Comment[] getComments () {
        this.comment_wrapper.reset();
        return this.comment_wrapper.getCommentByIssueId(this.issueId);
    }

    /**
     * getCategory
     * Returns the category model that this issue belongs to
     * @return
     */
    public Category getCategory() {
        this.category_wrapper.reset();
        return this.category_wrapper.getCategoryById(this.category);
    }

    public int getCategoryId(){
        return this.category;
    }


    /**
     * getStatus
     * Returns the status model attached to this issue
     * @return
     */
    public Status getStatus()
    {
        this.status_dbWrapper.reset();
        return this.status_dbWrapper.getStatusById(this.status);
    }

    /**
     * getUser
     * Returns the user model attached to this issue
     * @return
     */
    public User getUser() {
        this.user_dbWrapper.reset();
        return this.user_dbWrapper.getById(this.userId);
    }

    /**
     * isKBArticle
     * Helper function to check if the issue is to be shown in knowledge base
     * @return
     */
    public boolean isKBArticle() {
        return this.getStatus().getTitle().equals("Knowledge Base");
    }
}
