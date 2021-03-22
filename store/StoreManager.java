package store; /**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */
import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * The StoreManager class is the management backend that hosts the inventory, storeviews and shoppingcarts.
 */
public class StoreManager {
    private final Inventory inventory;
    private final ArrayList<StoreView> views;
    private final ArrayList<ShoppingCart> carts;

    /**
     * This default constructor initializes the inventory and arrayLists.
     */
    public StoreManager(){
        inventory = new Inventory();
        views = new ArrayList<>();
        carts = new ArrayList<>();
    }

    /**
     * This method is depreciated, from milestone 1 for testing the Product and Inventory classes.
     * @return is a boolean if test passes
     */
    private boolean test(){
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
        System.out.println(i1);

        System.out.println("Testing restocking");
        i1.stock("43676", 2); //error trigger: ID not recognised
        i1.stock("23458", 42);
        i1.stock("00040", 1);
        System.out.println(i1);

        System.out.println("Testing destocking");
        i1.stock("34587", -4);
        i1.stock("00040", -1);
        i1.stock("24907", -8); //error trigger: QTY BELOW 0
        System.out.println(i1);

        System.out.println("Testing finished and didn't crash! Hooray");
        return true;
    } //Depreciated

    /**
     * This method adds a new StoreView and new ShoppingCart to their respective ArrayLists.
     * @return is a String confirming the addition.
     */
    public String newView() {
        carts.add(new ShoppingCart(String.valueOf(views.size())));
        views.add(new StoreView(String.valueOf(views.size()), this));
        return "New StoreView Created Successfully";
    }

    /**
     * This method displays the ArrayList of StoreViews names.
     * @return is a String with the storeview names
     */
    public String displayStoreViews() {
        StringBuilder s = new StringBuilder("StoreView Names\n");
        for (StoreView i: views){
            s.append(i.getNick());
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * This method calls the StoreView sub-routine for the StoreView based on it's Nick(nickname).
     * @param nick is a String.
     */
    public void selectStoreView(String nick) {
        for (StoreView i: views){
            if(i.getNick().equals(nick)){
                i.run();
            }
        }
    }

    /**
     * This method removes/closes a StoreView given from a nickname.
     * @param nick is a String.
     */
    public void removeStoreView(String nick) {
        for (StoreView i: views){
            if(i.getNick().equals(nick)){
                //return cart contents
                carts.remove(views.indexOf(i));
                views.remove(i);
            }
        }
    }

    /**
     * This is a Manager Sub-routine that runs to allow a manager to control the inventory.
     */
    public void run(){ //manage backend
        boolean quit = false;
        char program;
        String itemName, itemID, itemPrice;
        int itemQTY;

        //TODO ADD PASSWORD CHECK(Milestone 3)

        while (!quit) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Display Inventory		        	D");
            System.out.println("Add New Item to Inventory		   	N");
            System.out.println("Add Item Stock		             	S");
            System.out.println("Remove Item	Stock	            	R");
            System.out.println("Override Stock		            	O");
            System.out.println("Return to Main Menu                 Q");

            program = Keyboard.getCharacter();
            switch (program){
                case 'd':
                case 'D':
                    System.out.println(this.inventory);
                    break;

                case 'n':
                case 'N':
                    System.out.println("Product name: ");
                    itemName = Keyboard.getString();
                    System.out.println("Product ID: ");
                    itemID = Keyboard.getString();
                    System.out.println("Product Price: ");
                    itemPrice = Keyboard.getString();
                    System.out.println("Product QTY: ");
                    itemQTY = Keyboard.getInteger();
                    this.inventory.addProduct(new Product(itemName, itemID ,new BigDecimal(itemPrice)), itemQTY);
                    System.out.println("\nProduct Added");
                    break;

                case 's':
                case 'S':
                    System.out.println("Product ID: ");
                    itemID = Keyboard.getString();
                    System.out.println("Product QTY to add: ");
                    itemQTY = Keyboard.getInteger();
                    this.inventory.stock(itemID, itemQTY);
                    System.out.format("\nInventory now contains %d %s",
                            this.inventory.getQty(itemID),
                            this.inventory.getProduct(itemID));
                    break;

                case 'r':
                case 'R':
                    System.out.println("Product ID: ");
                    itemID = Keyboard.getString();
                    System.out.println("Product QTY to remove: ");
                    itemQTY = Keyboard.getInteger();
                    this.inventory.stock(itemID, 0-itemQTY);
                    System.out.format("\nInventory now contains %d %s",
                            this.inventory.getQty(itemID),
                            this.inventory.getProduct(itemID));
                    break;

                case 'o':
                case 'O':
                    System.out.println("Product ID: ");
                    itemID = Keyboard.getString();
                    System.out.println("Product QTY: ");
                    itemQTY = Keyboard.getInteger();
                    this.inventory.setQty(itemID, itemQTY);
                    System.out.format("Inventory now contains %d %s",
                            this.inventory.getQty(itemID),
                            this.inventory.getProduct(itemID));
                    break;

                case 'q':
                case 'Q':
                    quit = true;

                default :
                    break;
            }
        }
    }

    /**
     * This method is an accessor for the Inventory.
     * @return is a Inventory Object.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * This method is an accessor for the ShoppingCart corresponging to it's nickname.
     * @param nick is a String.
     * @return is a ShoppingCart Object.
     */
    public ShoppingCart getCart(String nick) {
        for (ShoppingCart i: carts){
            if (i.getNick().equals(nick)){
                return i;
            }
        }
        return null;
    }


}
