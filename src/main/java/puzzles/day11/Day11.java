package puzzles.day11;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {

    public static void main(String[] args) {
        System.out.println("Part 1: " + solve(StringFileReader.readInputToList("puzzles/day11/input.txt"), 20,false));
        System.out.println("Part 2: " + solve(StringFileReader.readInputToList("puzzles/day11/input.txt"), 10000, true));
    }

    static long solve(List<String> monkeyProperties, int rounds, boolean useModForPart2) {
        Game game = new Game(monkeyProperties, useModForPart2);
        return game.run(rounds);
    }
}

class Game {
    List<Monkey> monkeys = new ArrayList<>();
    Integer modForPart2;
    
    public Game(List<String> monkeyProperties, boolean useModForPart2) {
        List<String> propertiesPerMonkey = getPropertiesPerMonkey(monkeyProperties);

        propertiesPerMonkey.stream()
            .map(s -> s.split("/"))
            .map(Monkey::new)
            .forEach(m -> monkeys.add(m));

        // I needed to cheat by looking in solutions posted on Reddit to find this one...
        if (useModForPart2) {
            modForPart2 = monkeys.stream()
                .map(m -> m.testDivisibleBy)
                .reduce((a, b) -> a * b)
                .orElseThrow();
        }
    }

    private static List<String> getPropertiesPerMonkey(List<String> monkeyProperties) {
        return Arrays.stream(monkeyProperties.stream()
                .collect(Collectors.joining("/"))
                .split("//"))
            .toList();
    }

    public long run(int rounds) {
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                for (Item item : monkey.items) {
                    int toMonkey = monkey.inspectItemAndThrow(item, modForPart2);
                    monkeys.get(toMonkey).items.add(item);
                }
                monkey.items = new ArrayList<>();
            }
        }
		return monkeys.stream()
            .map(m -> m.numberOfInspectedItems)
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce((a, b) -> a * b)
            .orElseThrow();
	}
}

class Monkey {
    List<Item> items = new ArrayList<>();
    String name;
    String operation;
    int factor;
    int testDivisibleBy;
    int throwToMonkeyIfTestTrue;
    int throwToMonkeyIfTestFalse;
    long numberOfInspectedItems;

    public Monkey(String[] properties) {
        for (String property : properties) {
            switch (property) {
                case String s when s.contains("Monkey") -> name = s;
                case String s when s.contains("Starting") -> parseItems(s);
                case String s when s.contains("Operation") -> parseOperation(s);
                case String s when s.contains("Test") -> parseTest(s);
                case String s when s.contains("If true") -> throwToMonkeyIfTestTrue = Integer.parseInt(s.split(" ")[9]);
                case String s when s.contains("If false") -> throwToMonkeyIfTestFalse = Integer.parseInt(s.split(" ")[9]);
                default -> throw new UnsupportedOperationException("Got unhandled property: " + property);
            }
        }
    }

    int inspectItemAndThrow(Item item, Integer modForPart2) {
        long factorToUse = (factor == -1) ? item.worryLevel : factor;
        switch (operation) {
            case "+" -> item.worryLevel += factorToUse;
            case "*" -> item.worryLevel *= factorToUse;
            default -> throw new UnsupportedOperationException(operation);
        }
        if (modForPart2 == null) {
            item.worryLevel /= 3;
        } else {
            item.worryLevel %= modForPart2;
        }
        numberOfInspectedItems++;
        return (item.worryLevel % testDivisibleBy == 0) ? throwToMonkeyIfTestTrue : throwToMonkeyIfTestFalse;
    }

    private void parseItems(String s) {
        String[] items = s.split(" ");
        for (int i = 4; i < items.length; i++) {
            String item = items[i];
            if (item.contains(",")) {
                item = item.replace(",", "");
            }
            this.items.add(new Item(Integer.parseInt(item)));
        }
    }

    private void parseOperation(String s) {
        String[] items = s.split(" ");
        operation = items[6];
        if ("old".equals(items[7])) {
            factor = -1;
        } else {
            factor = Integer.parseInt(items[7]);
        }
    }

    private void parseTest(String s) {
		testDivisibleBy = Integer.parseInt(s.split(" ")[5]);
    }
}

class Item {
    long worryLevel;

    public Item(int worryLevel) {
		this.worryLevel = worryLevel;
    }
}
