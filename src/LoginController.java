import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * LoginController
 *
 * Handles all login requests
 *
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class LoginController extends HttpServlet {
    private String referrer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Init Dispatcher
        RequestDispatcher dispatcher;

        // Check for referrer uris
        if (request.getSession().getAttribute("referrer") != null) {
            referrer = request.getSession().getAttribute("referrer").toString();
        } else if (request.getParameter("from") != null) {
            referrer = "/" + request.getParameter("from");
        } else {
            referrer = "/";
        }

        // Check if session exists (already logged in)
        if (request.getSession().getAttribute("PortalBean") != null) {
            // Session exists - check if it contains a user
            PortalBean bean = (PortalBean) request.getSession().getAttribute("PortalBean");
            if (bean.getUser() != null) {
                // User exists (they are logged in) redirect to requested page
                // *** NOTE: In real cases we would also need to check for auth expiry etc
                dispatcher = getServletContext().getRequestDispatcher(referrer);
            } else {
                // Bean exists but no user, treat as empty session and re-auth
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            }
        } else {
            // Session doesnt exist - create a new bean instance and initiate it
            PortalBean bean = new PortalBean();
            // Add it to the session
            request.getSession().setAttribute("PortalBean", bean);

            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Login form is submitted

        // Init Dispatcher
        RequestDispatcher dispatcher;
        boolean success = false;

        // Validate referrer
        if (referrer == null) {
            referrer = "/";
        }

        // Now pull in data
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PortalBean bean = (PortalBean) request.getSession().getAttribute("PortalBean");

        if (email != null && password != null) {
            // Query user in DB
            User user = null;
            List<User> users = bean.userDBWrapper.getUserByEmail(email);
            if (users.isEmpty()) {
                // Add error message for failed login
                request.setAttribute("errorMessage", "Email does not exist");
            } else {
                // Pull out user
                for (User u : users) {
                    user = u;
                }
                // Test password
                if (user != null && user.getPassword().equals(password)) {
                    // Success - add user to bean
                    bean.setUser(user);
                    // Save updated bean to session
                    request.getSession().setAttribute("PortalBean", bean);
                    // Flip success flag
                    success = true;
                } else {
                    // Add error message for failed login
                    request.setAttribute("errorMessage", "Incorrect password");
                }
            }
        } else {
            // Add error message for failed login
            request.setAttribute("errorMessage", "Incorrect data provided");
        }

        // Setup dispatcher
        if (success) {
            // Redirect to referrer
            response.sendRedirect(referrer);
            return;
        } else {
            // Reload Login with errors attached
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }
}
