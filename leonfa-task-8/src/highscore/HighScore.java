import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class prints out the highscores of different players stored in a seperate file.
 * 
 * @author Leon Fällman
 * @version 2021.11.11
 */
public class HighScore {
    // Exercises 1, 2, 3
    public static void printHighScores(String filename) {
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(filename));
            
            ArrayList<Player> player = new ArrayList<>();

            String line;
            String[] playerData;
            while ((line = inputStream.readLine()) != null) {
                playerData = line.split(",");
                player.add(new Player(playerData[0], playerData[1], Integer.parseInt(playerData[2])));
            }
            System.out.println(player.toString());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        printHighScores("scores.txt");
    }
}
