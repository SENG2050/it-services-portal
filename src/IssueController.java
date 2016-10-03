import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * IssueController.java
 *
 * Handles main application HTTP request/response
 */
public class IssueController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String[] pathParts = path.split("/");
        RequestDispatcher dispatcher;

        // If pathParts is empty direct to issue categories
        if (pathParts.length == 1) {
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issue_categories.jsp");
        } else {
            // First handle 1st part of url
            switch (pathParts[1]) {
                case "create":
                    // Show new issue form
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/create_issue.jsp");
                    break;
                case "view":
                    // Show this issue based on its id
                    int issueId = Integer.parseInt(pathParts[1]);
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/view_issue.jsp");
                    break;
                case "knowledgebase":
                    // Show knowledge base search
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/knowledge_base.jsp");
                    break;
                default:
                    // Direct to main issue categories
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/issue_categories.jsp");
                    break;
            }
        }
        // Forward dispatcher
        dispatcher.forward(request, response);
    }
}
