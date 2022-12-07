package puzzles.day1;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    @Test
    void execute() {
        int result = Day1.executePartOne(StringFileReader.readInputToList("puzzles/day1/testinput.txt"));

        assertEquals(24000, result);
    }

    @Test
    void executePartTwo() {
        int result = Day1.executePartTwo(StringFileReader.readInputToList("puzzles/day1/testinput.txt"));

        assertEquals(45000, result);
    }
}
