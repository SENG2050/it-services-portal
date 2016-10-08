import javax.naming.Context;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import javax.naming.InitialContext;

/**
 * PortalBean.java
 * <p>
 * Main portal bean to encapsulate session and provide view data
 */
public class PortalBean {
    public User_DBWrapper users;
    public Issue_DBWrapper issues;

    public PortalBean() {
        this.users = new User_DBWrapper();
        this.issues = new Issue_DBWrapper();
    }

    public User_DBWrapper getUsers() {
        return users;
    }

    public void setUsers(User_DBWrapper users) {
        this.users = users;
    }

    public Issue_DBWrapper getIssues() {
        return issues;
    }

    public void setIssues(Issue_DBWrapper issues) {
        this.issues = issues;
    }
}
