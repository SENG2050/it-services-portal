import java.sql.ResultSet;
import java.util.List;

/**
 * Status_DBWrapper
 * Extends DBWrapper to handle Status specific requests
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class Status_DBWrapper extends DBWrapper {

    public Status_DBWrapper() {
        this.setTableName("issueStatuses");
    }

    //-----------
    // Accessors
    //-----------

    /**
     * getStatuses()
     * Returns a list of all Status in database
     *
     * @return Status[]
     */
    public Status[] getStatuses() {
        List<Object> data = this.getAll();

        return (Status[]) data.toArray(new Status[data.size()]);
    }

    /**
     * getStatusById()
     * Returns the status by specific id
     *
     * @param id int
     * @return Status
     */
    public Status getStatusById(int id) {
        return Status.class.cast(this.getOneBy("id", id));
    }

    /**
     * getStatusByTitle()
     * Returns the status by specific title
     *
     * @param title String
     * @return Status
     */
    public Status getStatusByTitle(String title) {
        return Status.class.cast(this.getOneBy("title", title));
    }

    /**
     * runQuery()
     * Returns the queried results
     *
     * @return Status[]
     */
    public Status[] runQuery() {
        List<Object> data = this.run();

        return (Status[]) data.toArray(new Status[data.size()]);
    }

    //-----------
    // Helper
    //-----------

    /**
     * mapRowToObject()
     * Maps a row to a Role
     *
     * @param rs ResultSet
     * @return Category
     */
    protected Status mapRowToObject(ResultSet rs) {
        try {
            return new Status(
                    rs.getInt("id"),
                    rs.getString("title")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
