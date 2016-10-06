import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Issue_DBWrapper
 *
 * Extends DBWrapper to handle Issue specific requests
 *
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class Issue_DBWrapper extends DBWrapper {
    private List<Issue> list;

    public Issue_DBWrapper() {
        this.tableName = "issues";
        this.list = new LinkedList<>();
    }

    /******************************************************************/
    /** Accessors
    /******************************************************************/

    /**
     * getIssues()
     *
     * Returns a list of all issues in database
     *
     * @return
     */
    public List<Issue> getIssues () {
        try {
            ResultSet rs = this.getAll();
            if (rs != null) {
                convertResult(rs);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

    /**
     * getIssueById()
     *
     * Implements super getById to retrieve user model by id
     *
     * @param id
     * @return
     */
    public List<Issue> getIssueById (int id) {
        try {
            ResultSet rs = this.getById(id);
            if (rs != null) {
                convertResult(rs);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

    /**
     * getIssueByCategory()
     *
     * Gets all issues that belong to given category
     *
     * @param catId
     * @return
     */
    public List<Issue> getIssueByCategory(int catId) {
        try {
            ResultSet rs = this.getByIntParam("category", catId);
            if (rs != null) {
                convertResult(rs);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

    /**
     * getIssueByStatus()
     *
     * Gets all issues that have a given status
     *
     * @param status
     * @return
     */
    public List<Issue> getIssueByStatus(int status) {
        try {
            ResultSet rs = this.getByIntParam("status", status);
            if (rs != null) {
                convertResult(rs);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

    /**
     *
     */
    public void test(){
        
    }


    /******************************************************************/
    /** Mutators
     /******************************************************************/

    /**
     * convertResult()
     *
     * Private helper function to convert Result set to User List
     *
     * @param rs
     */
    private void convertResult(ResultSet rs) {
        try {
            list.clear();
            while (rs.next()) {
                Issue issue = new Issue(
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
                list.add(issue);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
