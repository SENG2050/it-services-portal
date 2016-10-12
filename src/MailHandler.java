import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * MailHandler
 * <p>
 * Basic static methods to handle smtp mail requests
 * <p>
 * Author: Ben Sutter
 * Updated: 12/10/16
 */
public class MailHandler {
    private static final String fromAddress = "noreply@portal.com.au";
    private static Issue issue;
    private static User user;

    public static void sendMail(Issue i, User u) {
        issue = i;
        user = u;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            Session session = (Session) envCtx.lookup("mail/Session");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress, "IT Portal Admin"));
            InternetAddress to[] = new InternetAddress[1];
            to[0] = new InternetAddress(user.getEmail());
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(generateSubject());
            message.setContent(generateBody(), "text/plain");
            Transport.send(message);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private static String generateSubject() {
        return "It Services Portal - issue #" + issue.getIssueId() + " has been updated.";
    }

    private static String generateBody() {
        return "Hi " + user.getFirstName() + ","+
                "\n You are receiving this email because the issue you have lodged has been updated.  See below for details." +
                "\n\n Title: " + issue.getTitle() +
                "\n Description: " + issue.getDescription() +
                "\n Status: " + issue.getStatus() +
                "\n Resolution: " + issue.getResolution() +
                "\n\n Please do not reply to this email.";
    }

}
