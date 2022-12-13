package puzzles.day12;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    private Node start;

    private List<Node> possibleStarters = new ArrayList<>();

    private List<Row> rows = new ArrayList<>();

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        day12.init(StringFileReader.readInputToList("puzzles/day12/input.txt"));
        System.out.println("Part 1: " + day12.solvePart1());
        System.out.println("Part 2: " + day12.solvePart2());
    }

    void init(List<String> input) {
        for (String inputRow : input) {
            Row row = new Row();

            for (int i = 0; i < inputRow.length(); i++) {
                char c = inputRow.charAt(i);
                switch (c) {
                    case 'S' -> {
                        Node node = new Node('a' - 'a', i, rows.size());
                        row.add(node);
                        start = node;
                    }
                    case 'E' -> {
                        Node node = new Node('z' - 'a' + 1, i, rows.size());
                        row.add(node);
                    }
                    default -> {
                        Node node = new Node(c - 'a', i, rows.size());
                        row.add(node);
                        if (c == 'a') {
                            possibleStarters.add(node);
                        }
                    }
                }
            }
            rows.add(row);
        }
    }

    private void createGraph() {
        // create node graph from input with all possible moves between the nodes
        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i);
            for (int j = 0; j < row.nodes.size(); j++) {
                Node node = row.nodes.get(j);
                if (j > 0 && row.nodes.get(j - 1).getValue() < node.getValue() + 2) {
                    node.connect(row.nodes.get(j - 1));
                }
                if (j < row.nodes.size() - 1 && row.nodes.get(j + 1).getValue() < node.getValue() + 2) {
                    node.connect(row.nodes.get(j + 1));
                }
                if (i > 0 && rows.get(i - 1).nodes.get(j).getValue() < node.getValue() + 2) {
                    node.connect(rows.get(i - 1).nodes.get(j));
                }
                if (i < rows.size() - 1 && rows.get(i + 1).nodes.get(j).getValue() < node.getValue() + 2) {
                    node.connect(rows.get(i + 1).nodes.get(j));
                }
            }
        }
    }

    int solvePart1() {
        createGraph();
        return BreadthFirstSearchAlgorithm.search('z' - 'a' + 1, start);
    }

    int solvePart2() {
        createGraph();
        // somehow revering the graph did not succeed, so brute force the solution...
        return possibleStarters.stream()
            .map(starter -> BreadthFirstSearchAlgorithm.search('z' - 'a' + 1, starter))
            .filter(i -> i > 0)  // I assume there are unreachable 'a' nodes in the grid...
            .min(Integer::compareTo)
            .orElseThrow();
    }
}

class Row {
    List<Node> nodes = new ArrayList<>();

    void add(Node node) {
		nodes.add(node);
    }
}


