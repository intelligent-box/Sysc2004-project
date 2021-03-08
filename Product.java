// SYSC2004 Project
// Milestone 1
// Michael Silveira 101145789
// January 27th 2021

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final String id;
    private final BigDecimal price;

    public Product(String name, String id, BigDecimal price){
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name = " + name +
                ", Id = " + id +
                ", Price = " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) && id.equals(product.id) && price.equals(product.price);
    }
}
