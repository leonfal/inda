import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction(){
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /** Exercise 4.48
     * Prints the closing bidder
     */
    public void close(){
        for (Lot lot : lots){
            Bid bid = lot.getHighestBid();
            if(bid != null) {
                System.out.println("sold " + lot.toString() + " to " + bid.getBidder().getName());
            }
            else{
                System.out.println("no bidder to " + lot);
            }
        }
    }

    /**
     * method to get the unsold items
     * @return the unsolds array.
     */
    public ArrayList<Lot> getUnsold(){
        ArrayList<Lot> unsolds = new ArrayList<Lot>();
        for(Lot lot : lots){
            if (lot.getHighestBid() == null){
                unsolds.add(lot);
            }
        }
        return unsolds;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description){
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots(){
        for(Lot lot : lots){
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     *
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value){
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null){
            Bid bid = new Bid(bidder, value);
            boolean successful = selectedLot.bidFor(bid);
            if(successful){
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else{
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */


     // Exercise 4.50 the problems with the previous getLot method is that when removeLot is
     // called the indexing of every lot after the removed lot in lots is being moved. When that happens
     // the line where it checks if selectedLot is != lotnumber will now be wrong

    // Exercise 4.51
    public Lot getLot(int lotNumber){
        Lot selectedLot = null;
        for(Lot lot : lots){
            if (lot.getNumber() == lotNumber){
                selectedLot = lot;
                break;
            }
        }
        return selectedLot;
    }

    // Exercise 4.52
    public Lot removeLot(int number){
        Iterator<Lot> i = lots.iterator();
        while(i.hasNext()){
            Lot lot = i.next();
            if(lot.getNumber() == number){
                i.remove();
                return lot;
            }
        }
        return null; //didn't find any lot with the given number
    }

}
