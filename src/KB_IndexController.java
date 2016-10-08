import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.Date;

/**
 * Displays all knowledge base (KB) articles and a search bar
 */
public class KB_IndexController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // this will be a request variable
        ArrayList<Comment> testComments = dummyComments(request);
        request.setAttribute("comments", testComments);

        request.getRequestDispatcher("/WEB-INF/jsp/kb/index.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.getRequestDispatcher("/WEB-INF/jsp/kb/index.jsp").forward(request, response);
    }


    protected ArrayList<Comment> dummyComments(HttpServletRequest request) throws ServletException, IOException {
        ArrayList<Comment> dummyData = new ArrayList<>();

        HttpSession session = request.getSession();

        ArrayList<Issue> issues = (ArrayList<Issue>)session.getAttribute("issues");
        if(issues!=null){

            issues.forEach(i ->{
                int issueId = i.getIssueId();
                String[] comments = {
                        "Woa, this solution is great!Thanks",
                        "I didn't know it was that simple",
                        "Thanks for your help"
                };
                for (int j = 0; j < comments.length; j++) {
                    Comment cmt = new Comment(j+1,issueId,comments[j],new Date());
                    dummyData.add(cmt);
                }

            });
        }

        return dummyData;
    }

}
