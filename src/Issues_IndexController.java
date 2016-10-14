import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

/**
 * Shows the admin a list of all issues
 */
public class Issues_IndexController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        if (this.isLoggedIn()) {
            if (this.getUser().isAdmin()) {
                Issue_DBWrapper issueWrapper = this.getPortalBean().getIssues();

                String column = request.getParameter("column");
                request.setAttribute("column", column);

                if (column != null) {
                    String[] split = column.split("\\|");

                    String sortBy = split[0];
                    String direction = split[1];

                    issueWrapper.addSort(sortBy, direction);
                }

                List<Issue> issues = Arrays.asList(issueWrapper.runQuery());

                request.setAttribute("issues", issues);

                request.getRequestDispatcher("/WEB-INF/jsp/issues/index.jsp").forward(request, response);
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}

