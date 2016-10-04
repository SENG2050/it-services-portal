import javax.naming.Context;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import javax.naming.InitialContext;

/**
 * PortalBean.java
 *
 * Main portal bean to encapsulate session and provide view data
 */
public class PortalBean {
    private User user;

    public User_DBWrapper userDBWrapper;
    public Issue_DBWrapper issueDbWrapper;


    public PortalBean() {
        this.user = null;
        this.userDBWrapper = new User_DBWrapper();
        this.issueDbWrapper = new Issue_DBWrapper();
    }

    /**
     * getUser
     * getter function
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * setUser
     * setter function
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
