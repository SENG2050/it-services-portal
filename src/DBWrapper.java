import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * DBWrapper
 *
 * Basic wrapper to handle all DB requests
 *
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class DBWrapper {
    protected static Connection connection;
    protected String tableName;

    /******************************************************************/
    /** Connections
    /******************************************************************/

    /**
     * open()
     *
     * Opens a connection to the database
     */
    public static void open() {
        try {
            DataSource dataSource;
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/seng2050a3_java");
            connection = dataSource.getConnection();
        } catch (Exception ex) {
            // Handle ex here
            System.out.println("Error:> "+ex.getMessage());
        }
    }

    /**
     * close()
     *
     * Closes the connection to the database
     */
    public static void close() {
        try {
            connection.close();
        } catch (Exception ex) {
            // Handle ex here
            System.out.println("Error:> "+ex.getMessage());
        }
    }

    /******************************************************************/
    /** Accessors
    /******************************************************************/

    /**
     * getAll()
     *
     * Retrieves all entries from the database
     *
     * @return
     */
    protected ResultSet getAll() {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform query
        try {
            String query = "SELECT * FROM" + tableName;
            PreparedStatement s = connection.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            return rs;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    /**
     * getById()
     *
     * Performs generic DB query on Id
     *
     * @param id
     * @return
     */
    protected ResultSet getById (int id) {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform query
        try {
            String query = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            return rs;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    /**
     * getByIntParam()
     *
     * Gets all values by integer parameter
     * @param param
     * @param value
     * @return
     */
    protected ResultSet getByIntParam (String param, int value) {
        return this.getByParam(param, value);
    }

    /**
     * getByStringParam()
     *
     * Gets all values by string parameter
     * @param param
     * @param value
     * @return
     */
    protected ResultSet getByStringParam (String param, String value) {
        return this.getByParam(param, value);
    }

    /**
     * getByParam()
     *
     * Generic gets all values by a parameter
     * @param param
     * @param value
     * @return
     */
    private <V> ResultSet getByParam (String param, V value) {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform query
        try {
            String query = "SELECT * FROM " + tableName + " WHERE "+ param + " = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setObject(1, value);
            ResultSet rs = s.executeQuery();
            return rs;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    /******************************************************************/
    /** Mutators
    /******************************************************************/

    /**
     * insert_update()
     *
     * Performs generic Update statement
     *
     * @param s
     * @return
     */
    protected boolean insert_update (PreparedStatement s) {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform statement
        try {
            int result = s.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }

    /**
     * delete
     *
     * Deletes a value from the database
     *
     * @param id
     * @return
     */
    protected boolean delete (int id) {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform statement
        try {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1, id);
            int result = s.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }
}