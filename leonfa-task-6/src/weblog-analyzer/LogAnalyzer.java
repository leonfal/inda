/**
 * Read web server data and analyse hourly access patterns.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(){
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Exercise 4.73 + 4.74
     * Get number of accessess
     * @return the total accessess.
     */
    public int numberOfAccesses(){
        int total = 0;
        for(int elements : hourCounts){
            total += elements;
        }
        return total;
    }

    /**
     * Exercise 4.75
     * Calculates the busiest hour
     * @return the busiest hour.
     */
    public int busiestHour(){
        int busiestHour = 0;
        int busiestHourIndex = 0;

        for(int i = 0; i < hourCounts.length; i++){
            if(hourCounts[i] > busiestHour){
                busiestHour = hourCounts[i];
                busiestHourIndex = i;

            }
        }
        return busiestHourIndex;
    }

    /**
     * Exercise 4.76
     * Calculates the quietest hour
     * @return the quitest hour
     */
    public int quietestHour(){
        int quietestHour = hourCounts[0];
        int quietestHourIndex = 0;

        for(int i = 0; i < hourCounts.length; i++){
            if(hourCounts[i] < quietestHour){
                quietestHour = hourCounts[i];
                quietestHourIndex = i;
            }
        }
        return quietestHourIndex;
    }

    // Exercise 4.77
    /*
    The busiest hour that is returned is the FIRST hour with the most entries
    because the loop checks the value at indexes strictly less than the previous
    value.
    */

    /**
     * Exercise 4.78
     * calculates the busiest hours
     * @return the busiest hour period
     */
    public int busiestTwoHours() {
        int busiestHour = 0;
        int busiestHourPeriod = 0;

        for (int i = 0; i < hourCounts.length - 1; i++) { // the loop iterates to the length -1
            if ((hourCounts[i] + hourCounts[i + 1]) > busiestHour) { // checks the hour and the hour after
                busiestHour = (hourCounts[i] + hourCounts[i + 1]);
                busiestHourPeriod = i;
            }
        }
        return busiestHourPeriod;
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData(){
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts(){
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData(){
        reader.printData();
    }
}
