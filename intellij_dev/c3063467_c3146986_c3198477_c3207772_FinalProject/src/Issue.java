import java.util.Date;

/**
 * Created by b8ne on 25/09/2016.
 */
public class Issue {
    private int issueId;
    private String title;
    private String description;
    private String resolution;
    private int category;
    private int userId;
    private int Status;
    private Date created;
    private Date resolved;

    public Issue() {

    }

    public Issue(int issueId, String title, String description, String resolution, int category, int userId, int status, Date created, Date resolved) {
        this.issueId = issueId;
        this.title = title;
        this.description = description;
        this.resolution = resolution;
        this.category = category;
        this.userId = userId;
        Status = status;
        this.created = created;
        this.resolved = resolved;
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

    public int getCategory() {
        return category;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

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
}
