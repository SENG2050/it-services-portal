import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Searches all KB articles for the given term and displays titles and links to them, with a search bar
 */
public class KB_SearchController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
        super.doGet(request, response);

        Issue_DBWrapper issueWrapper = this.getPortalBean().getIssues();

        issueWrapper.reset();

        String term = request.getParameter("term");
        request.setAttribute("term", term);

        if (term != null) {
            issueWrapper.addLike("id", term);
            issueWrapper.addLike("title", term);
            issueWrapper.addLike("description", term);
            issueWrapper.addLike("resolved", term);
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

        List<Issue> issues = Arrays.asList(issueWrapper.runQuery());

        request.setAttribute("issues", issues);

        Category_DBWrapper categoryWrapper = this.getPortalBean().getCategories();

        categoryWrapper.reset();
        categoryWrapper.addSort("title", "ASC");

        Category[] categories = categoryWrapper.runQuery();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/jsp/kb/search.jsp").forward(request, response);
    }
}
