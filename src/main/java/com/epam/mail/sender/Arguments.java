package com.epam.mail.sender;

import com.epam.util.StringParser;
import org.kohsuke.args4j.Option;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dauren_Altynbekov on 5/17/2016.
 */
public class Arguments {

    @Option(name = "-f", usage = "From email address", required = true)
    private String fromAddress;

    @Option(name = "-t", usage = "To email address", required = true)
    private String toAddress;

    @Option(name = "-cc", usage = "CC email address")
    private String ccAddress;

    @Option(name = "-s", usage = "Email subject")
    private String subject;

    @Option(name = "-m", usage = "Email message", required = true)
    private String message;

    @Option(name = "-mct", usage = "Message content type")
    private String contentType;

    @Option(name = "-a", usage = "Attachment")
    private String attachment;

    @Option(name = "-smtp", usage = "SMTP server", required = true)
    private String server;

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public String getSubject() {
        if(subject == null){
            return null;
        }
        return StringParser.getString(subject);
    }

    public String getMessage() {
        return StringParser.getString(message);
    }

    public String getContentType() {
        return contentType;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getServer() {
        return server;
    }

    Map<String, String> arguments = new HashMap<String, String>();

    public Map<String, String> getArgumentsMap() {
        arguments.put("fromAddress", fromAddress);
        arguments.put("toAddress", toAddress);
        arguments.put("subject", subject);
        arguments.put("message", message);
        arguments.put("contentType", contentType);
        arguments.put("attachment", attachment);
        arguments.put("server", server);
        return arguments;
    }
}
