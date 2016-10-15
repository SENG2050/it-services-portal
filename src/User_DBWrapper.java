import java.sql.ResultSet;
import java.util.*;

/**
 * User_DBWrapper
 * <p>
 * Extends DBWrapper to handle User specific requests
 * <p>
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class User_DBWrapper extends DBWrapper {
    private User user;
    public User_DBWrapper() {
        this.setTableName("users");
    }

    //-----------
    // Accessors
    //-----------

    /**
     * getUsers()
     * Returns a list of all users in database
     *
     * @return User[]
     */
    public User[] getUsers() {
        List<Object> data = this.getAll();

        return (User[]) data.toArray(new User[data.size()]);
    }

    /**
     * getById()
     * Returns the user with a matching id
     *
     * @param id int
     * @return User
     */
    public User getById(int id) {
        return User.class.cast(this.getOneBy("id", id));
    }

    /**
     * getByEmail()
     * Returns the user with a matching email
     *
     * @param email String
     * @return User
     */
    public User getByEmail(String email) {
        return User.class.cast(this.getOneBy("email", email));
    }

    /**
     * runQuery()
     * Returns the queried results
     *
     * @return User[]
     */
    public User[] runQuery() {
        List<Object> data = this.run();

        return (User[]) data.toArray(new User[data.size()]);
    }

    //-----------
    // Mutators
    //-----------

    /**
     * Update and existing comment
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        this.user = user;
        return this.updateRow(user.getUserId());
    }

    /**
     * Maps an User to an SQL update statement
     * @return
     */
    @Override
    protected Map<String, String> mapObjectToUpdateValues() {
        Map<String, String> values = new HashMap<>();
        values.put("values", "NOTIFICATION='" + this.user.hasNotification() + "'");
        return values;
    }

    /**
     * mapRowToObject()
     * Maps a row to a User
     *
     * @param rs ResultSet
     * @return User
     */
    protected User mapRowToObject(ResultSet rs) {
        try {
            return new User(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("role"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getBoolean("notification")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return null;
        }
    }
}
