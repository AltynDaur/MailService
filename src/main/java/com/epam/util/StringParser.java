package com.epam.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Dauren_Altynbekov on 5/17/2016.
 */
public class StringParser {

    public static String getString(String stringLine) {
        if(stringLine.matches("^[a-zA-Z]{1}:\\\\.+")){
            String subjectText = "";
            try {
                BufferedReader reader = getReader(stringLine);
                String line = reader.readLine();
                while (line != null)
                {
                    subjectText = subjectText.concat(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return subjectText;
        } else
            return stringLine;
    }

    public static String getFileNameFromPath(String filePath){
        String[] pathParts = filePath.split("\\\\");
        return pathParts[pathParts.length - 1];
    }

    public static BufferedReader getReader(String filePath) throws IOException
    {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("UTF-8")));
    }
}
