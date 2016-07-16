package mail;

	import javax.mail.*;
	import javax.mail.internet.*;
	import javax.mail.Authenticator;
	import javax.mail.PasswordAuthentication;

	import java.util.Properties;


	public class SimpleMail {

	    private static final String SMTP_HOST_NAME = "smtp.mail.yahoo.com";
	    private static final String SMTP_AUTH_USER = "c4t4lyn_party_vl@yahoo.com";
	    private static final String SMTP_AUTH_PWD  = "Nughicesti1";

	    public static void main(String[] args) throws Exception{
	     String catre ="catalin.enache.357@gmail.com";
	       new SimpleMail().test(catre);
	       System.out.println("email trimis catre"+ catre);
	    }

	    public void test(String catre) throws Exception{
	        Properties props = new Properties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.transport.port","465");
	        props.put("mail.smtp.host", SMTP_HOST_NAME);
	        props.put("mail.smtp.auth", "true");

	        Authenticator auth = new SMTPAuthenticator();
	        Session mailSession = Session.getDefaultInstance(props, auth);
	        // uncomment for debugging infos to stdout
	        // mailSession.setDebug(true);
	        Transport transport = mailSession.getTransport();
	        
	       

	        MimeMessage message = new MimeMessage(mailSession);
	        message.setContent("Salutare barcaoane", "text/plain");
	        message.setFrom(new InternetAddress("batem-palma"));
	        message.addRecipient(Message.RecipientType.TO,
	             new InternetAddress(catre));
	        
	        
	        
	        transport.connect();
	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        transport.close();
	    }

	    private class SMTPAuthenticator extends javax.mail.Authenticator {
	        public PasswordAuthentication getPasswordAuthentication() {
	           String username = SMTP_AUTH_USER;
	           String password = SMTP_AUTH_PWD;
	           return new PasswordAuthentication(username, password);
	        }
	        
	    }
	    
	}


