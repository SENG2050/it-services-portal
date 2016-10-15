import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Role_DBWrapper
 * Extends DBWrapper to handle User Role specific requests
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class Role_DBWrapper extends DBWrapper {

    public Role_DBWrapper() {
        this.setTableName("roles");
    }

    //-----------
    // Accessors
    //-----------

    /**
     * getRoles()
     * Returns a list of all roles in database
     *
     * @return Comment[]
     */
    public Role[] getRoles() {
        List<Object> data = this.getAll();

        return (Role[]) data.toArray(new Role[data.size()]);
    }

    /**
     * getRoleById()
     * Returns the role by specific id
     *
     * @param id int
     * @return Role
     */
    public Role getRoleById(int id) {

        return Role.class.cast(this.getOneBy("id", id));
    }

    /**
     * runQuery()
     * Returns the queried results
     *
     * @return Role[]
     */
    public Role[] runQuery() {
        List<Object> data = this.run();

        return (Role[]) data.toArray(new Role[data.size()]);
    }

    //-----------
    // Helper
    //-----------

    /**
     * mapRowToObject()
     * Maps a row to a Role
     *
     * @param rs ResultSet
     * @return Role
     */
    protected Role mapRowToObject(ResultSet rs) {
        try {
            return new Role(
                    rs.getInt("id"),
                    rs.getString("title")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
