package com.epam.jwd.reader;

import com.epam.jwd.runner.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {

    private static final Logger logger = LogManager.getLogger(Reader.class);
    private static final String DELIMITER = "\n";

    /**
     * Reads text from a file.
     * @param fileName - the name of the file to read
     * @return text in the form of a String
     * @throws IOException if an I/O error occurs opening the file
     */
    public static String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.joining(DELIMITER));
        } catch (IOException e) {
            logger.error(e.toString());
            throw new IOException(e.getMessage());
        }
    }
}
