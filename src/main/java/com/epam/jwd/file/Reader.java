package com.epam.jwd.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {

    public static String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
