package puzzles.day2;

import utils.StringFileReader;

import java.util.List;
import java.util.Map;

public class Day2 {

    static final Map<Shape, Map<Shape, Integer>> playScores = Map.of(
        Shape.ROCK, Map.of(Shape.ROCK, 3, Shape.SCISSORS, 6, Shape.PAPER, 0),
        Shape.PAPER, Map.of(Shape.ROCK, 6, Shape.SCISSORS, 0, Shape.PAPER, 3),
        Shape.SCISSORS, Map.of(Shape.ROCK, 0, Shape.SCISSORS, 3, Shape.PAPER, 6)
    );

    static final Map<Shape, Map<String, Shape>> myShapePart2 = Map.of(
        Shape.ROCK, Map.of("X", Shape.SCISSORS, "Y", Shape.ROCK, "Z", Shape.PAPER),
        Shape.PAPER, Map.of("X", Shape.ROCK, "Y", Shape.PAPER, "Z", Shape.SCISSORS),
        Shape.SCISSORS, Map.of("X", Shape.PAPER, "Y", Shape.SCISSORS, "Z", Shape.ROCK)
    );

    public static void main(String[] args) {
        System.out.println(solvePart1(StringFileReader.readInputToList("puzzles/day2/input.txt")));
        System.out.println(solvePart2(StringFileReader.readInputToList("puzzles/day2/input.txt")));
    }

    protected static int solvePart1(List<String> input) {
        return input.stream()
            .mapToInt(Day2::totalPointsForRound)
            .sum();
    }

    private static int totalPointsForRound(String roundInput) {
        Shape otherShape = Shape.getShape(roundInput.substring(0, 1));
        Shape myShape = Shape.getShape(roundInput.substring(2, 3));
        return myShape.points + playScores.get(myShape).get(otherShape);
    }

    // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
    protected static int solvePart2(List<String> input) {
        return input.stream()
            .mapToInt(Day2::totalPointsForRoundPart2)
            .sum();
    }

    private static int totalPointsForRoundPart2(String roundInput) {
        Shape otherShape = Shape.getShape(roundInput.substring(0, 1));
        Shape myShape = myShapePart2.get(otherShape).get(roundInput.substring(2, 3));
        return myShape.points + playScores.get(myShape).get(otherShape);
    }

    private enum Shape {
        ROCK(1), PAPER(2), SCISSORS(3);

        final int points;

        Shape(int points) {
            this.points = points;
        }

        static Shape getShape(String code) {
            return switch (code) {
                case "A", "X" -> ROCK;
                case "B", "Y" -> PAPER;
                case "C", "Z" -> SCISSORS;
                default -> throw new UnsupportedOperationException();
            };
        }
    }
}
