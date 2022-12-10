package puzzles.day9;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9 {

    public static void main(String[] args) {
        System.out.println("Part 1 : " + solve(StringFileReader.readInputToList("puzzles/day9/input.txt"),2 ));
        System.out.println("Part 2 : " + solve(StringFileReader.readInputToList("puzzles/day9/input.txt"),10 ));
    }

    public static long solve(List<String> moves, int numberOfKnots) {
        Grid grid = new Grid(numberOfKnots);
        grid.run(moves);
        System.out.println(grid.output());
        return grid.countTailPositions();
    }
}

class Grid {
    List<Row> rows = new ArrayList<>();
    List<Knot> knots = new ArrayList<>();

    public Grid(int numberOfKnots) {
		IntStream.range(0, numberOfKnots)
            .forEach(i -> knots.add(new Knot()));
        IntStream.range(0, 500)
            .forEach(i -> rows.add(new Row()));
    }

    public long countTailPositions() {
        return rows.stream()
				.map(Row::countTailPositions)
				.reduce(0L, Long::sum);
    }

    public String output() {
        return rows.stream()
            .map(Row::output)
            .collect(Collectors.joining("\n"));
    }

    void run(List<String> moves) {
        for (String move : moves) {
            String[] parts = move.split(" ");
            String direction = parts[0];
            int distance = Integer.parseInt(parts[1]);

            for (int i = 0; i < distance; i++) {
                Knot knot = knots.get(0);
                switch (direction) {
                    case "L" -> knot.x--;
					case "R" -> knot.x++;
					case "U" -> knot.y--;
                    case "D" -> knot.y++;
                }

                for (Knot nextKnot : knots.subList(1, knots.size())) {
                    nextKnot.move(knot);
                    knot = nextKnot;
                }

                rows.get(knot.y).points.get(knot.x).hit = true;
            }
        }
    }
}

class Knot {
    int x = 250;
    int y = 250;

    void move(Knot previousKnot) {
        if (previousKnot.x == x || previousKnot.y == y) {
            if (previousKnot.x > x + 1) {
                x++;
            } else if (previousKnot.x < x - 1) {
                x--;
            }
            if (previousKnot.y > y + 1) {
                y++;
            } else if (previousKnot.y < y - 1) {
                y--;
            }
        } else {
            if (previousKnot.x > x + 1 || previousKnot.y > y + 1 || previousKnot.x < x - 1 || previousKnot.y < y - 1) {
                if (previousKnot.x > x) {
                    x++;
                } else {
                    x--;
                }
                if (previousKnot.y > y) {
                    y++;
                } else {
                    y--;
                }
            }
        }
    }
}

class Row {
    List<Point> points = new ArrayList<>();

    public Row() {
        IntStream.range(0, 500)
            .forEach(i -> points.add(new Point()));
    }

    public long countTailPositions() {
        return points.stream()
				.filter(p -> p.hit)
				.count();
    }

    public String output() {
        return points.stream()
            .map(p -> p.hit ? "#" : ".")
            .collect(Collectors.joining(""));
    }
}

class Point {
    boolean hit = false;
}
