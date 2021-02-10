// SYSC2004 Project
// Milestone 1
// Michael Silveira 101145789
// January 27th 2021

import java.math.BigDecimal;

public class StoreManager {
    public StoreManager(){}
    public boolean test(){
        System.out.println("Hello, If you are reading this, You are looking at milestone 1");

        System.out.println("Creating new products");
        Product p1 = new Product("Carrot", "24907", new BigDecimal("2.99"));
        Product p2 = new Product("Cucumber", "34676", new BigDecimal("3.99"));
        Product p3 = new Product("Orange", "34587", new BigDecimal("5.99"));
        Product p4 = new Product("Lemon", "23458", new BigDecimal("7.99"));
        Product p5 = new Product("Excel Gum", "00040", new BigDecimal("1.99"));

        System.out.println("Initializing inventory");
        Inventory i1 = new Inventory();

        System.out.println("Adding products to inventory");
        i1.addProduct(p1, 7);
        i1.addProduct(p2);
        i1.addProduct(p3, 5);
        i1.addProduct(p4);
        i1.addProduct(p5);

        System.out.println("Testing restocking");
        i1.stock("43676", 2); //error trigger: ID not recognised
        i1.stock("23458", 42);
        i1.stock("00040", 1);

        System.out.println("Testing destocking");
        i1.stock("34587", -4);
        i1.stock("00040", -1);
        i1.stock("24907", -8); //error trigger: QTY BELOW 0

        System.out.println("Testing finished and didn't crash! Hooray");
        return true;
    }
}
