package puzzles.day7;

import org.junit.jupiter.api.Test;

import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {
    @Test
    void testPart1() {
        Day7 day7 = new Day7();
        day7.createFilesystem(StringFileReader.readInputToList("puzzles/day7/testinput.txt"));
        long result = day7.solvePart1();

        assertEquals(95437, result);
    }

    @Test
    void testPart2() {
        Day7 day7 = new Day7();
        day7.createFilesystem(StringFileReader.readInputToList("puzzles/day7/testinput.txt"));
        long result = day7.solvePart2();

        assertEquals(24933642, result);
    }
}
