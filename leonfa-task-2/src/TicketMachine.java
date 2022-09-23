/**
 * TicketMachine models a ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * Instances will check to ensure that a user only enters
 * sensible amounts of money, and will only print a ticket
 * if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TicketMachine 
{
    // The price of a ticket from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;

    /**
     * Create a machine that issues tickets of the given price.
     */
    // public TicketMachine(int cost)
    // {
    //     price = cost;
    //     balance = 0;
    //     total = 0;
    // }

    // Part of Exercise 2.44 specificies the price for a ticket 
    
    public TicketMachine(int price)
    {
        this.price = price;
    }
    
    // Part of Exercise 2.44 default value of price will be 38 (kronor)
    public TicketMachine()
    {
        price = 38;
    }

    // Exercise 2.45. this method below called empty() will 
    // empty all the money from the machine.
    public void empty()
    {
        total = 0;
    }

    public int getTotal(){
        return total;
    }

    public int getPrice()
    {
        return price;
    }

    // public int refundBalance() {
    //     balance = 0;
    //     return balance;
    // }

    // Exercise 2.58 The above method (commented) will not give the sam
    // result as the one below because 'balance' is given the value of zero
    // and will the return a 0, no matter what 'balance' was assigned to before
    // calling the method.
    public int getBalance()
    {
        return balance;
    }

    // Exercise 2.59 The method commented below will not compile
    // because every possible executable path ends with a return statement.
    // public int refundBalance() {
    //     return balance;
    //     balance = 0;
    // }

    public void insertMoney(int amount)
    {
        if(amount > 0) {
            balance = balance + amount;
        }
        else {
            System.out.println("Use a positive amount rather than: " +
                               amount);
        }
    }

    // Exercise 2.60 The method below will instead of using the 
    // already created private int price, it will initilize a new variable 'price' in the local scope
    // after compiling of the method is done, the variable will dissapear
    // 
    // public TicketMachine(int cost) {
    //     int price = cost;
    //     balance = 0;
    //     total = 0;
    // }


    //Exercise 2.61 This method will set the total to zero, but also
    // return the amount that total had before the reset.
    public int emptyMachine()
    {
        int totalStored = total;
        total = 0;
        return totalStored;
    }



    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket()
    {
        if(balance >= price) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + price;
            // Reduce the balance by the price.
            balance = balance - price;
        }
        else {
            System.out.println("You must insert at least: " +
                               (price - balance) + " more cents.");
                    
        }
    }

    /**
     * Return the money in the balance.
     * The balance is cleared.
     */
    public int refundBalance()
    {
        int amountToRefund;
        amountToRefund = balance;
        balance = 0;
        return amountToRefund;
    }

}