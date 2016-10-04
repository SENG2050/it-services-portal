import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * User_DBWrapper
 *
 * Extends DBWrapper to handle User specific requests
 *
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class User_DBWrapper extends DBWrapper {
    private List<User> list;

    public User_DBWrapper() {
        this.tableName = "users";
        this.list = new LinkedList<>();
    }

    /******************************************************************/
    /** Accessors
    /******************************************************************/

    /**
     * getUsers()
     *
     * Returns a list of all users in database
     *
     * @return
     */
    public List<User> getUsers () {
        List<User> list = new LinkedList<>();
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
     * getUserById()
     *
     * Implements super getById to retrieve user model by id
     *
     * @param id
     * @return
     */
    public List<User> getUserById (int id) {
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
     * getUserByEmail()
     *
     * Gets user based on email param
     *
     * @param email
     * @return
     */
    public List<User> getUserByEmail (String email) {
        try {
            ResultSet rs = this.getByStringParam("email", email);
            if (rs != null) {
                convertResult(rs);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

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
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("role"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                list.add(user);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
