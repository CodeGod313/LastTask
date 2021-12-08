package edu.epam.lasttask.parser.impl;

import edu.epam.lasttask.parser.StringParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringParserImplTest {

    StringParser stringParser;
    String line;

    @BeforeAll
    public void setUp() {
        stringParser = new StringParserImpl();
        line = "person Ales 12";
    }

    @Test
    void separateLine() {
        List<String> expected = List.of("person", "Ales", "12");
        List<String> actual = stringParser.separateLine(line);
        Assertions.assertEquals(expected, actual);
    }
}