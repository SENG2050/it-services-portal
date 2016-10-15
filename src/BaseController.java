import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * BaseController
 * Handles portal bean and session management
 */
public class BaseController extends HttpServlet {
    protected PortalBean portalBean;
    protected HttpSession session;
    protected User user;
    protected boolean loggedIn;

    public BaseController() {
        this.setPortalBean(new PortalBean());
        this.setSession(null);
        this.setUser(null);
        this.setLoggedIn(false);
    }

    protected PortalBean getPortalBean() {
        return portalBean;
    }

    private void setPortalBean(PortalBean portalBean) {
        this.portalBean = portalBean;
    }

    protected HttpSession getSession() {
        return session;
    }

    private void setSession(HttpSession session) {
        this.session = session;
    }

    protected User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        if (this.session != null) {
            this.session.setAttribute("user", user);
        }
    }

    protected boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getDefaultData(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getDefaultData(request);
    }

    protected ArrayList<Issue> generateDummyData() {
        ArrayList<Issue> testData = new ArrayList<>();

        String[] categories = {"Network", "Software", "Hardware", "Email", "Account"};
        String[] titles = {
                "Cannot use Uni wifi",
                "Cannot log in to myHub",
                "Printer at library",
                "Cannot log in to uni email",
                "How can i reset my password"
        };

        String[] descriptions = {
                "I have tried to use uni wifi but it keep failing. How i can set it up?",
                "I forgot my password to log in to myHub, how can i retrieve my password?",
                "I printed 2 couple pages using a printer at library, instead of giving me the black and white pages, it gave me the colored ones," +
                        "which charges me more money, what was the problem?",
                "I graduated several moths ago, i thought i would still be able to login to uni email can't now, i have lots of documents stored on" +
                        "Outlook, how can i get them back?",
                "I think someone knows my password, how do i change it to another one?"

        };

        String[] resolutions = {
                "Please go to www.help.uon.edu.au for more details",
                "Please visit one of the uni hubs for more help, there is one in shortland building. They will be able to sort it out for you.",
                "Please ask librarian for more assistance",
                "In this case, you will not get you documents back as all graduated students after living uni, their uni accounts will get deleted.",
                "Please explore your profile from your account when you log in, there is a section called Change Password, from there you should be able to change your password. Cheers"
        };

        int[] status = {1, 0, 1, 1, 0};

        for (int i = 0; i < 5; i++) {
            Issue issue = new Issue(i + 1, titles[i], descriptions[i], resolutions[i], i + 1, i + 1, status[i], new Date(), new Date());
            testData.add(issue);
        }

        return testData;
    }

    /**
     * Helper function to populate request with needed info
     * @param request
     */
    private void getDefaultData(HttpServletRequest request) {
        this.setSession(request.getSession());
        this.setUser((User) this.session.getAttribute("user"));
        this.setLoggedIn(this.user != null);

        // User has a notification/issue has been updated
        // @TODO: Alter notification text
        if (this.user.hasNotification()) {
            request.setAttribute("notification", "Has Notification.");
        }

        request.setAttribute("user", this.getUser());
        request.setAttribute("loggedIn", this.isLoggedIn());
    }
}
