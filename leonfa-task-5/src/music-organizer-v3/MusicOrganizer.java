import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 *
 * Modified by Simon Larsén 2017.09.23
 *
 * @version 2017.09.23
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
    // A player for the music files.
    private MusicPlayer player;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<>();
        player = new MusicPlayer();
    }

    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }
    
    public void addMusic(){
    files.add("../audio/BigBillBroonzy-BabyPleaseDontGo1-8kbps.mp3");
    files.add("../audio/BlindBlake-EarlyMorningBlues-8kbps.mp3");
    files.add("../audio/BlindLemonJefferson-matchBoxBlues-8kbps.mp3");
    files.add("../audio/BlindLemonJefferson-OneDimeBlues-8kbps.mp3");
    }
    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFiles()
    {   //exercise 4.24
        int position = 0; // sets the position aka indexing to zero to initialize the variable.
        for(String filename : files) {
            System.out.println(position + ": " + filename);
            position++; // adds 1 to the indexing/postioning each loop-time.
        } // this is why the classic for loop is suited better for looping with indexing!
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index)) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            player.startPlaying(filename);
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Play a file in the collection. Only return once playing has finished.
     * @param index The index of the file to be played.
     */
    public void playAndWait(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            player.playSample(filename);
        }
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= files.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    /**
     * List the names of files matching the given search string.
     * @param searchString The string to match.
     */
    public void listMatching(String searchString)
    {
        // exercise 4.26
        boolean isMatch = false; // initializes a boolean set to false by default
        for(String filename : files) {
            if(filename.contains(searchString)) {
                // A match.
                System.out.println(filename);
                isMatch = true; // if a match is found then isMatch is true
            }
        }
        if(!isMatch){ // if after the loop isMatch is still false, then print message.
            System.out.println("no files names matched the search string");
        }
    }
    
    // Exercise 4.27
    public void samplePlay(String searchArtist){
        for(int i = 0; i < files.size(); i++){
            if(files.get(i).contains(searchArtist)){
                playAndWait(i);
            }
        }
    }

    /* 
    public void samplePlay(String searchArtist){
        int i = 0;
        for(filename : files){
            if(filename.contains(searchArtist)){
                playAndWait(i);
            }
            i++;
        }
    }
    */
}

