package com.epam.mail.sender;

import org.apache.log4j.Logger;

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

    private static Logger LOGGER = Logger.getLogger(MailSender.class);

    public static void sendEmail(Arguments arguments) {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", arguments.getServer());
        LOGGER.info("Setting SMTP server: " + arguments.getServer());
        Session session = Session.getDefaultInstance(properties);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(arguments.getFromAddress()));
            LOGGER.info("Setting from address: " + arguments.getFromAddress());
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(arguments.getToAddress()));
            LOGGER.info("Setting to address: " + arguments.getToAddress());
            if (arguments.getCcAddress() != null) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(arguments.getCcAddress()));
                LOGGER.info("Setting CC address: " + arguments.getCcAddress());
            }
            // Set Subject: header field
            if (arguments.getSubject() != null) {
                message.setSubject(arguments.getSubject());
                LOGGER.info("Setting subject: " + arguments.getArgumentsMap().get("subject"));
            }
            MimeBodyPart body = new MimeBodyPart();
            if (arguments.getMessage() != null) {
                body.setContent(arguments.getMessage(), arguments.getContentType());
                LOGGER.info("Setting content-type: " + arguments.getContentType());
                message.saveChanges();
            }
            // Create a multipar message
            Multipart multipart = new MimeMultipart("alternative");
            // Set text message part
            multipart.addBodyPart(body);
            // Part two is attachment
            body = new MimeBodyPart();
            if (arguments.getAttachment() != null) {
                String filename = arguments.getAttachment();
                LOGGER.info("Setting attachment file: " + arguments.getAttachment());
                DataSource source = new FileDataSource(filename);
                body.setDataHandler(new DataHandler(source));
                body.setFileName(filename);
                multipart.addBodyPart(body);
            }
            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
