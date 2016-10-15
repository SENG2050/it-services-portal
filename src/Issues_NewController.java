import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Allows the user to create a new issue
 */
public class Issues_NewController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        Category_DBWrapper categoryWrapper = this.getPortalBean().getCategories();

        categoryWrapper.reset();
        categoryWrapper.addSort("title", "ASC");

        Category[] categories = categoryWrapper.runQuery();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/jsp/issues/new.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
