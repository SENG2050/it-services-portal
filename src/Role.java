/**
 * Role
 * Models a users role
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class Role {
    private int id;
    private String title;

    public Role(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
