import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LogoutController
 *
 * Handles user logout
 *
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("PortalBean", null);
        response.sendRedirect("/");
    }
}
