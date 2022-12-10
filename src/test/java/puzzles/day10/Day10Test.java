package puzzles.day10;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    @Test
    void testPart1() {
        assertEquals(13140, Day10.solve(StringFileReader.readInputToList("puzzles/day10/testinput.txt")));
    }
}
