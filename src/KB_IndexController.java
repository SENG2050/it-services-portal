import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Displays all knowledge base (KB) articles and a search bar
 */
public class KB_IndexController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        Category_DBWrapper categoryWrapper = this.getPortalBean().getCategories();

        categoryWrapper.reset();

        List<Category> categories = Arrays.asList(categoryWrapper.getCategories());

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/jsp/kb/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
