import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
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

        // Get issue id from url and test
        int id;
        try {
            id  = Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException ex) {
            // Send 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (this.isLoggedIn()) {
            try {
                Issue issue = this.getPortalBean().getIssues().getIssueById(id);
                // Only admin or reporter can view
                if (this.getUser().isUser() && this.getUser().getUserId() != issue.getUserId()) {
                    this.setErrorNotification("Unable to access this issue. Only Admin or the original reporter can access an issue.");
                    response.sendRedirect(this.getBaseURL() + "/issue");
                    return;
                }

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

        String path = request.getPathInfo();
        String[] pathParts = path.split("/");

        int id;
        try {
            id  = Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Issue currentState = this.getPortalBean().getIssues().getIssueById(id);

        if(currentState != null){

            int status = Integer.parseInt(request.getParameter("status"));
            String commentPara = request.getParameter("reply");

            // Check if status has changed
            if (currentState.getStatus().getId() != status) {
                // It has, so update
                currentState.setStatus(status);

                // Now check if status has been set to Completed
                // If so, add resolved message
                if (status == 5 && commentPara != null && !commentPara.equals("")) {
                    currentState.setResolution(commentPara);
                    currentState.setResolved(new Timestamp(new Date().getTime()));
                } else if (status == 5) {
                    // Marked as completed but no resolution message
                    // Send error to admin and return
                    this.portalBean.getUsers().setAdminNotification(true, "Error: Issue was set as completed with no resolution message.  Please try again.");
                    response.sendRedirect(request.getRequestURI());
                    return;
                }

                // Now check if status has been set to Resolved or rejected
                // Notify Admin
                if (status == 6 || status == 7) {
                    this.portalBean.getUsers().setAdminNotification(true, "Issue: " + currentState.getIssueId() + " has been updated.");
                }

                // Now notify reporter on all status change
                User reporter = currentState.getUser();
                reporter.setNotification(true);
                reporter.setNotificationText("Issue: #" + currentState.getIssueId() + " has been updated.");
                this.portalBean.users.updateUser(reporter);

                // Finally update issue
                try {
                    this.portalBean.getIssues().updateIssue(currentState);
                } catch (Exception ex) {
                    this.setErrorNotification("There was an error updating this issue, please try again.");
                    response.sendRedirect(request.getRequestURI());
                    return;
                }

            }

            // Now create comment if needed - note that we dont want to add if its a resolved message
            if (status != 5 && commentPara != null && !commentPara.equals("")) {
                // Get public flag
                boolean isPublic = true;
                if (this.user.isAdmin()) {
                    isPublic = (Integer.parseInt(request.getParameter("public")) != 0);
                }
                // Create new comment model
                Comment newComment = new Comment(
                        id,
                        this.getUser().getUserId(),
                        isPublic,
                        commentPara,
                        new Timestamp(new Date().getTime())
                );
                // Add comment to DB
                try {
                    this.portalBean.getComments().createNewComment(newComment);
                } catch (Exception ex) {
                    this.setErrorNotification("There was an error posting this comment, please try again.");
                    response.sendRedirect(request.getRequestURI());
                    return;
                }
            }

            response.sendRedirect(request.getRequestURI());
        }
        else {
            // Issue doesnt exist, shouldnt be updating it
            response.sendError(500);
        }
    }

    private void setErrorNotification(String msg) {
        User user = this.getUser();
        user.setNotification(true);
        user.setNotificationText(msg);
        this.portalBean.users.updateUser(user);
    }
}


