package com.epam.jwd.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Writer {

    public static void writeFile(String fileName, String text) throws IOException {
        Path path = Paths.get(fileName);
        Files.writeString(path, text);
    }
}
