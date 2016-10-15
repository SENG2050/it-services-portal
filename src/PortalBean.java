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
    public Comment_DBWrapper comments;
    public Category_DBWrapper categories;
    public Role_DBWrapper roles;
    public Status_DBWrapper statuses;

    public PortalBean() {
        this.users = new User_DBWrapper();
        this.issues = new Issue_DBWrapper();
        this.comments = new Comment_DBWrapper();
        this.categories = new Category_DBWrapper();
        this.roles = new Role_DBWrapper();
        this.statuses = new Status_DBWrapper();
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

    public Comment_DBWrapper getComments() {
        return comments;
    }

    public void setComments(Comment_DBWrapper comments) {
        this.comments = comments;
    }

    public Category_DBWrapper getCategories() {
        return categories;
    }

    public void setCategories(Category_DBWrapper categories) {
        this.categories = categories;
    }

    public Role_DBWrapper getRoles() {
        return roles;
    }

    public void setRoles(Role_DBWrapper roles) {
        this.roles = roles;
    }

    public Status_DBWrapper getStatuses() {
        return statuses;
    }

    public void setStatuses(Status_DBWrapper statuses) {
        this.statuses = statuses;
    }
}
