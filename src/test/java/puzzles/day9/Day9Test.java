package puzzles.day9;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

    @Test
    void testPart1() {
        assertEquals(13, Day9.solve(StringFileReader.readInputToList("puzzles/day9/testinput.txt"), 2));
    }

    @Test
    void testPart2() {
        assertEquals(36, Day9.solve(StringFileReader.readInputToList("puzzles/day9/testinput2.txt"), 10));
    }
}
