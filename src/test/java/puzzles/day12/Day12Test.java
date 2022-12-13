package puzzles.day12;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {
    @Test
    void testPart1() {
        Day12 day12 = new Day12();
        day12.init(StringFileReader.readInputToList("puzzles/day12/testinput.txt"));
        assertEquals(31, day12.solvePart1());
    }

    @Test
    void testPart2() {
        Day12 day12 = new Day12();
        day12.init(StringFileReader.readInputToList("puzzles/day12/testinput.txt"));
        assertEquals(29, day12.solvePart2());
    }

}
