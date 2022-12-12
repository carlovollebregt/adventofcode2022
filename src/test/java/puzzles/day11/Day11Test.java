package puzzles.day11;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
    @Test
    void testPart1() {
        assertEquals(10605, Day11.solve(StringFileReader.readInputToList("puzzles/day11/testinput.txt"), 20,false));
    }

    @Test
    void testPart2() {
        assertEquals(2713310158L, Day11.solve(StringFileReader.readInputToList("puzzles/day11/testinput.txt"), 10000,true));
    }
}
