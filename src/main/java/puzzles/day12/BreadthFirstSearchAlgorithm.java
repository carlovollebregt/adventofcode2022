package puzzles.day12;

import java.util.*;

public class BreadthFirstSearchAlgorithm {

    public static int search(int value, Node start) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> alreadyVisited = new HashSet<>();
        start.setDistance(0);
        queue.add(start);

        while (!queue.isEmpty()) {
            final Node currentNode = queue.remove();

            if (currentNode.getValue() == value) {
                return currentNode.getDistance();
            } else {
                alreadyVisited.add(currentNode);
                for (Node neighbor : currentNode.getNeighbors()) {
                    neighbor.setDistance(currentNode.getDistance() + 1);
    				queue.add(neighbor);
				}
                queue.removeAll(alreadyVisited);
            }
        }

        return -1;
    }

}
