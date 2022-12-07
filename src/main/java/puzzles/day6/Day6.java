package puzzles.day6;

import utils.StringFileReader;

import java.util.stream.IntStream;

public class Day6 {

    public static void main(String[] args) {
        System.out.println(solve(StringFileReader.readInputToString("puzzles/day6/input.txt"), 4));
        System.out.println(solve(StringFileReader.readInputToString("puzzles/day6/input.txt"), 14));
    }

    protected static int solve(String input, int distinctCharacters) {
        return IntStream.range(0, input.length())
            .mapToObj(i -> input.substring(i, i + distinctCharacters))
            .filter(Day6::findMarker)
            .findFirst()
            .map(s -> input.indexOf(s) + distinctCharacters)
            .orElseThrow();
    }

    private static boolean findMarker(String i) {
        return i.chars().distinct().count() == i.length();
    }
}
