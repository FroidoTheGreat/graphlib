package graphlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import assignment.Islands;

public class TestLargestIsland {
    @Test
    public void testLargestIsland()
    {
        try {
            assertEquals(0, Islands.largestIslandFromFile("empty"));
            assertEquals(16, Islands.largestIslandFromFile("full"));
            assertEquals(1, Islands.largestIslandFromFile("one"));
            assertEquals(6, Islands.largestIslandFromFile("equal"));
            assertEquals(13, Islands.largestIslandFromFile("isthmus"));
            assertEquals(3, Islands.largestIslandFromFile("line"));
            assertEquals(0, Islands.largestIslandFromFile("void"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
