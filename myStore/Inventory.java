package myStore; /**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */
import java.util.ArrayList;

/**
 * Inventory is a class that contains 2 ArrayLists, one of Products and one of Integers to represent quantities
 */
public class Inventory extends ProductStockContainer {

    /**
     * The default constructor initializes the ArrayLists
     */
    public Inventory(){}

    /**
     * This method adds a Product to the inventory with a quantity of 0.
     * @param newProduct is a Product object
     * @return is a boolean to confirm a product has been added.
     */
    @Override
    public boolean addProduct(Product newProduct){ return super.addProduct(newProduct, 0); }

    /**
     * This Method overrides Object.toString() to return all the information for each product and it's quantity.
     * @return is a String of all the information.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < products.size(); i++){
            str.append(products.get(i));
            str.append(", Quantity = ");
            str.append(quantity.get(i));
            str.append("\n");
        }
        return str.toString();
    }

}
