import java.util.Random;
import java.util.ArrayList;
import java.lang.Integer;
/**
 * Write a description of class RandomTester here.
 *
 * @author Fällman, Leon
 * @version 29 okt 2021
 */
public class RandomTester{

    private Random rand;

    private ArrayList<String> words;
    /**
     * Constructor for objects of class RandomTester
     */
    public RandomTester(){
        // initialise instance variables
        rand = new Random();
        words = new ArrayList<String>();
    }

    // Exercise 5.14
    /**
     * Method to print out random integer
     *
     */
    public void printOneRandom(){
        System.out.println(rand.nextInt());
    }

    // Exercise 5.14
    /**
     * Method to print out multiple random integers
     *
     * @param howMany The number of random ints to print
     */
    public void prinftMultiRandom(int howMany){

        for(int iterate = 0; iterate < howMany; iterate++){
            System.out.println(rand.nextInt());
        }
    }

    // Exercise 5.16
    /**
     * Method to illustrate rolling a dice
     */
    public void throwDice(){
        System.out.println(rand.nextInt(6)+1);
    }

    // Exercise 5.17 + 5.18

    /**
     * Method to return a randomly added string from words
     *
     * @return randomWord The randomly selected string.
     */
    public String getResponse(){
        words.add("hello");
        words.add("goodbye");
        words.add("evening");
        words.add("morning");
        int randomIndex = rand.nextInt(4);
        String randomWord = words.get(randomIndex);
        return randomWord;
    }

    // Exercise 5.20
    /**
     * Method to return two random integers in given range.
     *
     * @param min,max The range from minumum to maximum.
     * @return randomInt The random integer.
     */
    public int randInRangeMinMax(int max, int min){
        int randomInt = rand.nextInt((max-min)+1) + min;
        return randomInt;
    }

    public static void main(String[] args) {
        RandomTester r1 = new RandomTester();
        r1.prinftMultiRandom(10);
        r1.throwDice();
        System.out.println(r1.getResponse());
    }
}
