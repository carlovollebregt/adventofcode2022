package puzzles.day4;

import utils.StringFileReader;
import java.util.List;

public class Day4 {

	public static void main(String[] args) {
		System.out.println(solvePart1(StringFileReader.readInput("puzzles/day4/input.txt")));
		System.out.println(solvePart2(StringFileReader.readInput("puzzles/day4/input.txt")));
	}

    protected static long solvePart1(List<String> inputs) {
		return inputs.stream()
				.map(AssignmentPair::fromString)
				.filter(AssignmentPair::overlapsCompletely)
				.count();
	}

	protected static long solvePart2(List<String> inputs) {
		return inputs.stream()
				.map(AssignmentPair::fromString)
				.filter(AssignmentPair::overlapsParty)
				.count();
	}
}

