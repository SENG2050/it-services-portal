import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LogoutController
 * <p>
 * Handles user logout
 * <p>
 * Author: Ben Sutter
 * Updated: 5/10/16
 */
public class LogoutController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        this.setUser(null);
        this.setLoggedIn(false);

        response.sendRedirect(this.getBaseURL());
    }
}
