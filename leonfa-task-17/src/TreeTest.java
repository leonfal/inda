import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Test class for a tree.
 *
 * @author Simon Larsén (PUT YOUR NAME HERE)
 * @version 2019-01-20 (UPDATE THIS)
 */
public class TreeTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(5);

    Tree<Integer> tree;
    int[] elementsInTree;
    int[] elementsNotInTree;

    @Before
    public void setUp() {
        /**
         * This tree should look like this:
         *
         *               8
         *             /  \
         *            3   10
         *           / \    \
         *          1   6    14
         *             / \   /
         *            4   7 13
         */
        tree = new Tree<>();
        elementsInTree = new int[] {8, 10, 14, 13, 3, 1, 6, 4, 7};
        for (int elem : elementsInTree) {
            tree.insert(elem);
        }
        elementsNotInTree = new int[] {34, -3, -10, 12, 74, 5};
    }

    // Tests for insert
    @Test
    public void insertIsTrueWhenElementIsNotInTree() {
        Arrays
            .stream(elementsNotInTree)
            // Act
            .mapToObj(elem -> tree.insert(elem))
            // Assert
            .forEach(wasInserted -> assertTrue(wasInserted));


    }

    @Test
    public void insertIsFalseForDuplicates() {
        // Arrange
        int expectedSize = elementsInTree.length;

        Arrays
            .stream(elementsInTree)
            // Act
            .mapToObj(elem -> tree.insert(elem))
            // Assert
            .forEach(wasInserted -> assertFalse(wasInserted));


        // Assert
        // Note that multiple asserts is not disallowed but can be confusing
        // The aim is always to test one logical unit in isolation
        // Here this second assert reinforces that the size has not changed
        int actualSize = tree.size();
        assertThat(actualSize, equalTo(expectedSize));

        /**
         * Again, a stream! What happens above is the following:
         *
         * IntStream.range(0, numDuplicates): creates a stream of
         * integers from 0 to numDuplicates - 1
         *
         * .mapToObj(i -> tree.insert(elem)): for every value produced
         * by the range stream, insert elem into the tree. The `i` is
         * not actually used. The resulting strem is the return values of
         * each `tree.insert(elem)` statement.
         *
         * .forEach(wasInserted -> asertFalse(wasInserted)): For each return
         * value in the mapToObj stream, assert that it is false!
         */
    }

    // Tests for search
    @Test
    public void searchIsTrueForInsertedElements() {
        // Assert that every value in elementsInTree is found in the tree field
        boolean check = false;
        for (int elem : elementsInTree) {
            check = tree.search(elem);
            assertTrue(check);
        }
    }

    @Test
    public void searchIsFalseForNonInsertedElements() {
        // Use the tree field and the elementsNotInTree field for this test
        boolean check = true;
        for (int elem : elementsNotInTree) {
            check = tree.search(elem);
            assertFalse(check);
        }
    }

    @Test
    public void searchIsFalseWhenTreeIsEmpty() {
        // Search for anything in an empty tree and it should be false
        Tree<Integer> emptyTree = new Tree<>();
        assertFalse(emptyTree.search(3));
    }

    @Test
    public void searchIsTrueForRootElementWhenTreeHasOnlyRoot() {
        // Arrange
        Tree<Integer> rootOnlyTree = new Tree<>();
        int rootElement = 1338;
        rootOnlyTree.insert(rootElement);

        // Act
        boolean rootFound = rootOnlyTree.search(rootElement);

        // Assert
        assertTrue(rootFound);
    }

    // Tests for insert/size
    @Test
    public void insertUniqueElementsIncrementsSize() {
        // Arrange
        Tree<Integer> tree = new Tree<>();
        int expectedSize = 0;

        for (int elem : elementsInTree) {
            expectedSize++;
            // Act
            tree.insert(elem);
            int actualSize = tree.size();

            // Assert
            assertThat(actualSize, equalTo(expectedSize));
        }
    }

    @Test
    public void insertDuplicateElementsDoesNotIncreaseSize() {
        // Arrange
        Tree<Integer> tree = new Tree<>();
        // The only element we insert
        int elem = 1338;
        tree.insert(elem);
        int numDuplicates = 100;

        // Act
        for (int i = 0; i < numDuplicates; i++) {
            tree.insert(elem);
        }

        // Assert
        assertThat(tree.size(), equalTo(1));
    }

    // Tests for leaves
    @Test
    public void leavesIsZeroWhenTreeIsEmpty() {
        Tree<Integer> emptyTree = new Tree<>();
        assertThat(emptyTree.leaves(), equalTo(0));
    }

    @Test
    public void leavesIsOneWhenTreeHasOnlyRoot() {
        Tree<Integer> oneRoot = new Tree<>();
        oneRoot.insert(10);
        assertThat(oneRoot.leaves(), equalTo(1));
    }

    @Test
    public void leavesIsTwoWhenPerfectTreeHasThreeNodes() {
        // Arrange
        Tree<Integer> tree = new Tree<>();
        // root must be smaller than one and larger than the other child
        tree.insert(1338); // root
        tree.insert(1337); // smaller child
        tree.insert(1396); // larger child

        // Act
        int numLeaves = tree.leaves();
        // Assert
        assertThat(numLeaves, equalTo(2));
    }

    @Test
    public void leavesIsCorrectWhenTreeIsPerfect() {
        // A perfect tree has all leaves at the same depth, and all internal nodes
        // (i.e. non-leaves) have two children
        //
        // This test should assert that a perfect tree with 2*n-1 nodes total,
        // has exactly n leaves (i.e. that Tree.leaves() returns n).
        //
        // An example is the perfect three-node tree from the test above:
        //
        //                        (1338)
        //                        /    \
        //                    (1337)  (1396)

        // You have to construct your own tree here, with n >= 4
        Tree<Integer> newTree = new Tree<>();
        newTree.insert(1000);
        newTree.insert(995);
        newTree.insert(1005);
        newTree.insert(999);
        newTree.insert(990);
        newTree.insert(1001);
        newTree.insert(1010);

        int numLeaves = tree.leaves();
        assertThat(numLeaves, equalTo(4));

    }

    @Test
    public void leavesIsOneWhenElementsWereInsertedInAscendingOrder() {
        // Arrange
        Tree<Integer> tree = new Tree<>();
        // insert elements in ascending order => all elements are inserted to the right
        int numElements = 100;
        for (int i = 0; i < numElements; i++) {
            tree.insert(i);
        }

        // Act
        int numLeaves = tree.leaves();
        // Assert
        assertThat(numLeaves, equalTo(1));
    }

    // Tests for height
    @Test
    public void heightIsZeroWhenTreeIsEmpty() {
        // Arrange
        Tree<Integer> emptyTree = new Tree<>();
        // Act
        int height = emptyTree.height();
        // Assert
        assertThat(height, equalTo(0));
    }

    @Test
    public void heightIsZeroWhenTreeHasOnlyRoot() {
        // Arrange
        Tree<Integer> rootOnlyTree = new Tree<>();
        rootOnlyTree.insert(1338);
        // Act
        int height = rootOnlyTree.height();
        // Assert
        assertThat(height, equalTo(0));
    }

    @Test
    public void heightIsLogOfNumLeavesTreeIsPerfect() {
        // For a perfect tree, tree.height() == log2(tree.leaves()

        // Arrange
        Tree<Integer> tree = new Tree<>();
        int[] elements = new int[] {8, 3, 10, 1, 6, 9, 14};
        int numLeaves = 4;
        int logNumLeaves = (int) Math.round(Math.log(numLeaves) / Math.log(2));
        for (int elem : elements) {
            tree.insert(elem);
        }

        // Act
        int height = tree.height();
        // Assert
        assertThat(height, equalTo(logNumLeaves));
    }

    // Tests for insert/height
    @Test
    public void insertValuesInAscendingOrderIncrementsHeight() {

        Tree<Integer> newTree = new Tree<>();
        int[] elements = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int elem : elements) {
            newTree.insert(elem);
        }

        int height = newTree.height();
        assertThat(height, equalTo(9)); // 9 because when only root height is 0.
    }

    @Test
    public void toStringIsBracketsWhenTreeIsEmpty() {
        // Arrange
        Tree<Integer> emptyTree = new Tree<>();
        String expectedString = "[]";
        // Act
        String actualString = emptyTree.toString();
        // Assert
        assertThat(actualString, equalTo(expectedString));
    }

    @Test
    public void toStringIsOnlyRootNodeWhenTreeHasOnlyRoot() {
        // Arrange
        Tree<Integer> rootOnlyTree = new Tree<>();
        int elem = 1338;
        rootOnlyTree.insert(elem);
        String expectedString = "[" + 1338 + "]";

        // Act
        String actualString = rootOnlyTree.toString();
        // Assert
        assertThat(actualString, equalTo(expectedString));
    }

    // Tests for toString
    @Test
    public void toStringIsEqualToSortedArrayRepresentationWhenTreeIsNotEmpty() {
        // Arrange
        Arrays.sort(elementsInTree);
        String expectedString = Arrays.toString(elementsInTree);

        // Act
        String actualString = tree.toString();
        // Assert
        assertThat(actualString, equalTo(expectedString));
    }
}
