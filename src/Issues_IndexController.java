import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Shows the admin a list of all issues
 */
public class Issues_IndexController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Issue> issues = new ArrayList<Issue>();

        issues.add(new Issue());
        issues.add(new Issue());
        issues.add(new Issue());

        request.setAttribute("issues", issues);

        request.getRequestDispatcher("/WEB-INF/jsp/issues/index.jsp").forward(request, response);
    }
}
