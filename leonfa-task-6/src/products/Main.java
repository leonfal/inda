public class Main {
    public static void main(String[] args) {
        StockManager sm = new StockManager();
        Product p1 = new Product(1, "computer");
        Product p2 = new Product(42, "headphones");
        sm.addProduct(p1);
        sm.addProduct(p2);   
        sm.printProductDetails();
        System.out.println(sm.numberInStock(1));
        sm.delivery(1, 100);
        sm.printProductDetails();
    }
}