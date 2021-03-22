package store; /**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */

import java.math.BigDecimal;

/**
 * Product is a class that represents a store product,
 * containing a string for name and ID,
 * and using a Big Decimal object to accurately maintain price.
 */
public class Product {
    private final String name;
    private final String id;
    private final BigDecimal price;

    /**
     * The Product constructor
     * @param name is a String for the product name.
     * @param id is a String for the product ID.
     * @param price is a BigDecimal object for the product price.
     */
    public Product(String name, String id, BigDecimal price){
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * This Method returns the name of the product.
     * @return is a String for the product name.
     */
    public String getName() {
        return name;
    }

    /**
     *This Method returns the Price of the product.
     * @return is a BigDecimal Object for the product price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *This Method returns the ID of the product.
     * @return is a String for the product ID.
     */
    public String getId() {
        return id;
    }

    /**
     * This Method overrides Object.toString() to return a string representing the product.
     * @return is a String containing the product Name, ID and Price.
     */
    @Override
    public String toString() {
        return "Name = " + name +
                ", Id = " + id +
                ", Price = " + price;
    }

    /**
     * This Method overrides Object.equals() to check if a given object is a Product object with the same values.
     * @param o is an object to be checked to be equal to this product.
     * @return is a boolean for if they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) && id.equals(product.id) && price.equals(product.price);
    }
}
