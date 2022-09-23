public class Main {
    public static void main(String[] args) {
        Auction a1 = new Auction();
        a1.enterLot("my sweatshirt");
        a1.enterLot("my leggings");
        a1.enterLot("my watterbottle");
        Person p1 = new Person("Leon");

        a1.makeABid(1, p1, 1000);
        a1.close();
        System.out.println(a1.getUnsold());
        System.out.println(a1.removeLot(3));
        a1.showLots();
    }
}
