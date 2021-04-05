package myStore; /**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */
import java.math.BigDecimal;

/**
 * The ShoppingCart class extends the Inventory class to add a nickname parameter, and functionality to remove items and checkout.
 */
public class ShoppingCart extends Inventory {
    private String nick;
    public ShoppingCart(String nickname){
        super();
        nick = nickname;
    }

    /**
     * This method overrides Inventory.addProduct to set the quantity of a new product in a cart to 1.
     * @param newProduct is a Product object.
     * @return is a boolean that returns if the product was successfully added or not.
     */
    @Override
    public boolean addProduct(Product newProduct) {
        return super.addProduct(newProduct, 1);
    }

    /**
     * This method removes all of a Product object from the ShoppingCart.
     * @param oldProduct is a Product object.
     */
    public void removeProduct(Product oldProduct) {
        getQuantity().remove(getProducts().indexOf(oldProduct));
        getProducts().remove(getProducts().indexOf(oldProduct));
    }

    /**
     * This method removes a given quantity of a given product.
     * @param oldProduct is a Product object.
     * @param qtyToRemove is an int of the amount of items to remove.
     */
    public void removeProduct(Product oldProduct, int qtyToRemove) {
        if (getQuantity().get(getProducts().indexOf(oldProduct)) <= qtyToRemove){
            this.removeProduct(oldProduct);
        }
        else{
            int newQTY = getQuantity().get(getProducts().indexOf(oldProduct)) - qtyToRemove;
            getQuantity().set(getProducts().indexOf(oldProduct), newQTY);
        }
    }

    /**
     * This method is an accessor for the cart's Nickname.
     * @return is a String.
     */
    public String getNick() {
        return nick;
    }

    /**
     * This method is a setter for the car's Nickname
     * @param nickname is a String.
     */
    public void setNick(String nickname) {
        this.nick = nickname;
    }

    /**
     * This method calculated the total cost of all the products in the cart.
     * @return is a BigDecimal Object for the total
     */
    public BigDecimal checkout(Inventory inventory){
        BigDecimal sum = new BigDecimal(0);
        for (int i = this.getProducts().size()-1; i >= 0; i--) {
            if(inventory.stock(this.getProducts().get(i).getId(), 0 - this.getQuantity().get(i))){
                sum = sum.add(this.getProducts().get(i).getPrice().multiply(BigDecimal.valueOf(this.getQuantity().get(i))));
            }
            else{
                this.setQty(this.getProducts().get(i).getId(), inventory.getQty(this.getProducts().get(i).getId()));
                sum = sum.add(this.getProducts().get(i).getPrice().multiply(BigDecimal.valueOf(this.getQuantity().get(i))));
            }
        }
        return sum;
    }
}
