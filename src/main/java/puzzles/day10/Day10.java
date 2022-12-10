package puzzles.day10;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    public static void main(String[] args) {
        solve(StringFileReader.readInputToList("puzzles/day10/input.txt"));
    }

    static long solve(List<String> operations) {
        CPU cpu = new CPU();
        cpu.run(operations);

        System.out.println("Part 1 : " + cpu.sum + "\n");
        System.out.println("Part 2:\n" + cpu.crt.output());
        return cpu.sum;
    }

}

class CPU {
    int cycle = 0;
    int registerX = 1;
    int sum = 0;
    CRT crt = new CRT();

    void run(List<String> operations) {
        for (String operation : operations) {
            executeCycle();
            if ("noop".equals(operation)) {
                continue;
            }
            executeCycle();
            registerX += Integer.parseInt(operation.split(" ")[1]);
        }
    }

    void executeCycle() {
        crt.addPixel(cycle, registerX);
        cycle++;
        if (((cycle + 20) % 40) == 0) {
            sum += cycle * registerX;
        }
    }
}

class CRT {
    List<Row> rows = new ArrayList<>();
    Row activeRow;

    void addPixel(int cycle, int registerX) {
        if (cycle % 40 == 0) {
            activeRow = new Row();
            rows.add(activeRow);
        }
        int position = cycle % 40;
        boolean state = (position > registerX - 2 && position < registerX + 2);
        activeRow.addPixel(new Pixel(state));
    }

    String output() {
        return rows.stream()
            .map(Row::output)
            .collect(Collectors.joining("\n"));
    }
}

class Row {
    List<Pixel> pixels = new ArrayList<>();

    void addPixel(Pixel pixel) {
		pixels.add(pixel);
    }

    String output() {
        return pixels.stream()
            .map(Pixel::output)
            .collect(Collectors.joining());
    }
}

record Pixel(boolean on) {
    String output() {
        return on ? "#" : " ";
    }
}
