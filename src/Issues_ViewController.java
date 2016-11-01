import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
        try {
            id  = Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

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


//        System.out.println("Testub");
//        //@TODO: Not sure how the issue is being sent through here, so will just generate a dummy issue for now
//        Issue updatedIssue = new Issue();
//        // Update user on status change if needed
//        Issue currentState = this.portalBean.issues.getIssueById(updatedIssue.getIssueId());
//        if (updatedIssue.getIssueId() != currentState.getIssueId()) {
//            // Notify reporter
//            User reporter = this.portalBean.users.getById(updatedIssue.getUserId());
//            reporter.setNotification(true);
//            reporter.setNotificationText("Issue: #" + updatedIssue.getIssueId() + " has been updated.");
//            this.portalBean.users.updateUser(reporter);
//        }


        String path = request.getPathInfo();
        String[] pathParts = path.split("/");

        int id;
        try {
            id  = Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        Issue currentState = null;

        Issue_DBWrapper issue_dbWrapper = this.getPortalBean().getIssues();

        if(this.getUser().isAdmin()){
            currentState = issue_dbWrapper.getIssueById(id);
        }
        else {
            Issue[] allUserIssues = issue_dbWrapper.getIssuesbyUserId(this.getUser().getUserId());

            for (int m = 0; m <allUserIssues.length; m++) {
                if(allUserIssues[m].getIssueId() == id){
                    currentState = allUserIssues[m];
                    break;
                }
            }
        }

        if(currentState!=null){

            String status = request.getParameter("status");
            String commentPara = request.getParameter("reply");
            //when they submit with status and comment
            if(status !=null && commentPara!=null){

                process(request,response,issue_dbWrapper, currentState,commentPara,true);

            }
            //if the status is 8, then there will be no comment, admin marks the issue as kb
            else if(status!=null && commentPara == null){
                currentState.setStatus(Integer.parseInt(status));
                issue_dbWrapper.updateIssue(currentState);

                response.sendRedirect(this.getBaseURL()+"issues");
            }
            // comment will be submitted but status is not available, this is the case when the user submit their comment
            // not when he accepts or rejects the resolution when the admin marks it as completed.
            else if(status==null && commentPara != null){
                process(request,response,issue_dbWrapper, currentState,commentPara,false);
            }
            else {
                response.sendRedirect(this.getBaseURL()+"issues");
            }


        }
        else {
            response.sendRedirect(this.getBaseURL()+"issues");
        }

    }

    protected void process(HttpServletRequest request, HttpServletResponse
            response,Issue_DBWrapper dbWrapper,Issue currentState, String commentPar, boolean isStatusSupplied) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        Comment_DBWrapper comment_dbWrapper = this.getPortalBean().getComments();

        //String commentPara = request.getParameter("reply");
        if(!"".equals(commentPar) && commentPar.trim().length() != 0){

            Comment comment = new Comment();
            comment.setIssueId(currentState.getIssueId());
            comment.setUserId(this.getUser().getUserId());

            comment.setComment(commentPar);

            comment.setCreated(startDate);

            if(this.getUser().isAdmin()){
                comment.setPublic(Integer.parseInt(request.getParameter("public")) !=0);
            }
            else{
                comment.setPublic(false);
            }

            //create new comment
            comment_dbWrapper.createNewComment(comment); // it will throw null exceptions here

            //update issue status

            if(isStatusSupplied){
                currentState.setStatus(Integer.parseInt(request.getParameter("status")));
                dbWrapper.updateIssue(currentState);
            }

            response.sendRedirect(this.getBaseURL()+"issues");

        }
        else {
            response.sendRedirect(this.getBaseURL()+"issues/"+ currentState.getIssueId());
        }
    }
}


