package edu.epam.lasttask.main;

import edu.epam.lasttask.entity.Hookah;
import edu.epam.lasttask.entity.HookahBar;
import edu.epam.lasttask.entity.Visitor;
import edu.epam.lasttask.exception.WrongFilePathException;
import edu.epam.lasttask.factory.VisitorFactory;
import edu.epam.lasttask.factory.impl.VisitorFactoryImpl;
import edu.epam.lasttask.parser.StringParser;
import edu.epam.lasttask.parser.impl.StringParserImpl;
import edu.epam.lasttask.reader.InputReader;
import edu.epam.lasttask.reader.impl.InputReaderImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws WrongFilePathException, InterruptedException {
        InputReader reader = new InputReaderImpl();
        List<String> stringsFromFile = reader.readStringsFromFile(Paths.get("src/main/resources/input/data.txt"));
        VisitorFactory visitorFactory = new VisitorFactoryImpl();
        StringParser stringParser = new StringParserImpl();
        List<Visitor> visitors = new ArrayList<>();
        stringsFromFile.forEach(x -> {
            List<String> parsedLine = stringParser.separateLine(x);
            Visitor visitor = visitorFactory.createVisitorFromStrings(parsedLine).get();
            visitors.add(visitor);
        });
        Hookah hookah1 = new Hookah();
        Hookah hookah2 = new Hookah();
        List<Hookah> hookahs = new ArrayList<>();
        hookahs.add(hookah1);
        hookahs.add(hookah2);
        HookahBar hookahBar = HookahBar.getInstance(hookahs, visitors);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(hookahBar);
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        logger.info(hookahBar.getReviews());
    }
}
