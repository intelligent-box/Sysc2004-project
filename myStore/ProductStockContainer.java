package myStore;

import java.util.ArrayList;

public abstract class ProductStockContainer {
    protected final ArrayList<Product> products = new ArrayList<>();
    protected final ArrayList<Integer> quantity = new ArrayList<>();

    /**
     * This method adds a Product to the inventory with a quantity of 0.
     * @param newProduct is a Product object
     * @return is a boolean to confirm a product has been added.
     */
    public abstract boolean addProduct(Product newProduct);

    /**
     * This method adds a Product to the inventory with a given quantity.
     * @param newProduct is a Product Object
     * @param qty is an int for the quantity being added.
     * @return is a boolean to confirm a product has been added.
     */
    public boolean addProduct(Product newProduct, int qty){
        for (Product i: products){
            if (i.equals(newProduct)){
                return false;
            }
        }
        products.add(newProduct);
        quantity.add(qty);
        return true;
    }

    /**
     * This method gets a inventory Product from it's ID.
     * @param id is a String for ID.
     * @return is a Product object of the corresponding object.
     */
    public Product getProduct(String id){
        for (Product i: products){
            if (i.getId().equals(id)){
                return i;
            }
        }
        System.out.println("error: ID Not Recognised");
        return null;
    }

    /**
     * This method gets the quantity of a product from it's ID.
     * @param id is a String for ID.
     * @return is an int of the quantity of the corresponding product.
     */
    public int getQty(String id){
        Product i = getProduct(id);
        if (i != null){
            return quantity.get(products.indexOf(i));
        }
        return 0;
    }

    /**
     *  This method adds or removes stock from the quantity of a given product from it's ID.
     * @param id is a String for ID.
     * @param stock is an int for the quantity being added or removed(positive int adds stock, negative int removes stock).
     * @return is a boolean of the success of the method running correctly.
     */
    public boolean stock(String id, int stock){//usage: stock +/- qty items
        int qty;
        Product i = getProduct(id);
        if (i != null){
            qty = quantity.get(products.indexOf(i)) + stock;
            if(qty >= 0){
                quantity.set(products.indexOf(i), qty);
                return true;
            }
            else{
                quantity.set(products.indexOf(i), 0);
                System.out.println("error: Quantity can not be less than 0");
                System.out.println("Quantity has been set to 0");
            }
        }
        return false;
    }

    /**
     * This method sets the quantity of a given product from it's ID.
     * @param id is a String for ID.
     * @param qty is an int for the quantity being set.
     * @return is a boolean of the success of the method running correctly.
     */
    public boolean setQty(String id, int qty){
        Product i = getProduct(id);
        if (i != null) {
            quantity.set(products.indexOf(i), qty);
            return true;
        }
        return false;
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
     * This method is an accessor for the ArrayList containing integers representing the item quantities.
     * @return is an ArrayList of Integers
     */
    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    /**
     * This method is an accessor for the ArrayList containing Product Objects.
     * @return is an ArrayList of Product Objects.
     */
    public ArrayList<Product> getProducts() {
        return products;
    }
}
