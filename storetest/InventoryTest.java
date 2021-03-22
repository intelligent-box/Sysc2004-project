package storetest;/**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */

import org.junit.jupiter.api.*;
import store.Inventory;
import store.Product;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests Inventory.java
 */
public class InventoryTest {
    Inventory i;

    /**
     * this method is called before each test and reinitializes man with fresh data.
     */
    @BeforeEach
    void init(){
       this.i = new Inventory();
       this.i.addProduct(new Product("carrot", "01", new BigDecimal(5.99)));
       this.i.addProduct(new Product("celery", "02", new BigDecimal(3.99)), 7);
    }

    /**
     * this method tests addProduct with a given quantity
     */
    @Test
    void addProductTest() {
        Product newProduct = new Product("cucumber", "03", new BigDecimal(4.99));
        assertTrue(i.addProduct(newProduct));
        assertTrue(i.getProduct("03").equals(newProduct));
        assertFalse(i.addProduct(newProduct));
    }

    /**
     * this method tests addProduct() with a given quantity
     */
    @Test
    void addProductsTest(){
        Product newProduct = new Product("cucumber", "03", new BigDecimal(4.99));
        assertTrue(i.addProduct(newProduct, 6));
        assertEquals(newProduct, i.getProduct("03"));
        assertEquals(6, i.getQty("03"));
        assertFalse(i.addProduct(newProduct, 3));
    }

    /**
     * this method tests getProduct()
     */
    @Test
    void getProductTest(){
        assertEquals(new Product("carrot", "01", new BigDecimal(5.99)), i.getProduct("01"));
        assertEquals(new Product("celery", "02", new BigDecimal(3.99)), i.getProduct("02"));
    }

    /**
     * this method tests getQty()
     */
    @Test
    void getQtyTest(){
        assertEquals(0, i.getQty("01"));
        assertEquals(7, i.getQty("02"));
    }

    /**
     * this method tests setQty()
     */
    @Test
    void setQtyTest(){
        assertEquals(0, i.getQty("01"));
        assertEquals(7, i.getQty("02"));

        i.setQty("01", 6);
        i.setQty("02", 4);

        assertEquals(6, i.getQty("01"));
        assertEquals(4, i.getQty("02"));
    }

    /**
     * this method tests stock()
     */
    @Test
    void stockTest(){
        assertEquals(0, i.getQty("01"));
        assertEquals(7, i.getQty("02"));

        i.stock("01", 5);
        i.stock("02", 3);

        assertEquals(5, i.getQty("01"));
        assertEquals(10, i.getQty("02"));
    }
}
