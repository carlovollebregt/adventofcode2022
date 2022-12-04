package puzzles.day1;

import utils.StringFileReader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

	public static void main(String[] args) {
		System.out.println(executePartOne(StringFileReader.readInput("puzzles/day1/input.txt")));
		System.out.println(executePartTwo(StringFileReader.readInput("puzzles/day1/input.txt")));
	}

	public static int executePartOne(List<String> input) {
		return getTotalCaloriesPerElf(input)
				.max(Integer::compare)
				.get();
	}
	public static int executePartTwo(List<String> input) {
		return getTotalCaloriesPerElf(input)
				.limit(3)
				.reduce(0, Integer::sum);
	}

	private static Stream<Integer> getTotalCaloriesPerElf(List<String> input) {
		return splitCaloriesPerElf(input)
				.map(Day1::getTotalCaloriesForElf)
				.sorted(Comparator.reverseOrder());
	}

	private static Stream<String> splitCaloriesPerElf(List<String> input) {
		return Arrays.stream(input.stream()
				.collect(Collectors.joining(","))
				.split(",,"));
	}
	private static int getTotalCaloriesForElf(String values) {
		return Arrays.stream(values.split(","))
				.mapToInt(Integer::parseInt)
				.sum();
	}
}
