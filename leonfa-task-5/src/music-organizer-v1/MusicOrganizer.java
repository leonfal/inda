import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<>();
    }
    
    // exercise 4.14
    public void checkIndex(int index){
        int size = files.size();
        if(index < 0 || index >= size){
            if (files.isEmpty()){ // if the collection is empty the range is not from 0 to 0
                System.out.println("error: your collection is empty!");
            }
            else{
                System.out.println("error: index valid range is 0 to " + (size-1));
            }
        }
    }
    
    // exercise 4.15
    public boolean validIndex(int index){
        int size = files.size();
        if(index < 0 || index >= size){
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }
    
    public void addMusic(){
    files.add("../audio/BigBillBroonzy-BabyPleaseDontGo1-8kbps.mp3");
    files.add("../audio/BlindBlake-EarlyMorningBlues-8kbps.mp3");
    files.add("../audio/BlindLemonJefferson-matchBoxBlues-8kbps.mp3");
    files.add("../audio/BlindLemonJefferson-OneDimeBlues-8kbps.mp3");
    }
    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index)) { // exercise 4.16 (uses method validIndex())
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index)) { // exercise 4.16 (uses method validIndex())
            files.remove(index);
        }
    }
}
