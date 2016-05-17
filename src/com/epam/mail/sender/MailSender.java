package com.epam.mail.sender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Dauren_Altynbekov on 5/17/2016.
 */
public class MailSender {

    public static void sendEmail(Arguments arguments) {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", arguments.getServer());
        Session session = Session.getDefaultInstance(properties);
        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(arguments.getFromAddress()));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(arguments.getToAddress()));

            if(arguments.getCcAddress() != null){
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(arguments.getCcAddress()));
            }
            // Set Subject: header field
            message.setSubject(arguments.getSubject());

            MimeBodyPart body = new MimeBodyPart();
            body.setContent(arguments.getMessage(), arguments.getContentType());
            message.saveChanges();
            // Create a multipar message
            Multipart multipart = new MimeMultipart("alternative");

            // Set text message part
            multipart.addBodyPart(body);

            // Part two is attachment
            body = new MimeBodyPart();
            String filename = arguments.getAttachment();
            DataSource source = new FileDataSource(filename);
            body.setDataHandler(new DataHandler(source));
            body.setFileName(filename);
            multipart.addBodyPart(body);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
