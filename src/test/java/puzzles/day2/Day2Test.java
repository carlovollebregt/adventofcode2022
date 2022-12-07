package puzzles.day2;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    @Test
    void solvePart1() {
        int result = Day2.solvePart1(StringFileReader.readInputToList("puzzles/day2/testinput.txt"));

        assertEquals(15, result);
    }

    @Test
    void solvePart2() {
        int result = Day2.solvePart2(StringFileReader.readInputToList("puzzles/day2/testinput.txt"));

        assertEquals(12, result);
    }

}
