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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        Issue_DBWrapper issueWrapper = this.getPortalBean().getIssues();

        String term = request.getParameter("term");
        request.setAttribute("term", term);

        if (term != null) {
            issueWrapper.addLike("id", term);
            issueWrapper.addLike("userId", term);
            issueWrapper.addLike("status", term);
            issueWrapper.addLike("title", term);
            issueWrapper.addLike("created", term);
        }

        List<Issue> issues = Arrays.asList(issueWrapper.runQuery());

        request.setAttribute("issues", issues);

        request.getRequestDispatcher("/WEB-INF/jsp/kb/search.jsp").forward(request, response);
    }
}
