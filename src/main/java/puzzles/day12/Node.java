package puzzles.day12;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Node {

    private int value;
    private Set<Node> neighbors;
    private int x;
    private int y;
    private int distance;

    public Node(int value, int x, int y) {
		this.value = value;
		this.neighbors = new HashSet<>();
		this.x = x;
		this.y = y;
	}

    public int getValue() {
        return value;
    }

    public Set<Node> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }

    public void connect(Node node) {
        if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
        this.neighbors.add(node);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
