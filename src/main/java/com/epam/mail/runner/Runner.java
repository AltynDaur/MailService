package com.epam.mail.runner;

import com.epam.mail.sender.Arguments;
import com.epam.mail.sender.MailSender;
import com.epam.util.ArgumentParser;

/**
 * Created by Dauren_Altynbekov on 5/17/2016.
 */
public class Runner {

    public static void main(String[] args) {
        Arguments arguments = ArgumentParser.parse(args);
        MailSender.sendEmail(arguments);
    }
}
