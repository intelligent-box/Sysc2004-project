import java.math.BigDecimal;

public class StoreView {
    private StoreManager manager;
    private ShoppingCart cart;
    private String nick;

    public StoreView(String name, StoreManager manager){
        this.manager = manager;
        this.nick = name;
        this.cart = manager.getCart(this.nick);
    }

    public String getNick() {
        return nick;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void run() {
        boolean quitStoreView = false;
        String prodName = "";
        char program;
        int qty = 0;
        char receipt = 'a';
        Keyboard in = new Keyboard();

        while (quitStoreView != true) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Display Cart		        	C");
            System.out.println("Display Inventory		       	I");
            System.out.println("Add to Cart		            	A");
            System.out.println("Remove from Cart		       	R");
            System.out.println("Checkout		            	H");
            System.out.println("Return to Main Menu             Q");

            program = in.getCharacter();
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
                    prodName = in.getString();
                    System.out.println("Enter the quantity of items you want to add to your cart: ");
                    qty = in.getInteger();
                    this.cart.addProduct(manager.getInventory().getProduct(prodName), qty);
                    break;

                case 'r':
                case 'R':
                    System.out.println("Enter the name of the product you want to remove from your cart: ");
                    prodName = in.getString();
                    System.out.println("Enter the quantity of items you want to remove from your cart: ");
                    qty = in.getInteger();
                    this.cart.addProduct(manager.getInventory().getProduct(prodName), qty);
                    break;
                case 'h':
                case 'H':
                    System.out.println("Do you want a receipt? (y/n)");
                    receipt = in.getCharacter();
                    if(receipt == 'y' || receipt == 'Y'){
                        System.out.println(this.cart);
                        System.out.println("Total                   $" + this.cart.checkout());
                    }
                    else{
                        System.out.println("Your total is $" + this.cart.checkout());
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
        Keyboard in = new Keyboard();

        while (quitMain != true) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Add New StoreView		        	N");
            System.out.println("Display StoreViews		        	D");
            System.out.println("Select StoreView		           	S");
            System.out.println("Manage Store		            	M");
            System.out.println("Quit			                    Q");

            program = in.getCharacter();
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
                    manager.selectStoreView(in.getString());
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
