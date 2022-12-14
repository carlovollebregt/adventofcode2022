package utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class StringFileReaderTest {

    @Test
    void readInput() {
        List<String> strings = StringFileReader.readInputToList("utils/input.txt");
        List<String> expectedLines = List.of("1", "2", "3", "4");

        assertEquals(4, strings.size());
        assertLinesMatch(expectedLines, strings);
    }
}
