package puzzles.day3;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void solvePart1() {
        int result = Day3.solvePart1(StringFileReader.readInput("puzzles/day3/testinput.txt"));

        assertEquals(157, result);
    }

    @Test
    void solvePart2() {
        int result = Day3.solvePart2(StringFileReader.readInput("puzzles/day3/testinput.txt"));

        assertEquals(70, result);
    }
}
