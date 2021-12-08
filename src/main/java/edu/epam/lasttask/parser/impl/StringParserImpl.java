package edu.epam.lasttask.parser.impl;

import edu.epam.lasttask.parser.StringParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class StringParserImpl implements StringParser {
    static Logger logger = LogManager.getLogger(StringParserImpl.class);
    public static final String REGEX_DELIMITER = "\\s+";

    @Override
    public List<String> separateLine(String line) {
        String[] strings = line.split(REGEX_DELIMITER);
        List<String> separatedStrings = Arrays.stream(strings).toList();
        return separatedStrings;
    }
}
