import java.util.Random;
import java.lang.StringBuilder;

public class NameGenerator{

    // Exercise 5.71
    /**
     * Generates a starwars name
     * @return String
     */
    public String generateStarWarsName(String firstName, String lastName, String mothersName, String townName){

        // Builds the starwars name with stringbuilder.
        StringBuilder starWarsName = new StringBuilder();
        starWarsName.append(lastName.substring(0, 3)).append(firstName.substring(0, 2)).append(' ').append(mothersName.substring(0, 2)).append(townName.substring(0, 3));
        
        return starWarsName.toString();
    }

    public static void main(String[] args) {
        NameGenerator ng = new NameGenerator();
        System.out.println(ng.generateStarWarsName("Leon", "Fällman", "Jensen", "Stockholm"));
    }
}