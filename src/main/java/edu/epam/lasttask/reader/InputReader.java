package edu.epam.lasttask.reader;

import edu.epam.lasttask.exception.WrongFilePathException;

import java.nio.file.Path;
import java.util.List;

public interface InputReader {
    List<String> readStringsFromFile(Path filePath) throws WrongFilePathException;
}
