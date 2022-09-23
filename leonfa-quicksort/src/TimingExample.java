import java.util.*;
import java.util.Arrays;
/**
 * An example demonstrating the effects of just-in-time compilation
 * on time measurements.
 *
 * @author Stefan Nilsson
 * @version 2011-02-07
 */
public final class TimingExample {
    /**
     * If you're using a JVM with just-in-time compilation,
     * chanses are that the first reported time is much
     * larger than the rest: during most of the first
     * invocation of the sum() method, the code has yet
     * to be compiled and optimized.
     */
    public static void main(String[] args) {
        IntSorter insertion = new Insertionsort();
        IntSorter v1 = new QuicksortFixedPivot();
        IntSorter v2 = new QuicksortRandomPivot();
        IntSorter v3 = new QuicksortFixedPivotInsertion();
        IntSorter v4 = new QuicksortRandomPivotInsertion();

        Data[] randomtests = {
                new Data(100, 100, Data.Order.RANDOM),
                new Data(1000, 100, Data.Order.RANDOM),
                new Data(10000, 100, Data.Order.RANDOM),
                new Data(100000, 100, Data.Order.RANDOM),
                new Data(1000000, 100, Data.Order.RANDOM),
        };

        Data[] sortedtests = {
                new Data(100, 100, Data.Order.ASCENDING),
                new Data(1000, 100, Data.Order.ASCENDING),
                new Data(10000, 100, Data.Order.ASCENDING),
                new Data(100000, 100, Data.Order.ASCENDING),
                new Data(1000000, 100, Data.Order.ASCENDING),
        };

        Data[] reversedtests = {
                new Data(100, 100, Data.Order.DESCENDING),
                new Data(1000, 100, Data.Order.DESCENDING),
                new Data(10000, 100, Data.Order.DESCENDING),
                new Data(100000, 100, Data.Order.DESCENDING),
                new Data(1000000, 100, Data.Order.DESCENDING),
        };

        Data[] equaltests = {
                new Data(100, 1, Data.Order.RANDOM),
                new Data(1000, 1, Data.Order.RANDOM),
                new Data(10000, 1, Data.Order.RANDOM),
                new Data(100000, 1, Data.Order.RANDOM),
                new Data(1000000, 1, Data.Order.RANDOM),
        };

        ArrayList<String> results = new ArrayList<String>();
        Stopwatch s = new Stopwatch();

        for (Data d : equaltests) { // change to array of preference
            s.start();
            v4.sort(d.get()); // change to version to test
            s.stop();
            results.add(String.valueOf(s.nanoseconds()));
            s.reset();
        }
        System.out.println("runtimes = " + results);
    }

    }
