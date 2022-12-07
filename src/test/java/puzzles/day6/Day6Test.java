package puzzles.day6;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    @Test
    void testPart1() {
        int result = Day6.solve(StringFileReader.readInputToString("puzzles/day6/testinput.txt"), 4);

        assertEquals(5, result);
    }
}
