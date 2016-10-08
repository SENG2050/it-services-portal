import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Searches all KB articles for the given term and displays titles and links to them, with a search bar
 */
public class KB_SearchController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("term");
        if(term!=null){
            request.setAttribute("term",term);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/kb/search.jsp").forward(request, response);

    }
}
