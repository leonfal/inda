import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class to populate, shuffle and sum different arrays and lists
 *
 * @author Leon Fällman
 * @version 2021-11-23
 */
public class ListProcessor {
    Random rand = new Random();
    /**
     * Exercise 1
     * Method to populate an array
     * @param from the lower bound (inclusive)
     * @param to the upper bound (exclusive)
     * @return the array
     */
    public int[] arraySequence(int from, int to){
        if (to < from){
            throw new IllegalArgumentException();
        }
        int size = to - from;
        int[] myArray = new int[size];

        if (from == to){
            return myArray;
        }
        else {
            for (int i = 0; i < size; i++) {
                myArray[i] = i + from;
            }
            return myArray;
        }
    }

    /**
     * Exercise 1
     * Method to populate ArrayList
     * @param from the lower bound (inclusive)
     * @param to the upper bound (exclusive)
     * @return the arrayList
     */
    public List<Integer> listSequence(int from, int to){
       List<Integer> myList = new ArrayList<>();

       if (from == to){
           return myList;
       }
       else if (to < from){
           throw new IllegalArgumentException();
       }
       else {
           for (int i = from; i < to; i ++){
               myList.add(i);
           }
           return myList;
       }
    }

    /**
     * Exercise 2
     * Method to shuffle an array
     * @param numbers the array to shuffle
     * @return the shuffled list
     */
    public int[] shuffled(int[] numbers){
        int[] cloneArray = numbers.clone();
        for (int i = 0; i < cloneArray.length; i++){
            int index = rand.nextInt(cloneArray.length);
            int temp = cloneArray[index];
            cloneArray[index] = cloneArray[i];
            cloneArray[i] = temp;
        }
        return cloneArray;
    }

    /**
     * Exercise 2
     * Method to shuffle an array
     * @param numbers the arrayList to shuffle
     * @return the shuffled list
     */
    public List<Integer> shuffled(List<Integer> numbers) {
        List<Integer> copyList = new ArrayList<>(numbers);
        List<Integer> shuffledList = new ArrayList<>();

        while (!copyList.isEmpty()) {
            int index = rand.nextInt(copyList.size());
            shuffledList.add(copyList.get(index));
            copyList.remove(index);
        }
        return shuffledList;
    }

    /**
     * Exercise 3
     * Method to sum all numbers of array
     * @param numbers the array to sum
     * @return the sum
     */
    public int sumIterative(int[] numbers){
        int sum = 0;
        for (int i = 0; i < numbers.length; i++){
            sum += numbers[i];
        }
        return sum;
    }

    /**
     * Exercise 3
     * Method to sum all numbers of arrayList
     * @param numbers the arrayList to sum
     * @return the sum
     */
    public int sumIterative(List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        return sum;
    }

    /**
     * Exercise 4
     * Method to sum all numbers of array recursively
     * @param numbers the array to sum
     * @return the sum
     */
    public int sumRecursive(int[] numbers){
        // base case
        if (numbers.length == 0){
            return 0;
        }
        int[] subArray = Arrays.copyOfRange(numbers, 1, numbers.length);
        return numbers[0] + sumRecursive(subArray);
    }

    /**
     * Exercise 4
     * Method to sum all numbers of arrayList recursively
     * @param numbers the arrayList to sum
     * @return the sum
     */
    public int sumRecursive(List<Integer> numbers){
        if (numbers.isEmpty()){
            return 0;
        }
        else {
            List<Integer> subList = numbers.subList(1, numbers.size());
            return numbers.get(0) + sumRecursive(subList);
        }
    }
}
