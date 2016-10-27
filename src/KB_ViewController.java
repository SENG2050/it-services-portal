import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Allows the user to view the given KB article
 */
public class KB_ViewController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        //
        String path = request.getPathInfo();
        String[] pathParts = path.split("/");

        int id;
        try{
            id = Integer.parseInt(pathParts[1]);
            Issue issue = this.getPortalBean().getIssues().getIssueById(id);
            if(issue!=null && issue.isKBArticle()){
                request.setAttribute("issue", issue);

                request.getRequestDispatcher("/WEB-INF/jsp/kb/view.jsp").forward(request, response);
            }

            else{
                response.sendRedirect(this.getBaseURL());
            }
        }catch (Exception e){

            e.printStackTrace();
            response.sendRedirect(this.getBaseURL());
        }



    }
}
