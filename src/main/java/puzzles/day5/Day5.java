package puzzles.day5;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) {
        System.out.println(solve(StringFileReader.readInputToList("puzzles/day5/input.txt"), false));
        System.out.println(solve(StringFileReader.readInputToList("puzzles/day5/input.txt"), true));
    }

    protected static String solve(List<String> inputs, boolean moveMultiple) {
        List<String> rows = new ArrayList<>();
        Iterator<String> iterator = inputs.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("".equals(next.trim())) {
                break;
            }
            rows.add(next);
        }

        Ship ship = new Ship(rows);

        while (iterator.hasNext()) {
            ship.move(iterator.next(), moveMultiple);
        }

        return ship.getTopCrates();
    }
}

class Ship {
    List<Stack<String>> stacks = new ArrayList<>();

    public Ship(List<String> rows) {
        Collections.reverse(rows);
        rows.remove(0);

        for (String row : rows) {
            int stack = 0;
            while ((stack * 3) + stack + 3 <= row.length()) {
                String crate = row.substring((stack * 3) + stack, (stack * 3) + stack + 3);
                if (!"".equals(crate.trim())) {
                    if (stacks.size() < stack + 1) {
                        stacks.add(new Stack<>());
                    }
                    stacks.get(stack).add(crate);
                }
                stack++;
            }
        }
    }

    public void move(String instruction, boolean moveMultiple) {
        String[] instructions = instruction.split(" ");
        int numberOfCrates = Integer.parseInt(instructions[1]);
        int fromStack = Integer.parseInt(instructions[3]) - 1;
        int toStack = Integer.parseInt(instructions[5]) - 1;

        Stack<String> tempStack = new Stack<>();
        for (int i = 0; i < numberOfCrates; i++) {
            String crate = stacks.get(fromStack).pop();
            tempStack.push(crate);
        }
        if (!moveMultiple) {
            Collections.reverse(tempStack);
        }
        for (int i = 0; i < numberOfCrates; i++) {
            String crate = tempStack.pop();
            stacks.get(toStack).push(crate);
        }
    }

    public String getTopCrates() {
        return stacks.stream()
            .filter(stack -> !stack.isEmpty())
            .map(stack -> stack.pop().charAt(1))
            .map(Object::toString)
            .collect(Collectors.joining());
    }
}

