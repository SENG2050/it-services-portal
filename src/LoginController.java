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
 * Handles all login requests
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class LoginController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        String redirect;

        // Check for redirect
        if (request.getParameter("r") != null) {
            redirect = this.getBaseURL() + request.getParameter("r");
        } else {
            redirect = this.getBaseURL();
        }

        if (this.isLoggedIn()) {
            response.sendRedirect(redirect);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        String redirect;

        // Check for redirect
        if (request.getParameter("r") != null) {
            redirect = this.getBaseURL() + request.getParameter("r");
        } else {
            redirect = this.getBaseURL();
        }

        if (this.isLoggedIn()) {
            response.sendRedirect(redirect);
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email != null && password != null) {

                // Query user in DB
                User user = this.getPortalBean().getUsers().getByEmail(email);

                // Check if user was found
                if (user != null) {

                    // Test password
                    if (user.getPassword().equals(password)) {

                        // Success - add user to bean
                        this.setUser(user);

                        response.sendRedirect(redirect);

                        return;
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

            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
