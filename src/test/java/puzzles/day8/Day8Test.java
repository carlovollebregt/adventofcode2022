package puzzles.day8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.StringFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    static Forest forest;

    @BeforeAll
    static void beforeAll() {
        forest = new Forest(StringFileReader.readInputToList("puzzles/day8/testinput.txt"));
    }

    @Test
    void testPart1() {
        assertEquals(21, forest.countNumberOfVisibleTrees());
    }

    @Test
    void testPart2() {
        assertEquals(8, forest.getMaxScenicScore());
    }
}


