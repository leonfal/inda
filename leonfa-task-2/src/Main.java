class Main {
    public static void main(String[] args) {
        TicketMachine tm1 = new TicketMachine(35);
        tm1.insertMoney(500);
        tm1.printTicket();
        System.out.println("balance: " + tm1.getBalance());
        tm1.refundBalance();
        System.out.println("balance after refund: " + tm1.getBalance());
        System.out.println("total money collected from machine: " + tm1.emptyMachine());
        System.out.println("amount of money in the machine after emptying it: " +tm1.getTotal());
    }
}