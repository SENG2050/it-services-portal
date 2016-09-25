import javax.servlet.RequestDispatcher;
import java.io.IOException;

/**
 * AuthController.java
 *
 * Handles application entry point and authentication
 */
public class AuthController extends javax.servlet.http.HttpServlet {

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // Login for is submitted

        // Init Dispatcher
        RequestDispatcher dispatcher;

        // First pull in data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PortalBean bean = (PortalBean)request.getSession().getAttribute("PortalBean");

        if (username != null && password != null) {
            // Query user in DB
            // @TODO: bean.runQuery

            // If usernames exist and password matches, instantiate a new user instance
            User user = new User();
                // Fill with query data

            // Add user to bean
            bean.setUser(user);

            // Save updated bean to session
            request.getSession().setAttribute("PortalBean", bean);

            // Setup dispatcher
            dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        } else {
            // Add error message for failed login
            request.setAttribute("errorMessage", "Failed Login");

            // Setup dispatcher
            dispatcher = getServletContext().getRequestDispatcher("/");
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // Init Dispatcher
        RequestDispatcher dispatcher;

        // Check if session exists (already logged in)
        if (request.getSession().getAttribute("PortalBean") != null) {
            // Session exists - redirect to home page
            // *** NOTE: In real cases we would also need to check for auth expiry etc
            dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        } else {
            // Session doesnt exist - go to login
            // ***NOTE: dont need to instantiate bean, index.jsp will do that for us
            dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }
}
