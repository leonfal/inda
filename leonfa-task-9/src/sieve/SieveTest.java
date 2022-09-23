import org.junit.Test;
import org.junit.Before;
import java.lang.Math;
import static org.junit.Assert.*;
/**
 * Test class the Sieve class.
 *
 * @author Tobias Hansson
 * @author Leon Fällman
 * @version 2021-11-15
 */
public class SieveTest {
    private Sieve sieve;

    @Before
    public void setUp() {
        sieve = new Sieve();
    }

    @Test
    public void isPrimeTrueWhenNumberIsTwo() {
        assertTrue(sieve.isPrime(2));
    }

    @Test
    public void isPrimeTrueWhenNumberIsPrime() {
        boolean threeIsPrime = sieve.isPrime(3);
        boolean ninetySevenIsPrime = sieve.isPrime(97);
        boolean sevenIsPrime = sieve.isPrime(7);
        boolean fiveIsPrime = sieve.isPrime(5);

        assertTrue(threeIsPrime);
        assertTrue(ninetySevenIsPrime);
        assertTrue(sevenIsPrime);
        assertTrue(fiveIsPrime);
    }

    @Test
    public void isPrimeFalseWhenNumberIsComposite() {
        boolean fourIsPrime = sieve.isPrime(4);
        boolean nineHundredThreeIsPrime = sieve.isPrime(903);
        boolean sixIsPrime = sieve.isPrime(6);
        boolean thirtyFiveIsPrime = sieve.isPrime(35);

        assertFalse(fourIsPrime);
        assertFalse(nineHundredThreeIsPrime);
        assertFalse(sixIsPrime);
        assertFalse(thirtyFiveIsPrime);
    }

    @Test
    public void isPrimeWorksWhenPassedIncrementingValues() {
        boolean twoIsPrime = sieve.isPrime(2);
        boolean threeIsPrime = sieve.isPrime(3);
        boolean fourIsPrime = sieve.isPrime(4);
        boolean fiveIsPrime = sieve.isPrime(5);
        boolean sixIsPrime = sieve.isPrime(6);
        boolean sevenIsPrime = sieve.isPrime(7);
        boolean eightIsPrime = sieve.isPrime(8);
        boolean nineIsPrime = sieve.isPrime(9);

        assertTrue(twoIsPrime);
        assertTrue(threeIsPrime);
        assertFalse(fourIsPrime);
        assertTrue(fiveIsPrime);
        assertFalse(sixIsPrime);
        assertTrue(sevenIsPrime);
        assertFalse(eightIsPrime);
        assertFalse(nineIsPrime);
    }

    // Exercise S.1

    /**
     * Method to test the IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void isPrimeExceptionWhenNumberIsOne() {
        sieve.isPrime(1);
    }
    /**
     * Method to test the IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void isPrimeExceptionWhenNumberIsMinusTen() {
        sieve.isPrime(-10);
    }

    //Exercise S.3
    /**
     * Method to test that max value works
     */
    @Test
    public void isPrimeFalseWhenNumberIs2Pow26(){
        boolean twoPow26 = sieve.isPrime((int)Math.pow(2,26));
        assertFalse(twoPow26);
    }

    /**
     * Method to test IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void isPrimeExceptionWhenNumberIs2Pow26Plus1(){
        sieve.isPrime((int)Math.pow(2,26)+1);
    }

    /**
     * Method to test IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void isPrimeExceptionWhenNumberIs2Pow28(){
        sieve.isPrime((int)Math.pow(2,28));
    }


}
