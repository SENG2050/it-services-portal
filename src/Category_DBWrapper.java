import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Category_DBWrapper
 * Extends DBWrapper to handle Category specific requests
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class Category_DBWrapper extends DBWrapper {

    public Category_DBWrapper() {
        this.setTableName("issueCategories");
    }

    //-----------
    // Accessors
    //-----------

    /**
     * getCategories()
     * Returns a list of all categories in database
     *
     * @return Category[]
     */
    public Category[] getCategories() {
        List<Object> data = this.getAll();

        return (Category[]) data.toArray(new Category[data.size()]);
    }

    /**
     * getCategoryById()
     * Returns the category by specific id
     *
     * @param id int
     * @return Role
     */
    public Category getCategoryById(int id) {

        return Category.class.cast(this.getOneBy("id", id));
    }

    /**
     * runQuery()
     * Returns the queried results
     *
     * @return Category[]
     */
    public Category[] runQuery() {
        List<Object> data = this.run();

        return (Category[]) data.toArray(new Category[data.size()]);
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
    protected Category mapRowToObject(ResultSet rs) {
        try {
            return new Category(
                    rs.getInt("id"),
                    rs.getString("title")
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
