package edu.epam.lasttask.reader.impl;

import edu.epam.lasttask.exception.WrongFilePathException;
import edu.epam.lasttask.reader.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InputReaderImplTest {

    InputReader inputReader;

    @BeforeAll
    public void setup() {
        inputReader = new InputReaderImpl();
    }

    @Test
    void readStringsFromFile() throws WrongFilePathException {
        List<String> expected = List.of("person Ales 12", "company Gleb 22 Nastya 33");
        List<String> actual = inputReader.readStringsFromFile(Paths.get("src/test/resources/input/testData.txt"));
        Assertions.assertEquals(expected, actual);
    }
}