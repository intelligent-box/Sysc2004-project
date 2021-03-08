// SYSC2004 Project
// Milestone 1
// Michael Silveira 101145789
// January 27th 2021

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Integer> quantity = new ArrayList<>();

    public Inventory(){}

    public boolean addProduct(Product newProduct){
        for (Product i: products){
            if (i.equals(newProduct)){
                return false;
            }
        }
        products.add(newProduct);
        quantity.add(0);
        return true;
    }

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

    public Product getProduct(String id){
        for (Product i: products){
            if (i.getId().equals(id)){
                return i;
            }
        }
        System.out.println("error: ID Not Recognised");
        return null;
    }

    public int getQty(String id){
        Product i = getProduct(id);
        if (i != null){
            return quantity.get(products.indexOf(i));
        }
        return 0;
    }

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

    public boolean setQTY(String id, int qty){
        Product i = getProduct(id);
        if (i != null) {
            quantity.set(products.indexOf(i), qty);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < products.size(); i++){
            str += products.get(i);
            str += ", Quantity = ";
            str += quantity.get(i);
            str += "\n";
        }
        return str;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
