import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Searches all KB articles for the given term and returns them in an iframe
 */
public class KB_SuggestController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        String term = request.getParameter("term");

        if (term != null && term.equals("") == false) {
            Status status = this.getPortalBean().getStatuses().getStatusByTitle("Knowledge Base");

            Issue_DBWrapper issueWrapper = this.getPortalBean().getIssues();

            issueWrapper.reset();

            issueWrapper.addLike("title", term);
            issueWrapper.addLike("description", term);
            issueWrapper.addLike("resolution", term);

            issueWrapper.addWhere("status", status.getId());

            List<Issue> issues = Arrays.asList(issueWrapper.runQuery());
            request.setAttribute("issues", issues);

            request.getRequestDispatcher("/WEB-INF/jsp/kb/suggest.jsp").forward(request, response);
        }
    }
}
