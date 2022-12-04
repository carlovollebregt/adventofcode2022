package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class StringFileReader {
    public static List<String> readInput(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemClassLoader().getResource(filename).toURI()))) {
            return stream.toList();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
