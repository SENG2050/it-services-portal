import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Shows the admin a list of all issues
 */
public class Issues_IndexController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        ArrayList<Issue> issues = this.generateDummyData();

        request.setAttribute("users", this.getPortalBean().getUsers().getUsers());
        request.setAttribute("issues", issues);

        String column = request.getParameter("column");
        request.setAttribute("column", column);

        if (column != null) {
            String[] split = column.split("|");

            String sortBy = split[0];
            String direction = split[1];
        }

        request.getRequestDispatcher("/WEB-INF/jsp/issues/index.jsp").forward(request, response);
    }
}

