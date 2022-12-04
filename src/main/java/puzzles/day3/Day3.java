package puzzles.day3;

import utils.StringFileReader;

import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        System.out.println(solvePart1(StringFileReader.readInput("puzzles/day3/input.txt")));
        System.out.println(solvePart2(StringFileReader.readInput("puzzles/day3/input.txt")));
    }

    protected static int solvePart1(List<String> inputs) {
        return inputs.stream()
            .mapToInt(Day3::calculatePart1)
            .sum();
    }

    protected static int solvePart2(List<String> inputs) {
        int sum = 0;
        for (int i = 0; i < inputs.size(); i += 3) {
            sum += calculatePart2(inputs.get(i), inputs.get(i + 1), inputs.get(i + 2));
        }
        return sum;
    }

    private static int calculatePart1(String s) {
        int mid = s.length() / 2;
        String part1 = s.substring(0, mid);
        String part2 = s.substring(mid);
        int matchingChar = findMatchingChar(part1, part2);
        return getPriority(matchingChar);
    }

    private static int getPriority(int matchingChar) {
        if (matchingChar > 96) {
            return matchingChar - 96;
        } else {
            return matchingChar - 64 + 26;
        }
    }

    private static int calculatePart2(String s1, String s2, String s3) {
        int matchingChar = findMatchingChar(s1, s2, s3);
        return getPriority(matchingChar);
    }

    public static int findMatchingChar(String s1, String s2) {
        return s1.replaceAll("[^" + s2 + "]", "").charAt(0);
    }

    public static int findMatchingChar(String s1, String s2, String s3) {
        return s1.replaceAll("[^" + s2 + "]", "").replaceAll("[^" + s3 + "]", "").charAt(0);
    }
}
