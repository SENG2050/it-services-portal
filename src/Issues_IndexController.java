import javax.servlet.RequestDispatcher;
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
            Issue_DBWrapper issueWrapper = this.getPortalBean().getIssues();

            issueWrapper.reset();

            String column = request.getParameter("column");
            request.setAttribute("column", column);

            if (column != null && column.equals("") == false) {
                String[] split = column.split("\\|");

                String sortBy = split[0];
                String direction = split[1];

                issueWrapper.addSort(sortBy, direction);
            }

            Status_DBWrapper statusWrapper = this.getPortalBean().getStatuses();

            statusWrapper.reset();
            statusWrapper.addSort("id", "ASC");

            Status[] statuses = statusWrapper.runQuery();
            request.setAttribute("statuses", statuses);

            Category_DBWrapper categoryWrapper = this.getPortalBean().getCategories();

            categoryWrapper.reset();
            categoryWrapper.addSort("title", "ASC");

            Category[] categories = categoryWrapper.runQuery();
            request.setAttribute("categories", categories);


            if (this.getUser().isAdmin()) {
                List<Issue> issues = Arrays.asList(issueWrapper.runQuery());
                request.setAttribute("issues", issues);

                request.getRequestDispatcher("/WEB-INF/jsp/issues/index.jsp").forward(request, response);
            } else {
                issueWrapper.addWhere("userId", this.getUser().getUserId());

                List<Issue> issues = Arrays.asList(issueWrapper.runQuery());
                request.setAttribute("issues", issues);

                request.getRequestDispatcher("/WEB-INF/jsp/issues/index.jsp").forward(request, response);
            }
        } else {
            request.getSession().setAttribute("r", "issues");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }
    }
}

