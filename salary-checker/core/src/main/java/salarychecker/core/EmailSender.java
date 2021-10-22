package salarychecker.core;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public  void sendMail(String recepient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "add your email here";
        //Your gmail password
        String password = "add password here";


        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myAccountEmail,password);
                    }
                });




                //Prepare email message
                Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);


    }

    private Message prepareMessage(Session session, String myAccountEmail, String recepient) {
       
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Email From Salary Checker App");
            String htmlCode = "<h1> Hi, your salary is ready! </h1> <br/> <h2><b>Get it! </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (MessagingException e) {
            System.out.println("Message was not sent");
        }
        return null;
    }
    
}
