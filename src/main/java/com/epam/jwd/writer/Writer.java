package com.epam.jwd.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Writer {

    /**
     * Writes text to a file.
     * @param fileName - the name of the file to write
     * @param text - text to write
     * @throws IOException
     *         if an I/O error occurs writing to or creating the file, or the
     *         text cannot be encoded using the specified charset
     */
    public static void writeFile(String fileName, String text) throws IOException {
        Path path = Paths.get(fileName);
        Files.writeString(path, text);
    }
}
