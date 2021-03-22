package store;/**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */

/**
 * The StoreView class is a UI frontend for each shopping cart.
 */
public class StoreView {
    private final StoreManager manager;
    private ShoppingCart cart;
    private String nick;

    /**
     * This constructor innitializes Storeview.
     * @param name is a String for nickname.
     * @param manager is a Manager Object.
     */
    public StoreView(String name, StoreManager manager){
        this.manager = manager;
        this.nick = name;
        this.cart = this.manager.getCart(this.nick);
    }

    /**
     * This method is an accessor for the nickname.
     * @return is a String.
     */
    public String getNick() {
        return nick;
    }

    /**
     * This method is an accessor for the cart.
     * @return is a ShoppingCart Object.
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * This method sets the nickname from a given String.
     * @param nick is a String.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * This method sets the cart from a given ShoppingCart
     * @param cart is a ShoppingCart Object.
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    /**
     * This is the StoreView Sub-Routine that runs the UI
     */
    public void run() {
        boolean quitStoreView = false;
        String prodName;
        char program;
        int qty;
        char receipt;

        while (!quitStoreView) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Display Cart		        	C");
            System.out.println("Display Inventory		       	I");
            System.out.println("Add to Cart		            	A");
            System.out.println("Remove from Cart		       	R");
            System.out.println("Checkout		            	H");
            System.out.println("Return to Main Menu             Q");

            program = Keyboard.getCharacter();
            switch (program){
                case 'c':
                case 'C':
                    System.out.println(this.cart);
                    break;

                case 'i':
                case 'I':
                    System.out.println(this.manager.getInventory());
                    break;

                case 'a':
                case 'A':
                    System.out.println("Enter the name of the product you want to add to your cart: ");
                    prodName = Keyboard.getString();
                    System.out.println("Enter the quantity of items you want to add to your cart: ");
                    qty = Keyboard.getInteger();
                    this.cart.addProduct(manager.getInventory().getProduct(prodName), qty);
                    break;

                case 'r':
                case 'R':
                    System.out.println("Enter the name of the product you want to remove from your cart: ");
                    prodName = Keyboard.getString();
                    System.out.println("Enter the quantity of items you want to remove from your cart: ");
                    qty = Keyboard.getInteger();
                    this.cart.removeProduct(getCart().getProduct(prodName), qty);
                    break;
                case 'h':
                case 'H':
                    System.out.println("Do you want a receipt? (y/n)");
                    receipt = Keyboard.getCharacter();
                    if(receipt == 'y' || receipt == 'Y'){
                        System.out.println(this.cart);
                        System.out.println("Total                   $" + this.cart.checkout(manager.getInventory()));
                    }
                    else{
                        System.out.println("Your total is $" + this.cart.checkout(manager.getInventory()));
                    }



                case 'q':
                case 'Q':
                    quitStoreView = true;
                    break;

                default :
                    break;
            }
        }
    }

    public static void main(String[] args) {
        boolean quitMain = false;

        System.out.println("Welcome to StoreView");
        System.out.println("A program created by Michael Silveira");

        StoreManager manager = new StoreManager();
        char program;

        while (!quitMain) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Add New StoreView		        	N");
            System.out.println("Display StoreViews		        	D");
            System.out.println("Select StoreView		           	S");
            System.out.println("Remove StoreView		           	R");
            System.out.println("Manage Store		            	M");
            System.out.println("Quit			                    Q");

            program = Keyboard.getCharacter();
            switch (program){
                case 'n':
                case 'N':
                    System.out.println(manager.newView());
                    break;

                case 'd':
                case 'D':
                    System.out.println(manager.displayStoreViews());
                    break;

                case 's':
                case 'S':
                    System.out.println("Enter the name of your desired storeview: ");
                    manager.selectStoreView(Keyboard.getString());
                    break;

                case 'r':
                case 'R':
                    System.out.println("Enter the name of your desired storeview: ");
                    manager.removeStoreView(Keyboard.getString());
                    break;
                case 'm':
                case 'M':
                    manager.run();
                    break;
                case 'q':
                case 'Q':
                    quitMain = true;

                default :
                    break;
            }
        }
    }
}
