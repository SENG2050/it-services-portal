import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User
 * Models a User
 * Author: Ben Sutter
 * Updated: 15/10/16
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private int role;
    private String phone;
    private String email;
    private String password;
    private boolean notification;
    private String notificationText;

    private Role_DBWrapper role_dbWrapper;

    public User() {
        this(0, "", "", 0, "", "", "", false, "");
    }

    public User(int userId, String firstName, String lastName, int role, String phone, String email, String password, boolean notification, String notificationText) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.notification = notification;
        this.notificationText = notificationText;

        this.role_dbWrapper = new Role_DBWrapper();
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

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        this.role_dbWrapper.reset();
        return this.role_dbWrapper.getRoleById(this.role);
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

    public boolean hasNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public boolean isAdmin() {
        return this.getRole().getTitle().equals("Admin");
    }

    public boolean isUser() {
        return this.getRole().getTitle().equals("User");
    }

    // http://stackoverflow.com/a/6565597
    public String getEmailHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        byte[] digest = md5.digest(this.email.trim().toLowerCase().getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }

        return sb.toString();
    }
}
