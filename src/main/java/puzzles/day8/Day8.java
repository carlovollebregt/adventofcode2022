package puzzles.day8;

import utils.StringFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {
        Forest forest = new Forest(StringFileReader.readInputToList("puzzles/day8/input.txt"));
        System.out.println("Part 1: " + forest.countNumberOfVisibleTrees());
        System.out.println("Part 2: " + forest.getMaxScenicScore());
    }
}

class Forest {
    List<TreeRow> treeRows = new ArrayList<>();
    List<TreeRow> treeColumns = new ArrayList<>();

    public Forest(List<String> input) {
        input.stream().forEach(this::addTreeRow);
        mapTreeRowsToColumns();
        markVisibleTrees();
        calcScenicScore();
    }

    public void addTreeRow(String i) {
        TreeRow treeRow = new TreeRow();
        i.chars().forEach(treeRow::addTree);
        treeRows.add(treeRow);
    }

    public void mapTreeRowsToColumns() {
        for (int i = 0; i < treeRows.get(0).trees.size(); i++) {
			TreeRow treeColumn = new TreeRow();
			for (TreeRow treeRow : treeRows) {
                treeColumn.addTree(treeRow.trees.get(i));
			}
			treeColumns.add(treeColumn);
		}
    }

    public void markVisibleTrees() {
        treeRows.stream().forEach(TreeRow::markVisibleTrees);
        treeColumns.stream().forEach(TreeRow::markVisibleTrees);
    }

    public void calcScenicScore() {
        treeRows.stream().forEach(TreeRow::calcScenicScore);
        treeColumns.stream().forEach(TreeRow::calcScenicScore);
    }

    public int countNumberOfVisibleTrees() {
        return treeRows.stream()
            .mapToInt(TreeRow::getNumberOfVisibleTrees)
            .sum();
    }

    public int getMaxScenicScore() {
        return treeRows.stream()
            .mapToInt(TreeRow::getMaxScenicScore)
            .max()
            .orElse(0);
    }

}

class TreeRow {
    List<Tree> trees = new ArrayList<>();

    public void addTree(int i) {
        trees.add(new Tree(i - '0'));
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public int getMaxScenicScore() {
        return trees.stream()
				.map(tree -> tree.scenicScore)
				.max(Integer::compareTo)
				.orElse(0);
    }

    public int getNumberOfVisibleTrees() {
        return (int) trees.stream()
            .filter(t -> t.visible)
            .count();
    }

    public void calcScenicScore() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (i == 0 || i == trees.size() - 1) {
                tree.scenicScore = 0;
                continue;
            }

            int j = i + 1;
            while (j < trees.size() - 1 && trees.get(j).height < tree.height) {
                j++;
            }

            if (tree.scenicScore == -1) {
                tree.scenicScore = (j - i);
            } else {
                tree.scenicScore *= (j - i);
            }

            j = i - 1;
            while (j > 0 && trees.get(j).height < tree.height) {
                j--;
            }
            tree.scenicScore *= (i - j);
        }
    }

    public void markVisibleTrees() {
        markVisibleTreesFromFrontSide();
        Collections.reverse(trees);
        markVisibleTreesFromFrontSide();
        Collections.reverse(trees);
    }

    private void markVisibleTreesFromFrontSide() {
        int lastTreeHeight = -1;
        for (Tree tree : trees) {
            if (tree.height > lastTreeHeight) {
                tree.visible = true;
                lastTreeHeight = tree.height;
            }
        }
    }
}

class Tree {
    final int height;
    boolean visible = false;
    int scenicScore = -1;

    public Tree(int height) {
        this.height = height;
    }
}
