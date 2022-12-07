package puzzles.day7;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7 {

    private final Dir root = new Dir("root", null);

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        day7.createFilesystem(StringFileReader.readInputToList("puzzles/day7/input.txt"));
        System.out.println(day7.solvePart1());
        System.out.println(day7.solvePart2());
    }

    void createFilesystem(List<String> input) {
        var currentDir = root;

        for (String commandOrResult : input) {
            // this is using Java 19 Pattern matching for switch Third Preview,
            // you might need to add '--enable-preview' to the Java command when compiling and running this code
            switch (commandOrResult) {
                case String s when s.startsWith("$") -> currentDir = executeCommand(commandOrResult, currentDir);
                case String s when s.startsWith("dir") -> currentDir.addSubDir(commandOrResult.substring(4));
                default -> currentDir.addFile(commandOrResult);
            }
        }
    }

    private Dir executeCommand(String command, Dir current) {
        var commands = command.split(" ");
        return switch (commands[1]) {
            case "cd" -> changeDir(current, commands[2]);
            case "ls", default -> current;
        };
    }

    private Dir changeDir(Dir current, String command) {
        return switch (command) {
            case ".." -> current.parentDir;
            case "/" -> root;
            default -> current.findDir(command);
        };
    }

    long solvePart1() {
        return root.getAllSubDirs().stream()
            .filter(dir -> dir.size() < 100000)
            .mapToLong(Dir::size)
            .sum();
    }

    long solvePart2() {
        long currentFree = 70000000 - root.size();
        long neededDelete = 30000000 - currentFree;

        return root.getAllSubDirs().stream()
            .filter(dir -> dir.size() > neededDelete)
            .min(Comparator.comparingLong(Dir::size))
            .map(Dir::size)
            .orElseThrow();
    }
}

class Dir {
    String name;
    Dir parentDir;
    List<Dir> subDirs = new ArrayList<>();
    List<File> files = new ArrayList<>();

    public Dir(String name, Dir parentDir) {
        this.name = name;
		this.parentDir = parentDir;
    }

    public Dir findDir(String command) {
        return subDirs.stream()
            .filter(dir -> dir.name.equals(command))
            .findFirst()
            .orElseThrow();
    }

    void addSubDir(String name) {
        subDirs.add(new Dir(name, this));
    }

    void addFile(String result) {
        var resultParts = result.split(" ");
        var name = resultParts[1];
        var size = Integer.parseInt(resultParts[0]);
        files.add(new File(name, size));
    }

    long size() {
        long fileSize = files.stream()
            .map(File::size)
            .reduce(0L, Long::sum);
        long subDirSize = subDirs.stream()
            .map(Dir::size)
            .reduce(0L, Long::sum);
        return fileSize + subDirSize;
    }

    List<Dir> getAllSubDirs() {
        List<Dir> collect = new ArrayList<>(subDirs);
        subDirs.forEach(s -> collect.addAll(s.getAllSubDirs()));
        return collect;
    }
}

record File(String name, long size) {
}
