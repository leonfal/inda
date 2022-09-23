import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for GraphAlgorithms.
 *
 * @author [Name]
 * @version [Date]
 */

public class GraphAlgorithmsTest {
    @Before
    public void setUp() {}

    @Test
    public void hasCycleTrueMultipleComponents() {
        // Arrange
        // Three components:
        // 0 - 1 - 5
        // 2 - 3
        //  \ /
        //   9
        // 4 - 6 - 7 - 8
        Graph g = new Graph(10);
        g.add(0, 1, 0);
        g.add(1, 5, 0);
        g.add(9, 2, 0);
        g.add(2, 3, 0);
        g.add(3, 9, 0);
        g.add(4, 6, 0);
        g.add(6, 7, 0);
        g.add(7, 8, 0);

        // Act
        boolean hasCycle = GraphAlgorithms.hasCycle(g);

        // Assert
        assertTrue(hasCycle);
    }

    @Test
    public void hasPathTrueTreeGraph() {
        // Arrange
        //            0
        //           / \
        //          1   2
        //         / \   \
        //        3   4   5
        //       / \   \
        //      6   7   8
        //               \
        //                9
        Graph g = new Graph(10);
        g.add(0, 1, 0);
        g.add(0, 2, 0);
        g.add(1, 3, 0);
        g.add(1, 4, 0);
        g.add(2, 5, 0);
        g.add(3, 6, 0);
        g.add(3, 7, 0);
        g.add(4, 8, 0);
        g.add(8, 9, 0);

        // Act
        boolean hasPath = GraphAlgorithms.hasPath(g, 0, 9);

        // Assert
        assertTrue(hasPath);
    }

    @Test
    public void hasCycleFalseSingleComponent() {
        Graph g = new Graph(4);
        g.add(0, 1, 0);
        g.add(1, 2, 0);
        g.add(2, 3, 0);

        // 0 - 1 - 2 - 3

        // Act
        boolean hasCycle = GraphAlgorithms.hasCycle(g);

        // Assert
        assertFalse(hasCycle);
    }

  @Test
  public void hasCycleTrueSingleComponent() {
        Graph g = new Graph(4);
        g.add(1, 2, 0);
        g.add(2, 3, 0);
        g.add(3, 1, 0);

        boolean hasCycle = GraphAlgorithms.hasCycle(g);
        //Assert
        assertTrue(hasCycle);
  }

  @Test
  public void hasCycleFalseMultipleComponents() {
      Graph g = new Graph(10);
        g.add(0, 1, 0);
        g.add(1, 5, 0);
        g.add(2, 3, 0);
        g.add(3, 9, 0);
        g.add(4, 6, 0);
        g.add(6, 7, 0);
        g.add(7, 8, 0);

        boolean hasCycle = GraphAlgorithms.hasCycle(g);

        assertFalse(hasCycle);
  }

    @Test
    public void graphWithCycleHasPathToSelf() {
        Graph g = new Graph(4);
        g.add(1, 2, 0);
        g.add(2, 3, 0);
        g.add(3, 1, 0);

        boolean hasPath = GraphAlgorithms.hasPath(g, 1, 1);
        //Assert
        assertTrue(hasPath);
    }

    @Test
        public void verteciesOnDifferentComponentsHasPathIsFalse() {
        Graph g = new Graph(10);
        g.add(0, 1, 0);
        g.add(1, 5, 0);
        g.add(9, 2, 0);
        g.add(2, 3, 0);
        g.add(3, 9, 0);
        g.add(4, 6, 0);
        g.add(6, 7, 0);
        g.add(7, 8, 0);

        boolean hasPath = GraphAlgorithms.hasPath(g, 1, 6);

        assertFalse(hasPath);
    }

    @Test
    public void graphWithVerteciesWithoutEdgesHasPathIsFalse() {
        Graph g = new Graph(3);
        // constructs a tree with no edges.
        // 0 1
        // 2 3

        boolean hasPath = GraphAlgorithms.hasPath(g, 0, 1);

        assertFalse(hasPath);
    }
}
