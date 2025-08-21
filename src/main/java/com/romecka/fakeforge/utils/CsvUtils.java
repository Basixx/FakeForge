package com.romecka.fakeforge.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CsvUtils {

    public static List<String> fromCsv(String path) {
        try (Stream<String> lines = Files.lines(Path.of(path), UTF_8)) {
            return lines.toList();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to read from CSV: " + e.getMessage());
        }
    }

}
