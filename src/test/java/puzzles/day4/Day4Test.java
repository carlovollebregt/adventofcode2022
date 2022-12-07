package puzzles.day4;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void solvePart1() {
        long result = Day4.solvePart1(StringFileReader.readInputToList("puzzles/day4/testinput.txt"));

        assertEquals(2, result);
    }

    @Test
    void solvePart2() {
        long result = Day4.solvePart2(StringFileReader.readInputToList("puzzles/day4/testinput.txt"));

        assertEquals(4, result);
    }
}
