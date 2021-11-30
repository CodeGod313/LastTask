package edu.epam.lasttask.reader.impl;

import edu.epam.lasttask.exception.WrongFilePathException;
import edu.epam.lasttask.reader.InputReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class InputReaderImpl implements InputReader {
    static Logger logger = LogManager.getLogger(InputReaderImpl.class);

    @Override
    public List<String> readStringsFromFile(Path filePath) throws WrongFilePathException {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.toList();
        } catch (IOException ex) {
            logger.error("Wrong file path " + filePath, ex);
            throw new WrongFilePathException(filePath.toString(), ex);
        }
    }
}
