import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;
/**
 * Abstract test class for  implementations.
 *
 * Implementing test classes must override the getIntSorter method.
 *
 * @author Leon Fällman
 * @version 2022-02-27
 */
public abstract class IntSorterTest {
    private int[] emptyArray;
    private int[] oddArray;
    private int[] evenArray;
    private int[] sortedAscending;
    private int[] sortedDescending;
    private int[] random;
    private int[] equals;
    private int[] hugeArray;

    private IntSorter intSorter;

    /**
     * Returns an implementation of the IntSorter interface. Extending classes
     * must override this method.
     *
     * @return An implementation of IntSorter.
     */
    protected abstract IntSorter getIntSorter();

    @Before
    public void setUp() {
        // []
        emptyArray = new int[0];

        // length ODD_LENGTH with random numbers from 1-100
        int ODD_LENGTH = 99;
        Data odd = new Data(ODD_LENGTH, 100, Data.Order.RANDOM);
        oddArray = odd.get();

        // length EVEN_LENGTH with random numbers from 1-100
        int EVEN_LENGTH = 100;
        Data even = new Data(EVEN_LENGTH, 100, Data.Order.RANDOM);
        evenArray = even.get();

        // length 20 with random numbers from 1-50 ascending
        Data ascending = new Data(20, 50, Data.Order.ASCENDING);
        sortedAscending = ascending.get();

        // length 20 with random numbers from 1-50 descending
        Data descending = new Data(20, 50, Data.Order.DESCENDING);
        sortedDescending = descending.get();

        // length 10 with random numbers from 1-100
        Data randomOrder = new Data(10, 100, Data.Order.RANDOM);
        random = randomOrder.get();

        // [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        Data equal = new Data(10, 1, Data.Order.RANDOM);
        equals = equal.get();

        Data huge = new Data(100000, 100000, Data.Order.RANDOM);
        hugeArray = huge.get();
        // instantiate an intSorter
        intSorter = getIntSorter();
    }

    @Test
    public void sortHasNoEffectWhenArrayIsEmpty() {
        // Act, Arrange
        int[] expected = emptyArray.clone();
        intSorter.sort(emptyArray);
        // Assert
        assertArrayEquals(expected, emptyArray);
    }

    @Test
    public void sortHasNoEffectSingleElement() {
        // Arrange
        int[] singleArray = { 43 };
        int[] expected = singleArray.clone();
        // Act, Assert
        intSorter.sort(singleArray);
        assertArrayEquals(expected, singleArray);
    }

    @Test
    public void sortEvenArrayIsCorrect() {
        // Arrange
        int[] actual = evenArray;
        int[] expected = actual.clone();

        // Act
        intSorter.sort(actual);
        Arrays.sort(expected);

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortOddArrayIsCorrect() {
        // Arrange
        int[] actual = oddArray;
        int[] expected = actual.clone();

        // Act
        intSorter.sort(actual);
        Arrays.sort(expected);

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortAscendingArrayHasNoEffect() {
        // Arrange
        int[] expected = sortedAscending.clone();
        // Act
        intSorter.sort(sortedAscending);
        Arrays.sort(expected);
        //Assert
        assertArrayEquals(expected, sortedAscending);
    }

    @Test
    public void sortDescendingArrayReversesCorrect() {
        // Arrange
        int[] actual = sortedDescending;
        int[] expected = sortedDescending.clone();

        // Act
        intSorter.sort(actual);
        Arrays.sort(expected);

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortArbitraryArrayIsCorrect() {
        // Arrange
        int[] expected = random.clone();
        // Act
        intSorter.sort(random);
        Arrays.sort(expected);
        //Assert
        assertArrayEquals(expected, random);
    }

    @Test
    public void sortAllEqualElementsIsCorrect() {
        // Arrange, Act
        int[] expected = equals.clone();
        intSorter.sort(equals);
        //Assert
        assertArrayEquals(expected, equals);
    }

    @Test
    public void sortHugeArrayIsCorrect() {
        // Arrange
        int[] expected = hugeArray.clone();
        // Act
        intSorter.sort(hugeArray);
        Arrays.sort(expected);
        //Assert
        assertArrayEquals(expected, hugeArray);
    }
}

