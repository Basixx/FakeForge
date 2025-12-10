package com.romecka.fakeforge.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CsvUtils {

    public static List<String> fromClasspathCsv(String resourcePath) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (InputStream is = cl.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new ExceptionInInitializerError("CSV resource not found on classpath: " + resourcePath);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8))) {
                return br.lines().toList();
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to read from CSV: " + e.getMessage());
        }
    }

}
