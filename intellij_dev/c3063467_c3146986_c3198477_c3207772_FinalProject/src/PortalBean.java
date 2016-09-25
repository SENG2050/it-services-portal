/**
 * PortalBean.java
 *
 * Main portal bean to encapsulate session and provide view data
 */
public class PortalBean {
    private User user;
    private DBWrapper dbWrapper;

    public PortalBean() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DBWrapper getDbWrapper() {
        return dbWrapper;
    }

    public void setDbWrapper(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }
}
