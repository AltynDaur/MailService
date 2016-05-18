package com.epam.util;

import com.epam.mail.sender.Arguments;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * Created by Dauren_Altynbekov on 5/17/2016.
 */
public class ArgumentParser {

    private static Logger LOGGER = Logger.getLogger(ArgumentParser.class);

    public static Arguments parse(String[] args) {
        Arguments arguments = new Arguments();
        CmdLineParser parser = new CmdLineParser(arguments);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            LOGGER.error(e.getMessage());
        }
        return arguments;
    }
}
