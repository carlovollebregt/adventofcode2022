package puzzles.day4;

import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {

	@Test
	void solvePart1() {
		long result = Day4.solvePart1(StringFileReader.readInput("puzzles/day4/testinput.txt"));

		assertEquals(2, result);
	}

	@Test
	void solvePart2() {
		long result = Day4.solvePart2(StringFileReader.readInput("puzzles/day4/testinput.txt"));

		assertEquals(4, result);
	}
}
