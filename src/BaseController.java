import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * BaseController
 * Handles portal bean and session management
 */
public class BaseController extends HttpServlet {
    protected PortalBean portalBean;
    protected HttpSession session;
    protected User user;
    protected boolean loggedIn;
    protected Properties properties;
    protected String baseURL;

    public BaseController() {
        this.properties = new Properties();

        try {
            this.properties.load(this.getClass().getResourceAsStream("/config/config.properties"));
        } catch (IOException ex) {
            System.out.println("The system could not find the config.properties file.");
        }

        this.setPortalBean(new PortalBean());
        this.setSession(null);
        this.setUser(null);
        this.setLoggedIn(false);
        this.setBaseURL(this.properties.getProperty("baseURL"));
    }

    protected PortalBean getPortalBean() {
        return portalBean;
    }

    private void setPortalBean(PortalBean portalBean) {
        this.portalBean = portalBean;
    }

    protected HttpSession getSession() {
        return session;
    }

    private void setSession(HttpSession session) {
        this.session = session;
    }

    protected User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        if (this.session != null) {
            this.session.setAttribute("user", user);
        }
    }

    protected boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getDefaultData(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getDefaultData(request);
    }

    /**
     * Helper function to populate request with needed info
     *
     * @param request HttpServletRequest
     */
    private void getDefaultData(HttpServletRequest request) throws java.io.IOException {
        this.setSession(request.getSession());
        this.setUser((User) this.session.getAttribute("user"));
        this.setLoggedIn(this.user != null);

        // User has a notification/issue has been updated
        if (this.getUser() != null) {
            // Refresh User
            this.setUser(this.portalBean.users.getById(this.getUser().getUserId()));
            // Check Notification
            if (this.getUser().hasNotification()) {
                // Set Notification
                request.setAttribute("notification", this.user.getNotificationText());
                // Toggle flag on user
                User user = this.getUser();
                user.setNotification(false);
                user.setNotificationText("");
                this.portalBean.users.updateUser(user);
            }
        }

        request.setAttribute("user", this.getUser());
        request.setAttribute("loggedIn", this.isLoggedIn());
        request.setAttribute("baseURL", this.getBaseURL());
    }

    protected boolean checkPermissionsAndRedirect(HttpServletRequest request, HttpServletResponse response, boolean loggedIn, boolean user, boolean admin) throws java.io.IOException {
        if (loggedIn) {
            if (this.isLoggedIn()) {
                if (user && this.getUser().isUser()) {
                    return true;
                } else if (admin && this.getUser().isAdmin()) {
                    return true;
                } else {
                    response.sendRedirect(this.getBaseURL());

                    return false;
                }
            } else {
                response.sendRedirect(this.getBaseURL() + "login?r=" + URLEncoder.encode(request.getRequestURL() + "?" + request.getQueryString(), "UTF-8"));

                return false;
            }
        }

        return true;
    }
}
