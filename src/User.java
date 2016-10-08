/**
 * Created by b8ne on 25/09/2016.
 */
public class User {
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;

    private int userId;
    private String firstName;
    private String lastName;
    private int role;
    private String phone;
    private String email;
    private String password;

    public User() {
        this(0, "", "", 0, "", "", "");
    }

    public User(int userId, String firstName, String lastName, int role, String phone, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return this.role == ROLE_ADMIN;
    }

    public boolean isUser() {
        return this.role == ROLE_USER;
    }
}
