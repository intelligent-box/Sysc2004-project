import java.math.BigDecimal;

public class StoreView {
    ShoppingCart cart;
    String nick;
    public StoreView(String name){
        cart = new ShoppingCart(name);
        this.nick = name;
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
    private static void cartOptions() {// prints menu
        System.out.println("\nPlease select a Function:");
        System.out.println("Function		Type\n");
        System.out.println("Display Carts			a");
        System.out.println("Select Cart			b");
        System.out.println("Display Current Cart			c");
        System.out.println("Add Product to Cart			e");
        System.out.println("Remove Product from Cart			f");
        System.out.println("Add Product to Inventory		g");
        System.out.println("Restock Product in Inventory		g");
        System.out.println("Display Inventory		g");
        System.out.println("Add Product to Inventory		g");

        System.out.println("Quit			q");
        return;
    }

    public BigDecimal checkout(){
        BigDecimal sum = new BigDecimal(0);
        for (int i = cart.getProducts().size()-1; i >= 0; i--) {
            sum = sum.add(this.cart.getProducts().get(i).getPrice().multiply(BigDecimal.valueOf(this.cart.getQuantity().get(i))));
        }
        return sum;
    }
}
