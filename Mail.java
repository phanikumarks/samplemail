import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	
	Session newSession = null;
	MimeMessage mimeMessage = null;  
	public static void main(String[] args) throws AddressException, MessagingException{
			Mail mail = new Mail();
			mail.setupServerProperties();
			mail.draftEmail();
			mail.sendEmail();
			
	}

	private void sendEmail() throws MessagingException{
			String fromUser = "sarathk042@gmail.com";
			String fromUserpassword = "6304343467";
			String emailHost = "smtp.gmail.com";
			Transport transport = newSession.getTransport("smtp");
			transport.connect(emailHost, fromUser, fromUserpassword);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			System.out.println("Email successfully sent");
			
	}

	private MimeMessage draftEmail() throws AddressException, MessagingException {
		String[] emailRecepients	= {"kspk042@gmail.com", "sandy.sandeep5150@gmail.com"};
		String emailSubject = "Test Mail";
		String emailBody = "Test Body of my Email";
		mimeMessage = new MimeMessage(newSession);
		
		for(int i = 0; i < emailRecepients.length; i++) {
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecepients[i]));
		}
		mimeMessage.setSubject(emailSubject);
		MimeMultipart multiPart = new MimeMultipart();
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(emailBody, "html/text");
		multiPart.addBodyPart(bodyPart);
		mimeMessage.setContent(multiPart);
		return mimeMessage;
		
	}

	private void setupServerProperties() {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			newSession = Session.getDefaultInstance(properties, null);
	}
}