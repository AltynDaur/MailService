package com.epam.mail.runner;

import com.epam.mail.sender.Arguments;
import com.epam.mail.sender.MailSender;
import com.epam.util.ArgumentParser;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Dauren_Altynbekov on 5/17/2016.
 */
public class Runner {

    private static Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        Arguments arguments = ArgumentParser.parse(args);
        MailSender.sendEmail(arguments);
    }
}
