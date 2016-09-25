import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthController.java
 *
 * Handles application entry point and authentication
 */
public class AuthController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login form is submitted

        // Init Dispatcher
        RequestDispatcher dispatcher;
        boolean success = false;

        // First pull in data
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PortalBean bean = (PortalBean)request.getSession().getAttribute("PortalBean");

        if (email != null && password != null) {
            // Query user in DB
            User user = bean.getUserLogin(email);

            // If email exists and password matches, instantiate a new user instance
            if (user != null) {
                if (user.getPassword().equals(password)) {
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
            } else {
                // Add error message for failed login
                request.setAttribute("errorMessage", "Email does not exist");
            }
        } else {
            // Add error message for failed login
            request.setAttribute("errorMessage", "Incorrect data provided");
        }

        // Setup dispatcher
        if (success) {
            dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        } else {
            dispatcher = getServletContext().getRequestDispatcher("/");
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Init Dispatcher
        RequestDispatcher dispatcher;

        // Check if session exists (already logged in)
        if (request.getSession().getAttribute("PortalBean") != null) {
            // Session exists - check if it contains a user
            PortalBean bean = (PortalBean)request.getSession().getAttribute("PortalBean");
            if (bean != null) {
                // User exists (they are logged in) redirect to home
                // *** NOTE: In real cases we would also need to check for auth expiry etc
                dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            } else {
                // Bean exists but no user, treat as empty session and re-auth
                dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            }
        } else {
            // Session doesnt exist - create a new bean instance and initiate it
            PortalBean bean = new PortalBean();
            bean.openDBConnection();
            // Add it to the session
            request.getSession().setAttribute("PortalBean", bean);

            dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }
}
