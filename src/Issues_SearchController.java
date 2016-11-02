import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Searches all issues for the given term and displays titles and links to them, with a search bar
 */
public class Issues_SearchController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        Issue_DBWrapper issueWrapper = this.getPortalBean().getIssues();

        issueWrapper.reset();

        String term = request.getParameter("term");
        request.setAttribute("term", term);

        if (term != null && term.equals("") == false) {
            issueWrapper.addLike("id", term);
            issueWrapper.addLike("title", term);
            issueWrapper.addLike("description", term);
            issueWrapper.addLike("created", term);
        }

        String status = request.getParameter("status");
        request.setAttribute("status", status);

        if (status != null && status.equals("0") == false) {
            issueWrapper.addWhere("status", status);
        }

        String category = request.getParameter("category");
        request.setAttribute("category", category);

        if (category != null && category.equals("0") == false) {
            issueWrapper.addWhere("category", category);
        }

        String column = request.getParameter("column");
        request.setAttribute("column", column);

        if (column != null && column.equals("") == false) {
            String[] split = column.split("\\|");

            String sortBy = split[0];
            String direction = split[1];

            issueWrapper.addSort(sortBy, direction);
        }

        if(this.getUser().isAdmin()){
            List<Issue> issues = Arrays.asList(issueWrapper.runQuery());
            request.setAttribute("issues", issues);
        }
        else {
            List<Issue> issues = Arrays.asList(issueWrapper.getIssuesbyUserId(this.getUser().getUserId()));
            request.setAttribute("issues", issues);
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

        request.getRequestDispatcher("/WEB-INF/jsp/issues/search.jsp").forward(request, response);
    }
}
