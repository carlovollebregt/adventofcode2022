package puzzles.day5;

import org.junit.jupiter.api.Test;

import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {
    @Test
    void solvePart1() {
        String result = Day5.solve(StringFileReader.readInputToList("puzzles/day5/testinput.txt"), false);

        assertEquals("CMZ", result);
    }

    @Test
    void solvePart2() {
        String result = Day5.solve(StringFileReader.readInputToList("puzzles/day5/testinput.txt"), true);

        assertEquals("MCD", result);
    }
}
