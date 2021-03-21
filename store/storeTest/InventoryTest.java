package store.storeTest;
import org.junit.jupiter.api.*;
import store.Inventory;
import store.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory i;
    @BeforeEach
    void init(){
       this.i = new Inventory();
       this.i.addProduct(new Product("carrot", "01", new BigDecimal(5.99)));
       this.i.addProduct(new Product("celery", "02", new BigDecimal(3.99)), 7);
    }

    @Test
    void addProductTest() {
        Product newProduct = new Product("cucumber", "03", new BigDecimal(4.99));
        assertTrue(i.addProduct(newProduct));
        assertTrue(i.getProduct("03").equals(newProduct));
        assertFalse(i.addProduct(newProduct));
    }

    @Test
    void addProductsTest(){
        Product newProduct = new Product("cucumber", "03", new BigDecimal(4.99));
        assertTrue(i.addProduct(newProduct, 6));
        assertEquals(newProduct, i.getProduct("03"));
        assertEquals(6, i.getQty("03"));
        assertFalse(i.addProduct(newProduct, 3));
    }

    @Test
    void getProductTest(){
        assertEquals(new Product("carrot", "01", new BigDecimal(5.99)), i.getProduct("01"));
        assertEquals(new Product("celery", "02", new BigDecimal(3.99)), i.getProduct("02"));
    }

    @Test
    void getQtyTest(){
        assertEquals(0, i.getQty("01"));
        assertEquals(7, i.getQty("02"));
    }

    @Test
    void setQtyTest(){
        assertEquals(0, i.getQty("01"));
        assertEquals(7, i.getQty("02"));

        i.setQty("01", 6);
        i.setQty("02", 4);

        assertEquals(6, i.getQty("01"));
        assertEquals(4, i.getQty("02"));
    }

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
