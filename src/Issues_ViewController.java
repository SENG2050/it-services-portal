import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Admin: Allows the admin to view the given issue, change state, add comments, mark as KB article, etc
 * User: Allows the user to view the given issue if they created it, add comments
 */
public class Issues_ViewController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);

        String path = request.getPathInfo();
        String[] pathParts = path.split("/");

        int id;
        id  = Integer.parseInt(pathParts[1]);

        if (this.isLoggedIn()) {
            try {
                Issue issue = this.getPortalBean().getIssues().getIssueById(id);
                if(issue!=null){

                    request.setAttribute("issue", issue);

                    Status_DBWrapper statusWrapper = this.getPortalBean().getStatuses();

                    statusWrapper.reset();
                    statusWrapper.addSort("id", "ASC");

                    Status[] statuses = statusWrapper.runQuery();
                    request.setAttribute("statuses", statuses);

                    if(this.getUser().isAdmin()){
                        request.getRequestDispatcher("/WEB-INF/jsp/issues/view-admin.jsp").forward(request, response);
                    }
                    else{
                        request.getRequestDispatcher("/WEB-INF/jsp/issues/view-user.jsp").forward(request, response);
                    }
                }
                else {
                    response.sendRedirect(this.getBaseURL() + "issues");
                }

            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect(this.getBaseURL()+"issues");
            }
        } else {
            request.getSession().setAttribute("r", "issues/" + id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        //@TODO: Not sure how the issue is being sent through here, so will just generate a dummy issue for now
        Issue updatedIssue = new Issue();
        // Update user on status change if needed
        Issue currentState = this.portalBean.issues.getIssueById(updatedIssue.getIssueId());
        if (updatedIssue.getIssueId() != currentState.getIssueId()) {
            // Notify reporter
            User reporter = this.portalBean.users.getById(updatedIssue.getUserId());
            reporter.setNotification(true);
            reporter.setNotificationText("Issue: #" + updatedIssue.getIssueId() + " has been updated.");
            this.portalBean.users.updateUser(reporter);
        }
        // End update user on status change
    }
}
