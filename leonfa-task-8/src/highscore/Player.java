/**
 * This secondary class creates a player with a name, country and a score associated with it.
 * 
 * @author provided by Ric Glassey
 * @version 2021.11.11
 */
public class Player {
    String name;
    String country;
    int score;

    public Player(String name, String country, int score) {
        this.name = name;
        this.country = country;
        this.score = score;
    }

    /**
     * Prints the players parameters.
     * 
     * @return a sentence with the name counrty and score.
     */
    public String toString() {
        return "Player " + name + " from " + country + " scored " + score + " points";
    }
}
