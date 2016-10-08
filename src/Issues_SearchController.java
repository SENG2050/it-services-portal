import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Searches all issues for the given term and displays titles and links to them, with a search bar
 */
public class Issues_SearchController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        ArrayList<Issue> issues = this.generateDummyData();

        request.setAttribute("issues", issues);

        String term = request.getParameter("term");
        request.setAttribute("term", term);

        String column = request.getParameter("column");
        request.setAttribute("column", column);

        if (column != null) {
            String[] split = column.split("|");

            String sortBy = split[0];
            String direction = split[1];
        }

        request.getRequestDispatcher("/WEB-INF/jsp/issues/search.jsp").forward(request, response);
    }
}
