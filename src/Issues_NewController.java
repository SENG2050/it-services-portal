import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Allows the user to create a new issue
 */
public class Issues_NewController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        if (this.isLoggedIn()) {
            if (this.getUser().isAdmin()) {
                response.sendRedirect(this.getBaseURL());
                return;
            }
            Category_DBWrapper categoryWrapper = this.getPortalBean().getCategories();

            categoryWrapper.reset();
            categoryWrapper.addSort("title", "ASC");

            Category[] categories = categoryWrapper.runQuery();
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("/WEB-INF/jsp/issues/new.jsp").forward(request, response);
        }
        else{
            request.getSession().setAttribute("r", "issues/new");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);


        try{
            Issue issue = new Issue();
            issue.setTitle(request.getParameter("issueTitle"));
            issue.setDescription(request.getParameter("description"));
            issue.setCategory(Integer.parseInt(request.getParameter("category")));
            issue.setUserId(this.getUser().getUserId());
            issue.setCreated(new Timestamp(new Date().getTime()));
            // Hacky way as updating dates with null is messy. Ok for this use
            issue.setResolved(new Timestamp(new Date().getTime()));

            issue.setStatus(1); // As a new issue

            Issue_DBWrapper issue_dbWrapper = this.getPortalBean().getIssues();

            boolean success = issue_dbWrapper.createNewIssue(issue);

            if(success){
                //request.getSession().setAttribute("successMessage", "Issue created!");
                response.sendRedirect(this.getBaseURL() + "issues");
            }
            else {
                //request.getSession().setAttribute("errorMessage", "Cannot create new issue. Please try again!");
                response.sendRedirect(this.getBaseURL() + "issues/new");
            }


        }
        catch (Exception e){
            e.printStackTrace();

        }



        Map params = request.getParameterMap();
        Iterator i = params.keySet().iterator();
        while ( i.hasNext() )
        {
            String key = (String) i.next();
            String value = ((String[]) params.get( key ))[ 0 ];
            System.out.println(value);
        }
        //response.sendRedirect(this.getBaseURL() + "issues");
    }
}
