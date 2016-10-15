/**
 * Category
 * Used to group Issues
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class Category {
    private int id;
    private String title;

    private Issue_DBWrapper issue_wrapper;

    public Category(int id, String title) {
        this.id = id;
        this.title = title;

        this.issue_wrapper = new Issue_DBWrapper();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getIssues
     * Returns all issues that belong to this category
     * @return Issue
     */
    public Issue[] getIssues() {
        this.issue_wrapper.reset();
        return this.issue_wrapper.getIssuesByCategory(this.id);
    }
}
