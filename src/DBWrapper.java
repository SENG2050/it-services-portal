import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.List;
import java.util.ArrayList;

/**
 * DBWrapper
 * <p>
 * Basic wrapper to handle all DB requests
 * <p>
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class DBWrapper {
    private static Connection connection;

    private String tableName;
    private Map<String, String> where;
    private Map<String, String> sort;
    private int limit;
    private int offset;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        DBWrapper.connection = connection;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getWhere() {
        return where;
    }

    public void setWhere(Map<String, String> where) {
        this.where = where;
    }

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    //-------------
    // Connections
    //-------------

    /**
     * open()
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
            System.out.println("Error:> " + ex.getMessage());
        }
    }

    /**
     * close()
     * Closes the connection to the database
     */
    public static void close() {
        try {
            connection.close();
        } catch (Exception ex) {
            // Handle ex here
            System.out.println("Error:> " + ex.getMessage());
        }
    }

    //-----------
    // Accessors
    //-----------

    /**
     * reset()
     * Resets the query parameters to default values
     */
    public void reset() {
        this.setWhere(new HashMap<String, String>());
        this.setSort(new HashMap<String, String>());
        this.setLimit(0);
        this.setOffset(0);
    }

    /**
     * getOneBy()
     * Gets first row with column = value
     *
     * @param column String
     * @param value  V
     * @return Object
     */
    public <V> Object getOneBy(String column, V value) {
        this.reset();

        this.setLimit(1);
        this.addWhere(column, value);

        return this.mapResultToObject(this.getResult());
    }

    /**
     * getManyBy()
     * Gets all rows with column = value
     *
     * @param column String
     * @param value  V
     * @return List<Object>
     */
    public <V> List<Object> getManyBy(String column, V value) {
        this.reset();

        this.addWhere(column, value);

        return this.mapResultToObjects(this.getResult());
    }

    /**
     * getAll()
     * Retrieves all entries from the database
     *
     * @return List<Object>
     */
    public List<Object> getAll() {
        this.reset();
        ResultSet rs = this.getResult();
        return this.mapResultToObjects(rs);
    }

    /**
     * getResult()
     * Generic gets all values by a parameter
     *
     * @return ResultSet
     */
    private ResultSet getResult() {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform query
        try {
            String query = "SELECT * FROM " + this.getTableName();

            if (this.getWhere() != null && this.getWhere().size() > 0) {
                query += " WHERE ";

                StringJoiner joiner = new StringJoiner(", ");

                for (String column : this.getWhere().keySet()) {
                    joiner.add("`" + column + "` = ?");
                }

                query += joiner.toString();
            }

            if (this.getSort() != null && this.getSort().size() > 0) {
                query += " ORDER BY ";

                StringJoiner joiner = new StringJoiner(", ");

                for (String column : this.getSort().keySet()) {
                    joiner.add("`" + column + "` " + this.getSort().get(column));
                }

                query += joiner.toString();
            }

            if (this.getLimit() != 0) {
                query += " LIMIT " + this.getOffset() + ", " + this.getLimit();
            } else if (this.getOffset() != 0) {
                // See http://stackoverflow.com/a/271650
                query += " LIMIT " + this.getOffset() + ", 18446744073709551615";
            }

            PreparedStatement s = connection.prepareStatement(query);

            if (this.getWhere() != null && this.getWhere().size() > 0) {
                int index = 1;

                for (String column : this.getWhere().keySet()) {
                    s.setObject(index++, this.getWhere().get(column));
                }
            }

            return s.executeQuery();
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return null;
        }
    }

    /**
     * mapResultToObject()
     * Helper method to map a result set to an array of objects
     *
     * @param resultSet ResultSet
     * @return List<Object>
     */
    private List<Object> mapResultToObjects(ResultSet resultSet) {
        List<Object> list = new ArrayList<Object>();

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Object object = this.mapRowToObject(resultSet);

                    list.add(object);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return list;
    }

    /**
     * mapResultToObject()
     * Helper method to map a result set to an object
     *
     * @param resultSet ResultSet
     * @return Object
     */
    private Object mapResultToObject(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                if (resultSet.next()) {
                    return this.mapRowToObject(resultSet);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());

            return null;
        }
    }



    //---------
    // Helpers
    //---------

    /**
     * mapRowToObject
     * Maps a row to an object
     *
     * @param resultSet ResultSet
     * @return Object
     */
    protected Object mapRowToObject(ResultSet resultSet)
    {
        return new Object();
    }

    /**
     * Maps an object to an SQL insert statement
     * @return
     */
    protected Map<String, String> mapObjectToInsertValues() { return new HashMap<>(); }

    /**
     * Maps an object to an SQL update statement
     * @return
     */
    protected Map<String, String> mapObjectToUpdateValues() { return new HashMap<>(); }

    /**
     * addWhere()
     * Generic gets all values by a parameter
     *
     * @param column String
     * @param value  V
     */
    public <V> void addWhere(String column, V value) {
        this.getWhere().put(column, value.toString());
    }

    /**
     * addSort()
     * Generic gets all values by a parameter
     *
     * @param column    String
     * @param direction String
     */
    public <V> void addSort(String column, String direction) {
        this.getSort().put(column, direction);
    }

    //-----------
    // Mutators
    //-----------

    /**
     * Inserts a row in the database
     *
     * @return boolean
     */
    protected boolean insertRow() {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform statement
        try {
            String query = "INSERT INTO " + this.getTableName() +
                    "(" + this.mapObjectToInsertValues().get("columns") + ") " +
                    "VALUES (" +
                    this.mapObjectToInsertValues().get("values") + ");";
            PreparedStatement s = connection.prepareStatement(query);
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
     * Updates a row in the database based on Id
     * @return boolean
     */
    protected boolean updateRow(int id) {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform statement
        try {
            String query = "UPDATE " + this.getTableName() + " SET " +
                    this.mapObjectToUpdateValues().get("values") + " WHERE ID=" + id + ";";
            PreparedStatement s = connection.prepareStatement(query);
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
     * Deletes a value from the database
     *
     * @param id int
     * @return boolean
     */
    protected boolean delete(int id) {
        // Open connection if not already opened
        if (connection == null) {
            open();
        }

        // Perform statement
        try {
            String query = "DELETE FROM " + this.getTableName() + " WHERE id = ?";
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